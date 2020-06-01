package com.school.common.services.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.school.common.model.ClassFeeCategoryModel;
import com.school.common.model.FeeCategoryModel;
import com.school.json.model.ClassFeeCategoryJsonModel;
import com.school.json.model.FeeCategoryJsonModel;
import com.school.json.response.FeeCategoryJsonResponse;

@Service
public class FeeCategoryBusiness 
{
	

//	Comparator for sort section Name
	private static Comparator<ClassFeeCategoryModel> comparator = new Comparator<ClassFeeCategoryModel>() {
		
		@Override
		public int compare(ClassFeeCategoryModel arg0, ClassFeeCategoryModel arg1) 
		{
			// TODO Auto-generated method stub
			String className1 = arg0.getClassInformation().getClassName();
			String className2 = arg1.getClassInformation().getClassName();
			return className1.compareTo(className2);
		}
	};
	
//	fetch Fee Category With Class 
	/*public FeeCategoryJsonResponse fetchFeeCategoryWithClass(List<FeeCategoryModel> feeCategoryList, ClassInformationModel classInformationModel) {

		FeeCategoryJsonResponse jsonResponse = new FeeCategoryJsonResponse();
		List<FeeCategoryJsonModel> feeCategoryModels = new ArrayList<FeeCategoryJsonModel>();
		FeeCategoryJsonModel jsonModel=null;
		Iterator<FeeCategoryModel> iterator = feeCategoryList.iterator();
		while (iterator.hasNext()) 
		{
			FeeCategoryModel feeCategory = (FeeCategoryModel) iterator.next();
			List<ClassInformationModel> classInformations = new ArrayList<ClassInformationModel>();
			for(ClassFeeCategoryModel classFeeCategory : feeCategory.getClassFeeCategory())
			{
				classInformations.add(classFeeCategory.getClassInformation());
			}
			Collections.sort(classInformations, comparator);
			Iterator<ClassInformationModel> iterator2 = classInformations.iterator();
			while (iterator2.hasNext()) 
			{
				ClassInformationModel classInformation = (ClassInformationModel) iterator2.next();
				if(classInformation.getClassCode().equalsIgnoreCase(classInformationModel.getClassCode()) 
						|| (classInformationModel.getClassCode() == null || "".equals(classInformationModel.getClassCode())))
				{					
					jsonModel=new FeeCategoryJsonModel();
					jsonModel.setFeeCategoryCode(feeCategory.getFeeCategoryCode());
					jsonModel.setFeeCategoryName(feeCategory.getFeeCategoryName());
					jsonModel.setClassCode(classInformation.getClassCode());
					jsonModel.setClassName(classInformation.getClassName());
					feeCategoryModels.add(jsonModel);
				}
			}
		}
		jsonResponse.setFeeCategories(feeCategoryModels);
		return jsonResponse;
	}
	*/
	
	
	
	public FeeCategoryJsonResponse fetchFeeCategoryWithClass(List<ClassFeeCategoryModel> classFeeCategoryList) {

		FeeCategoryJsonResponse jsonResponse = new FeeCategoryJsonResponse();
		List<FeeCategoryJsonModel> feeCategoryModels = new ArrayList<FeeCategoryJsonModel>();
		FeeCategoryJsonModel feeCategoryJson = null;
		Collections.sort(classFeeCategoryList, comparator);
		for(ClassFeeCategoryModel classFeeCategory : classFeeCategoryList)
		{
			feeCategoryJson = new FeeCategoryJsonModel();
			feeCategoryJson.setSchoolCode(classFeeCategory.getSchoolProfile().getSchoolCode());
			feeCategoryJson.setSchoolName(classFeeCategory.getSchoolProfile().getName());
			feeCategoryJson.setClassCode(classFeeCategory.getClassInformation().getClassCode());
			feeCategoryJson.setClassName(classFeeCategory.getClassInformation().getClassName());
			feeCategoryJson.setSectionCode(classFeeCategory.getSectionInformation().getSectionCode());
			feeCategoryJson.setSectionName(classFeeCategory.getSectionInformation().getSectionName());
			feeCategoryJson.setFeeCategoryCode(classFeeCategory.getFeeCategory().getFeeCategoryCode());
			feeCategoryJson.setFeeCategoryName(classFeeCategory.getFeeCategory().getFeeCategoryName());
			feeCategoryJson.setType(classFeeCategory.getType());
			feeCategoryJson.setFeeType(classFeeCategory.getFeeType());
			feeCategoryJson.setStatus(classFeeCategory.getStatus());
			feeCategoryModels.add(feeCategoryJson);
		}
		jsonResponse.setFeeCategories(feeCategoryModels);
		return jsonResponse;
	}
	
	
	
	
	
	
	
