package com.school.common.generic;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.school.common.facade.HiberSessionFactory;
import com.school.common.model.SchoolProfileModel;
import com.school.common.model.UniqueIdModel;
import com.school.common.model.UniqueIdPK;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UniqueIdHelper
{  
  @Autowired
  private HiberSessionFactory sessionFactory;

  
  
  public UniqueIdHelper()
  {
  }

   /* public Long getNextSequenceNo(Long seqId)
    {
      try{
        EaseSequenceValue seqValue = (EaseSequenceValue) ServiceLocalLocator.getInstance().getFacadeManager(FacadeCommonManager.class).read(EaseSequenceValue.class, seqId);
        long increment = seqValue.getIncrementBy().longValue();
        long lastId = seqValue.getLastUsedSeq().longValue();
        if(lastId != 0) //assuming that startValue is never 0.
          lastId = lastId + increment;
        else {
          long startValue = seqValue.getStartingSeq().longValue();
          if(startValue == 0)
            lastId = 1;
          else lastId = startValue;
        }
        seqValue.setLastUsedSeq(new Long(lastId));
        ServiceLocalLocator.getInstance().getFacadeManager(FacadeCommonManager.class).update(seqValue);
        return new Long(lastId);
      }catch(Exception ex)
      {
        System.out.println("Exception occured while generating Sequence - " + ex.toString());
      }
        return null;
    }*/

   public  String getNextTemplateName(String templateName, String templateId)
   {
      String nextTemplateName = templateName;
      String templatedateName = getDateAppended(templateName);
      int hashPosition = checkHashPosition(templatedateName);
      Session session = sessionFactory.getSessionFactory().openSession();
      try {
        if(hashPosition != -1) {
          UniqueIdPK pk = new UniqueIdPK(templateName, templateId);
          UniqueIdModel uniqueIdValue = sessionFactory.getSessionFactory().getCurrentSession().get(UniqueIdModel.class, pk);
          if(uniqueIdValue == null){
              return templateName;
          }
          String nextHashValue = getNextTemplateIdString(uniqueIdValue);
          uniqueIdValue.setSchoolProfile(new SchoolProfileModel(SessionManagerDataHelper.getSchoolCode()));
          nextTemplateName = templatedateName.substring(0,hashPosition) + nextHashValue + templatedateName.substring(hashPosition+1);
          Transaction transaction = session.beginTransaction();
          session.update(uniqueIdValue);
          transaction.commit();
          session.flush();
        }
      }catch(Exception ex){
      }
      finally{
          session.close();
      }
      return nextTemplateName;
   }

   /*public  String getNextTemplateNameForShipment(String templateName, String templateId){
	   String nextTemplateName = templateName;
	   String templatedateName=getDateAppended(templateName);
	   int hashPosition = checkHashPosition(templatedateName);
	   try {
		   if(hashPosition != -1) {
			   UniqueIdPK pk = new UniqueIdPK(templateName, templateId);
			   UniqueIdModel uniqueIdValue = (UniqueIdModel) ServiceLocalLocator.getInstance().getFacadeManager(FacadeCommonManager.class).read(UniqueIdModel.class, pk);
			   if(uniqueIdValue != null){
				   String nextHashValue = getNextTemplateIdStringForShipment(uniqueIdValue);
				   nextTemplateName = templatedateName.substring(0,hashPosition) + nextHashValue + templatedateName.substring(hashPosition+1);
				   ServiceLocalLocator.getInstance().getFacadeManager(FacadeCommonManager.class).update(uniqueIdValue);
			   }
		   }
		   else{
			   nextTemplateName=null;
		   }
	   }catch(Exception ex){
		   *//** added printstacktrace and change nextTemplateName value for fixing the duplicate shipment number issue.
		   By bachan Joseph on 23rd Nov 2011*//*
		   nextTemplateName=null;
		   ex.printStackTrace();
	   }
	   return nextTemplateName;
   }*/



   /*public String getNextTemplateIdStringForShipment(UniqueIdModel uniqueIdValue){
	   if(uniqueIdValue == null)
		   return "";

	   long width = uniqueIdValue.getIdWidth().longValue();
	   long lastId = uniqueIdValue.getLastId().longValue();
	   String templateName = uniqueIdValue.getTemplateName();
	   //SubTask#3511
	   if(templateName != null && !templateName.equals("")) {
			   currentVal = lastId;
	   }

	   if(currentVal == 0){
		   currentVal = lastId;
	   }
	   long nextId =  currentVal + 1;
	   currentVal = nextId;
	   String nextIdStr = String.valueOf(nextId);
	   String prefixStr = "";
	   if(nextIdStr.length() < width ){
		   long less = width - nextIdStr.length();
		   for(int i=0;i<less;i++)
			   prefixStr = prefixStr + "0";
	   }
	   uniqueIdValue.setLastId(new Long(nextId));
	   return (prefixStr + nextIdStr);
   }*/

    public int checkHashPosition(String templateName)
    {
    	templateName = getDateAppended(templateName);
      char[] ch = templateName.toCharArray();
      for(int i=0;i<ch.length;i++)
      {
        if(ch[i] == '#')
          return i;
      }
      return -1;
    }


    public String getNextTemplateIdString(UniqueIdModel uniqueIdValue)
    {
      long width = uniqueIdValue.getIdWidth().longValue();
      long lastId = uniqueIdValue.getLastId().longValue();
      long nextId = lastId + 1;
      String nextIdStr = String.valueOf(nextId);
      String prefixStr = "";
      if(nextIdStr.length() < width )
      {
         long less = width - nextIdStr.length();
         for(int i=0;i<less;i++)
         prefixStr = prefixStr + "0";
      }
      uniqueIdValue.setLastId(new Long(nextId));
      return (prefixStr + nextIdStr);
    }
    /**
     * Method Add to append the date at the system generated Id Template
     * as per ENHT-496 by Liju Paul on 30-11-2010
     * @param templateName
     * @return
     */
    public String getDateAppended(String templateName){
    	String replaceFormat="";
    	String pattern="";
    	boolean dateYYMMDD=templateName.contains("DATE(YYMMDD)");
    	boolean dateYYMM=templateName.contains("DATE(YYMM)");
    	boolean dateYY=templateName.contains("DATE(YY)");
    	boolean dateMMDDYY=templateName.contains("DATE(MMDDYY)");
    	
    	//ENHT-680
    	boolean dateYYYY=templateName.contains("DATE(YYYY)");
    	
    	if(templateName!=null){
    		if(dateYYMMDD==true){
    			//replaceFormat=DateUtil.format(new Date(), "yyMMdd");
    			replaceFormat = new SimpleDateFormat("yyMMdd").format(new Date());
    			pattern="DATE(YYMMDD)";
    			templateName=replace(templateName, pattern, replaceFormat);
    		}else if(dateYYMM == true){
    			//replaceFormat=DateUtil.format(new Date(), "yyMM");
    			replaceFormat = new SimpleDateFormat("yyMM").format(new Date());
    			pattern="DATE(YYMM)";
    			templateName=replace(templateName, pattern, replaceFormat);
    		}else if(dateYY == true){
    			//replaceFormat=DateUtil.format(new Date(), "yy");
    			replaceFormat = new SimpleDateFormat("yy").format(new Date());
    			pattern="DATE(YY)";
    			templateName=replace(templateName, pattern, replaceFormat);
    		}else if(dateMMDDYY == true){
    			//replaceFormat=DateUtil.format(new Date(), "MMddyy");
    			replaceFormat = new SimpleDateFormat("MMddyy").format(new Date());
    			pattern="DATE(MMDDYY)";
    			templateName=replace(templateName, pattern, replaceFormat);
    		}else if(dateYYYY == true){
    			//replaceFormat=DateUtil.format(new Date(), "yyyy");
    			replaceFormat = new SimpleDateFormat("yyyy").format(new Date());
    			pattern="DATE(YYYY)";
    			templateName=replace(templateName, pattern, replaceFormat);
    		}

    	}
    	return templateName;
    }
/**
 * Method add to replace with date Format as per ENHT-496 by Liju Paul on 30-11-2010.
 * @param str
 * @param pattern
 * @param replace
 * @return
 */
    public String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();

        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e+pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }


}
