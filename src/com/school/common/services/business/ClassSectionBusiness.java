package com.school.common.services.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.school.common.model.ClassInformationModel;
import com.school.common.model.ClassSectionModel;
import com.school.common.model.SectionInformationModel;
import com.school.json.model.ClassSectionJsonModel;
import com.school.json.response.ClassSectionJsonResponse;

@Service
public class ClassSectionBusiness 
{
	
//	Comparator for sort section Name
	private static Comparator<SectionInformationModel> comparator = new Comparator<SectionInformationModel>() {
		
		@Override
		public int compare(SectionInformationModel arg0, SectionInformationModel arg1) {
			// TODO Auto-generated method stub
			String sectionName1=arg0.getSectionName();
			String sectionName2=arg1.getSectionName();
			return sectionName1.compareTo(sectionName2);
		}
	};
	
	
	
//	Get Class List
	public ClassSectionJsonResponse getClassList(List<ClassInformationModel> classInformationsList) {

		ClassSectionJsonResponse jsonResponse = new ClassSectionJsonResponse();
		List<ClassSectionJsonModel> classSectionModels = new ArrayList<ClassSectionJsonModel>();
		ClassSectionJsonModel jsonModel=null;
		Iterator<ClassInformationModel> iterator = classInformationsList.iterator();
		while (iterator.hasNext()) 
		{
			ClassInformationModel classInformation = (ClassInformationModel) iterator.next();
			jsonModel=new ClassSectionJsonModel();
			jsonModel.setClassCode(classInformation.getClassCode());
			jsonModel.setClassName(classInformation.getClassName());
			classSectionModels.add(jsonModel);
		}
		jsonResponse.setClassSectionModels(classSectionModels);
		return jsonResponse;
	}
	
	
	/*public Map<String, List<ClassSectionJsonModel>> fetchSectionInfoByClassForAllocateToClass(ClassInformationModel classInformationModel, List<SectionInformationModel> sectionInformationList)
	{
		List<ClassSectionJsonModel> unAllocatedClassSectionJsonList = new ArrayList<ClassSectionJsonModel>();
		List<ClassSectionJsonModel> allocatedClassSectionJsonList = new ArrayList<ClassSectionJsonModel>();
		ClassSectionJsonModel classSectionJsonModel = null;
		Map<String, List<ClassSectionJsonModel>> dataMap = new HashMap<String, List<ClassSectionJsonModel>>();
		Set<SectionInformationModel> sectionInformationModels = new HashSet<SectionInformationModel>();
		if(classInformationModel.getClassSections().size() != 0)
		{
			for(ClassSectionModel classSectionModel : classInformationModel.getClassSections())
			{
				sectionInformationModels.add(classSectionModel.getSectionInformation());
			}
		}
		if(sectionInformationModels.size() == 0)
		{
			Iterator<SectionInformationModel> allSectionIterator = sectionInformationList.iterator();
			while (allSectionIterator.hasNext()) 
			{
				SectionInformationModel allSectionInformationModel = (SectionInformationModel) allSectionIterator.next();
				classSectionJsonModel = new ClassSectionJsonModel();
				//System.out.println("class have all section"+allSectionInformationModel.getSectionCode());
				classSectionJsonModel.setSectionCode(allSectionInformationModel.getSectionCode());
				classSectionJsonModel.setSectionName(allSectionInformationModel.getSectionName());
				unAllocatedClassSectionJsonList.add(classSectionJsonModel);					
			}
		}
		else
		{
			Iterator<SectionInformationModel> allSectionIterator = sectionInformationList.iterator();
			while (allSectionIterator.hasNext()) 
			{
				boolean checker = false;
				SectionInformationModel allSectionInformationModel = (SectionInformationModel) allSectionIterator.next();
				
				Iterator<SectionInformationModel> sectionIterator = sectionInformationModels.iterator();
				while (sectionIterator.hasNext()) 
				{
					SectionInformationModel sectionInformationModel = (SectionInformationModel) sectionIterator.next();
					classSectionJsonModel = new ClassSectionJsonModel();
					if(sectionInformationModel.getSectionCode().equalsIgnoreCase(allSectionInformationModel.getSectionCode()))
					{
						//System.out.println("class have this section: "+allSectionInformationModel.getSectionCode());
						classSectionJsonModel.setSectionCode(allSectionInformationModel.getSectionCode());
						classSectionJsonModel.setSectionName(allSectionInformationModel.getSectionName());
						allocatedClassSectionJsonList.add(classSectionJsonModel);
						checker = true;
						break;
					}
				}
				if(!checker)
				{
					classSectionJsonModel.setSectionCode(allSectionInformationModel.getSectionCode());
					classSectionJsonModel.setSectionName(allSectionInformationModel.getSectionName());
					unAllocatedClassSectionJsonList.add(classSectionJsonModel);
				}
				
			}
		}
		dataMap.put("allocated", allocatedClassSectionJsonList);
		dataMap.put("unAllocated", unAllocatedClassSectionJsonList);
		return dataMap;
	}
	*/
	
	
	