	/*public Map<String, List<FeeCategoryJsonModel>> fetchFeeCategoryByClassForAllocateToClass(ClassInformationModel classInformationModel, List<FeeCategoryModel> feeCategoryList)
	{
		List<FeeCategoryJsonModel> unAllocatedFeeCategoryJsonList = new ArrayList<FeeCategoryJsonModel>();
		List<FeeCategoryJsonModel> allocatedFeeCategoryJsonList = new ArrayList<FeeCategoryJsonModel>();
		FeeCategoryJsonModel feeCategoryJsonModel = null;
		Map<String, List<FeeCategoryJsonModel>> dataMap = new HashMap<String, List<FeeCategoryJsonModel>>();
		
		Set<FeeCategoryModel> feeCategoryModels = new HashSet<FeeCategoryModel>();
		for(ClassFeeCategoryModel classFeeCategory : classInformationModel.getClassFeeCategory())
		{
			feeCategoryModels.add(classFeeCategory.getFeeCategory());
		}
		if(feeCategoryModels.size() == 0)
		{
			Iterator<FeeCategoryModel> allSectionIterator = feeCategoryList.iterator();
			while (allSectionIterator.hasNext()) 
			{
				FeeCategoryModel allFeeCategoryModel = (FeeCategoryModel) allSectionIterator.next();
				feeCategoryJsonModel = new FeeCategoryJsonModel();
				//System.out.println("class have all section"+allSectionInformationModel.getSectionCode());
				feeCategoryJsonModel.setFeeCategoryCode(allFeeCategoryModel.getFeeCategoryCode());
				feeCategoryJsonModel.setFeeCategoryName(allFeeCategoryModel.getFeeCategoryName());
				unAllocatedFeeCategoryJsonList.add(feeCategoryJsonModel);					
			}
		}
		else
		{
			Iterator<FeeCategoryModel> allFeeCategoryIterator = feeCategoryList.iterator();
			while (allFeeCategoryIterator.hasNext()) 
			{
				boolean flag = false;
				FeeCategoryModel allFeeCategoryModel = (FeeCategoryModel) allFeeCategoryIterator.next();
				
				Iterator<FeeCategoryModel> sectionIterator = feeCategoryModels.iterator();
				while (sectionIterator.hasNext()) 
				{
					FeeCategoryModel feeCategoryModel = (FeeCategoryModel) sectionIterator.next();
					feeCategoryJsonModel = new FeeCategoryJsonModel();
					if(feeCategoryModel.getFeeCategoryCode().equalsIgnoreCase(allFeeCategoryModel.getFeeCategoryCode()))
					{
						//System.out.println("class have this section: "+allSectionInformationModel.getSectionCode());
						feeCategoryJsonModel.setFeeCategoryCode(allFeeCategoryModel.getFeeCategoryCode());
						feeCategoryJsonModel.setFeeCategoryName(allFeeCategoryModel.getFeeCategoryName());
						allocatedFeeCategoryJsonList.add(feeCategoryJsonModel);
						flag = true;
						break;
					}
				}
				if(!flag)
				{
					feeCategoryJsonModel.setFeeCategoryCode(allFeeCategoryModel.getFeeCategoryCode());
					feeCategoryJsonModel.setFeeCategoryName(allFeeCategoryModel.getFeeCategoryName());
					unAllocatedFeeCategoryJsonList.add(feeCategoryJsonModel);
				}
					
			}
		}
		dataMap.put("allocated", allocatedFeeCategoryJsonList);
		dataMap.put("unAllocated", unAllocatedFeeCategoryJsonList);
		return dataMap;
	}*/
	
	
	
	
	public Map<String, List<FeeCategoryJsonModel>> fetchFeeCategoryByClassForAllocateToClass(List<ClassFeeCategoryModel> classFeeCategoryList, List<FeeCategoryModel> feeCategoryList)
	{
		List<FeeCategoryJsonModel> unAllocatedFeeCategoryJsonList = new ArrayList<FeeCategoryJsonModel>();
		List<FeeCategoryJsonModel> allocatedFeeCategoryJsonList = new ArrayList<FeeCategoryJsonModel>();
		FeeCategoryJsonModel feeCategoryJsonModel = null;
		Map<String, List<FeeCategoryJsonModel>> dataMap = new HashMap<String, List<FeeCategoryJsonModel>>();
		
		List<FeeCategoryModel> feeCategoryModelsList = new ArrayList<FeeCategoryModel>();
		if(classFeeCategoryList.size() != 0)
		{
			for(ClassFeeCategoryModel classFeeCategory : classFeeCategoryList)
			{
				feeCategoryModelsList.add(classFeeCategory.getFeeCategory());
			}
		}
		if(feeCategoryModelsList.size() == 0)
		{
			Iterator<FeeCategoryModel> allSectionIterator = feeCategoryList.iterator();
			while (allSectionIterator.hasNext()) 
			{
				FeeCategoryModel allFeeCategoryModel = (FeeCategoryModel) allSectionIterator.next();
				feeCategoryJsonModel = new FeeCategoryJsonModel();
				//System.out.println("class have all section"+allSectionInformationModel.getSectionCode());
				feeCategoryJsonModel.setFeeCategoryCode(allFeeCategoryModel.getFeeCategoryCode());
				feeCategoryJsonModel.setFeeCategoryName(allFeeCategoryModel.getFeeCategoryName());
				unAllocatedFeeCategoryJsonList.add(feeCategoryJsonModel);					
			}
		}
		else
		{
			Iterator<FeeCategoryModel> allFeeCategoryIterator = feeCategoryList.iterator();
			while (allFeeCategoryIterator.hasNext()) 
			{
				boolean flag = false;
				FeeCategoryModel allFeeCategoryModel = (FeeCategoryModel) allFeeCategoryIterator.next();
				
				Iterator<FeeCategoryModel> sectionIterator = feeCategoryModelsList.iterator();
				while (sectionIterator.hasNext()) 
				{
					FeeCategoryModel feeCategoryModel = (FeeCategoryModel) sectionIterator.next();
					feeCategoryJsonModel = new FeeCategoryJsonModel();
					if(feeCategoryModel.getFeeCategoryCode().equalsIgnoreCase(allFeeCategoryModel.getFeeCategoryCode()))
					{
						//System.out.println("class have this section: "+allSectionInformationModel.getSectionCode());
						feeCategoryJsonModel.setFeeCategoryCode(allFeeCategoryModel.getFeeCategoryCode());
						feeCategoryJsonModel.setFeeCategoryName(allFeeCategoryModel.getFeeCategoryName());
						allocatedFeeCategoryJsonList.add(feeCategoryJsonModel);
						flag = true;
						break;
					}
				}
				if(!flag)
				{
					feeCategoryJsonModel.setFeeCategoryCode(allFeeCategoryModel.getFeeCategoryCode());
					feeCategoryJsonModel.setFeeCategoryName(allFeeCategoryModel.getFeeCategoryName());
					unAllocatedFeeCategoryJsonList.add(feeCategoryJsonModel);
				}
					
			}
		}
		dataMap.put("allocated", allocatedFeeCategoryJsonList);
		dataMap.put("unAllocated", unAllocatedFeeCategoryJsonList);
		return dataMap;
	}
	
