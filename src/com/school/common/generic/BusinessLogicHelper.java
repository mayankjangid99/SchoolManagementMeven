package com.school.common.generic;

import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.school.common.model.AdmissionDetailsModel;
import com.school.common.model.StudentDetailsModel;

public class BusinessLogicHelper 
{	
	private static Logger LOG = LoggerFactory.getLogger(BusinessLogicHelper.class);
	
	public static String getQueryStringFromPost(HttpServletRequest request)
	{
		Map<String, String[]> params = request.getParameterMap();
	    Iterator<String> i = params.keySet().iterator();
	    int arrayIndex = 0;
	    StringBuffer queryString = new StringBuffer("");
	    while ( i.hasNext() )
	    {
	        String key = (String) i.next();
	        String value = ((String[]) params.get( key ))[ 0 ];
	        if(arrayIndex == 0)
	        	queryString = queryString.append(key + "=" + value);
	        else
	        	queryString = queryString.append("&" + key + "=" + value);
	        
	        arrayIndex++;
	    }
	    return queryString.toString();
	}
	
	
	
	public static Map<String, String> parseAndReadConfigProperties(String stringProperties)
	{
		Map<String, String> mailConfigProperties = null;

		if (stringProperties != null && !stringProperties.trim().equals("")) {
			String[] tempServerDetail = stringProperties.trim().split(";");
			if (tempServerDetail != null) {
				mailConfigProperties = new Hashtable<String, String>();
				for (String tempStr : tempServerDetail) {
					String[] property = tempStr.split("=");
					if (property[0] != null && !property[0].trim().equals("")) {
						if (property.length == 1 || property[1] == null || property[1].trim().equals("")) {
							if (LOG.isDebugEnabled()) {
								LOG.debug("The value for configuration paramter {} is empty or null.", property[0]);
							}
							mailConfigProperties.put(property[0].trim(), "");
						} else {
							mailConfigProperties.put(property[0].trim(), property[1].trim());
						}
					}
				} // End of for loop
				if (LOG.isDebugEnabled()) {
					LOG.debug("The configuration values are {}", mailConfigProperties);
				}
			}
		}
		return mailConfigProperties;
	}
	
	public static String getMonthName(int monthCount)
	{ 
		String month = null;
		switch (monthCount){
		case 0:
			month = "Yearly";
			break;
		case 1:
			month = "Jan";
			break;
		case 2:
			month = "Feb";
			break;
		case 3:
			month = "Mar";
			break;
		case 4:
			month = "Apr";
			break;
		case 5:
			month = "May";
			break;
		case 6:
			month = "Jun";
			break;
		case 7:
			month = "Jul";
			break;
		case 8:
			month = "Aug";
			break;
		case 9:
			month = "Sep";
			break;
		case 10:
			month = "Oct";
			break;
		case 11:
			month = "Nov";
			break;
		case 12:
			month = "Dec";
			break;
		}
		return month;
	}
	
	@SuppressWarnings("deprecation")
	public static void createDirectory(String saveDirectory){

		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		Check save directory path
		if(saveDirectory.startsWith("\\"))
			saveDirectory = saveDirectory.substring(1, saveDirectory.length());
		if(saveDirectory.endsWith("\\"))
			saveDirectory = saveDirectory.substring(0, saveDirectory.length()-1);	
		
		//saveDirectory = request.getRealPath("/") + saveDirectory + File.separator + schoolCode + File.separator;
		saveDirectory = request.getRealPath("/") + saveDirectory + File.separator;
		File dir = new File(saveDirectory);
		LOG.info("Upload File Path: " + saveDirectory);
		if(!dir.exists())
		{
			boolean b = dir.mkdirs();
			if(b)
				LOG.info("PATH NOT EXIST SO CREATED: " + dir.getPath());
			else
				LOG.info("PATH NOT CREATED: " + dir.getPath());
		}
	}
	
	public static void removeHQLQueryDataFromSession(HttpSession session) {
		try {
			session.removeAttribute("searched");
			session.removeAttribute("params");
			session.removeAttribute("Query");
			session.removeAttribute("hqlQuery");
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error("ERROR: removeHQLQueryDataFromSession() in BusinessLogicHelper", e);
		}
	}
	
	public static AdmissionDetailsModel getAdmisionDetailsFromStudentDetails(StudentDetailsModel studentDetails, String sessionCode) {
		if(studentDetails != null && studentDetails.getAdmissionDetailses() != null && !studentDetails.getAdmissionDetailses().isEmpty()) {
			return studentDetails.getAdmissionDetailses().stream().filter(ad -> ad.getSessionDetails().getSessionCode().equals(sessionCode)).findAny().orElse(null);
		}
		return null;
		
	}
	
	public static AdmissionDetailsModel getAdmisionDetailsFromStudentDetails(StudentDetailsModel studentDetails) {
		String sessionCode = SessionManagerDataHelper.getSchoolCurrentSession();
		if(studentDetails != null && studentDetails.getAdmissionDetailses() != null && !studentDetails.getAdmissionDetailses().isEmpty()) {
			return studentDetails.getAdmissionDetailses().stream().filter(ad -> ad.getSessionDetails().getSessionCode().equals(sessionCode)).findAny().orElse(null);
		}
		return null;
		
	}
}