	public Map<String, List<ClassSectionJsonModel>> fetchSectionInfoByClassForAllocateToClass(List<ClassSectionModel> classSectionList, List<SectionInformationModel> sectionInformationList)
	{
		List<ClassSectionJsonModel> unAllocatedClassSectionJsonList = new ArrayList<ClassSectionJsonModel>();
		List<ClassSectionJsonModel> allocatedClassSectionJsonList = new ArrayList<ClassSectionJsonModel>();
		ClassSectionJsonModel classSectionJsonModel = null;
		Map<String, List<ClassSectionJsonModel>> dataMap = new HashMap<String, List<ClassSectionJsonModel>>();
		Set<SectionInformationModel> sectionInformationModels = new HashSet<SectionInformationModel>();
		if(classSectionList.size() != 0)
		{
			for(ClassSectionModel classSectionModel : classSectionList)
			{
				sectionInformationModels.add(classSectionModel.getSectionInformation());
			}
		}
		if(sectionInformationModels.size() == 0)
		{
			Iterator<SectionInformationModel> allSectionIterator = sectionInformationList.iterator();
			while (allSectionIterator.hasNext()) 
			{
				SectionInformationModel allSectionInformationModel = (SectionInformationModel) allSectionIterator.next();
				classSectionJsonModel = new ClassSectionJsonModel();
				//System.out.println("class have all section"+allSectionInformationModel.getSectionCode());
				classSectionJsonModel.setSectionCode(allSectionInformationModel.getSectionCode());
				classSectionJsonModel.setSectionName(allSectionInformationModel.getSectionName());
				unAllocatedClassSectionJsonList.add(classSectionJsonModel);					
			}
		}
		else
		{
			Iterator<SectionInformationModel> allSectionIterator = sectionInformationList.iterator();
			while (allSectionIterator.hasNext()) 
			{
				boolean checker = false;
				SectionInformationModel allSectionInformationModel = (SectionInformationModel) allSectionIterator.next();
				
				Iterator<SectionInformationModel> sectionIterator = sectionInformationModels.iterator();
				while (sectionIterator.hasNext()) 
				{
					SectionInformationModel sectionInformationModel = (SectionInformationModel) sectionIterator.next();
					classSectionJsonModel = new ClassSectionJsonModel();
					if(sectionInformationModel.getSectionCode().equalsIgnoreCase(allSectionInformationModel.getSectionCode()))
					{
						//System.out.println("class have this section: "+allSectionInformationModel.getSectionCode());
						classSectionJsonModel.setSectionCode(allSectionInformationModel.getSectionCode());
						classSectionJsonModel.setSectionName(allSectionInformationModel.getSectionName());
						allocatedClassSectionJsonList.add(classSectionJsonModel);
						checker = true;
						break;
					}
				}
				if(!checker)
				{
					classSectionJsonModel.setSectionCode(allSectionInformationModel.getSectionCode());
					classSectionJsonModel.setSectionName(allSectionInformationModel.getSectionName());
					unAllocatedClassSectionJsonList.add(classSectionJsonModel);
				}
				
			}
		}
		dataMap.put("allocated", allocatedClassSectionJsonList);
		dataMap.put("unAllocated", unAllocatedClassSectionJsonList);
		return dataMap;
	}
	
	
	
	
	
//	fetch Class Section 
	/*public ClassSectionJsonResponse fetchClassSection(List<ClassInformationModel> classInformationsList, SectionInformationModel sectionInformationModel) {

		ClassSectionJsonResponse jsonResponse = new ClassSectionJsonResponse();
		List<ClassSectionJsonModel> classSectionModels = new ArrayList<ClassSectionJsonModel>();
		ClassSectionJsonModel jsonModel=null;
		Iterator<ClassInformationModel> iterator = classInformationsList.iterator();
		while (iterator.hasNext()) 
		{
			ClassInformationModel classInformation = (ClassInformationModel) iterator.next();
			List<SectionInformationModel> sectionInformations = new ArrayList<SectionInformationModel>();
			if(classInformation.getClassSections().size() != 0)
			{
				for(ClassSectionModel classSectionModel : classInformation.getClassSections())
				{
					sectionInformations.add(classSectionModel.getSectionInformation());
				}
			}
			Collections.sort(sectionInformations, comparator);
			Iterator<SectionInformationModel> iterator2 = sectionInformations.iterator();
			while (iterator2.hasNext()) 
			{
				SectionInformationModel sectionInformation = (SectionInformationModel) iterator2.next();
				if(sectionInformation.getSectionCode().equalsIgnoreCase(sectionInformationModel.getSectionCode()) 
						|| (sectionInformationModel.getSectionCode() == null || "".equals(sectionInformationModel.getSectionCode())))
				{					
					jsonModel=new ClassSectionJsonModel();
					jsonModel.setClassCode(classInformation.getClassCode());
					jsonModel.setClassName(classInformation.getClassName());
					jsonModel.setSectionCode(sectionInformation.getSectionCode());
					jsonModel.setSectionName(sectionInformation.getSectionName());
					classSectionModels.add(jsonModel);
				}
			}
		}
		jsonResponse.setClassSectionModels(classSectionModels);
		return jsonResponse;
	}*/
	
	
//	fetch Class Section 
	public ClassSectionJsonResponse fetchClassSection(List<ClassSectionModel> classSectionModelsList)
	{
		ClassSectionJsonResponse jsonResponse = new ClassSectionJsonResponse();
		List<ClassSectionJsonModel> classSectionModels = new ArrayList<ClassSectionJsonModel>();
		ClassSectionJsonModel jsonModel = null;
		Iterator<ClassSectionModel> iterator = classSectionModelsList.iterator();
		while (iterator.hasNext()) {
			ClassSectionModel classSectionModel = (ClassSectionModel) iterator.next();
			jsonModel = new ClassSectionJsonModel();
			jsonModel.setSchoolCode(classSectionModel.getSchoolProfile().getSchoolCode());
			jsonModel.setSchoolName(classSectionModel.getSchoolProfile().getName());
			jsonModel.setClassCode(classSectionModel.getClassInformation().getClassCode());
			jsonModel.setClassName(classSectionModel.getClassInformation().getClassName());
			jsonModel.setSectionCode(classSectionModel.getSectionInformation().getSectionCode());
			jsonModel.setSectionName(classSectionModel.getSectionInformation().getSectionName());
			classSectionModels.add(jsonModel);
		}
		jsonResponse.setClassSectionModels(classSectionModels);
		return jsonResponse;
	}
	
	
	
//	Get Section List
	public ClassSectionJsonResponse getSectionList(List<SectionInformationModel> sectionInformationsList) {
		ClassSectionJsonResponse jsonResponse = new ClassSectionJsonResponse();
		List<ClassSectionJsonModel> classSectionModelsList = new ArrayList<ClassSectionJsonModel>();
		ClassSectionJsonModel jsonModel=null;
		
			
//			compare state name to sort
			//Collections.sort(sectionInformationsList, comparator);
			Iterator<SectionInformationModel> iterator = sectionInformationsList.iterator();
			while (iterator.hasNext()) 
			{
				SectionInformationModel sectionInformation = (SectionInformationModel) iterator.next();
				jsonModel = new ClassSectionJsonModel();
				jsonModel.setSectionCode(sectionInformation.getSectionCode());
				jsonModel.setSectionName(sectionInformation.getSectionName());
				classSectionModelsList.add(jsonModel);
			}

		jsonResponse.setClassSectionModels(classSectionModelsList);
		return jsonResponse;
	}
	
	
	public boolean validateClassSection(List<ClassInformationModel> classInformationList, ClassInformationModel classInformation, SectionInformationModel sectionInformation)
	{
		boolean checker = true;
		Iterator<ClassInformationModel> iterator = classInformationList.iterator();
		while (iterator.hasNext()) 
		{
			ClassInformationModel classInformationModel = (ClassInformationModel) iterator.next();
			if(!classInformationModel.getClassCode().equalsIgnoreCase(classInformation.getClassCode()))
			{
				continue;
			}
			else
			{
				Set<SectionInformationModel> sectionInformationList = new HashSet<SectionInformationModel>();
				if(classInformationModel.getClassSections().size() != 0)
				{
					for(ClassSectionModel classSectionModel : classInformationModel.getClassSections())
					{
						sectionInformationList.add(classSectionModel.getSectionInformation());
					}
				}
				Iterator<SectionInformationModel> iterator2 = sectionInformationList.iterator();
				while (iterator2.hasNext()) 
				{
					SectionInformationModel sectionInformationModel = (SectionInformationModel) iterator2.next();
					if(sectionInformationModel.getSectionCode().equalsIgnoreCase(sectionInformation.getSectionCode()))
						checker = false;
				}
			}
		}
		return checker;
	}
}