	public ResponseEntity<List<ClassFeeCategoryJsonModel>> getAdditionaFeeCategories(List<ClassFeeCategoryModel> classFeeCategoryList) {
		List<ClassFeeCategoryJsonModel> finalList = new ArrayList<ClassFeeCategoryJsonModel>();
		ClassFeeCategoryJsonModel json = null;
		if(classFeeCategoryList != null) {
			for(ClassFeeCategoryModel model : classFeeCategoryList) {
				json = new ClassFeeCategoryJsonModel();
				json.setAmount(model.getAmount());
				json.setClassCode(model.getClassInformation().getClassCode());
				json.setClassName(model.getClassInformation().getClassName());
				json.setFeeCategoryCode(model.getFeeCategory().getFeeCategoryCode());
				json.setFeeCategoryName(model.getFeeCategory().getFeeCategoryName());
				json.setFeeType(model.getFeeType());
				json.setSchoolCode(model.getSchoolProfile().getSchoolCode());
				json.setSchoolName(model.getSchoolProfile().getName());
				json.setSectionCode(model.getSectionInformation().getSectionCode());
				json.setSectionName(model.getSectionInformation().getSectionName());
				json.setSessionCode(model.getSessionDetails().getSessionCode());
				json.setSessionName(model.getSessionDetails().getSessionName());
				json.setStatus(model.getStatus());
				json.setType(model.getType());
				finalList.add(json);
			}
		}
		return new ResponseEntity<List<ClassFeeCategoryJsonModel>>(finalList, HttpStatus.OK);
	}
	
	
}
