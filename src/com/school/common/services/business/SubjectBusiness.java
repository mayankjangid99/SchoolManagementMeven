package com.school.common.services.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.school.common.model.ClassSubjectModel;
import com.school.common.model.SubjectDetailsModel;
import com.school.json.model.ClassSubjectJsonModel;

@Service
public class SubjectBusiness 
{
	
	
	public List<ClassSubjectJsonModel> fetchClassSubject(List<ClassSubjectModel> allClassSubjectList)
	{
		List<ClassSubjectJsonModel> jsonModelsList = new ArrayList<ClassSubjectJsonModel>();
		ClassSubjectJsonModel classSubjectJsonModel = null;
		if(allClassSubjectList.size() > 0)
		{
			for(ClassSubjectModel classSubjectModel : allClassSubjectList)
			{
				classSubjectJsonModel = new ClassSubjectJsonModel();
				classSubjectJsonModel.setClassCode(classSubjectModel.getClassInformation().getClassCode());
				classSubjectJsonModel.setClassName(classSubjectModel.getClassInformation().getClassName());
				classSubjectJsonModel.setSectionCode(classSubjectModel.getSectionInformation().getSectionCode());
				classSubjectJsonModel.setSectionName(classSubjectModel.getSectionInformation().getSectionName());
				classSubjectJsonModel.setSchoolCode(classSubjectModel.getSchoolProfile().getSchoolCode());
				classSubjectJsonModel.setSchoolName(classSubjectModel.getSchoolProfile().getName());
				classSubjectJsonModel.setSubjectCode(classSubjectModel.getSubjectDetails().getSubjectCode());
				classSubjectJsonModel.setSubjectName(classSubjectModel.getSubjectDetails().getSubjectName());
				classSubjectJsonModel.setType(classSubjectModel.getType());
				jsonModelsList.add(classSubjectJsonModel);
			}
		}
		return jsonModelsList;
	}
	
	
	public Map<String, List<ClassSubjectJsonModel>> fetchSectionInfoByClassForAllocateToClass(List<ClassSubjectModel> classSubjectList, List<SubjectDetailsModel> subjectDetailsList)
	{
		List<ClassSubjectJsonModel> unAllocatedClassSubjectJsonList = new ArrayList<ClassSubjectJsonModel>();
		List<ClassSubjectJsonModel> allocatedClassSubjectJsonList = new ArrayList<ClassSubjectJsonModel>();
		ClassSubjectJsonModel classSubjectJsonModel = null;
		Map<String, List<ClassSubjectJsonModel>> dataMap = new HashMap<String, List<ClassSubjectJsonModel>>();
		Set<SubjectDetailsModel> subjectDetailsModels = new HashSet<SubjectDetailsModel>();
		if(classSubjectList.size() != 0)
		{
			for(ClassSubjectModel classSubjectModel : classSubjectList)
			{
				subjectDetailsModels.add(classSubjectModel.getSubjectDetails());
			}
		}
		if(subjectDetailsModels.size() == 0)
		{
			Iterator<SubjectDetailsModel> allSubjectIterator = subjectDetailsList.iterator();
			while (allSubjectIterator.hasNext()) 
			{
				SubjectDetailsModel allSubjectDetailsModel = (SubjectDetailsModel) allSubjectIterator.next();
				classSubjectJsonModel = new ClassSubjectJsonModel();
				//System.out.println("class have all section"+allSectionInformationModel.getSectionCode());
				classSubjectJsonModel.setSubjectCode(allSubjectDetailsModel.getSubjectCode());
				classSubjectJsonModel.setSubjectName(allSubjectDetailsModel.getSubjectName());
				if(classSubjectList != null && !classSubjectList.isEmpty()) {
					for(ClassSubjectModel classSubjectModel : classSubjectList) {
						if(classSubjectModel.getSubjectDetails() != null && classSubjectModel.getSubjectDetails().getSubjectCode().equals(allSubjectDetailsModel.getSubjectCode()))
						classSubjectJsonModel.setType(classSubjectModel.getType());
					}
				}
				unAllocatedClassSubjectJsonList.add(classSubjectJsonModel);					
			}
		}
		else
		{
			Iterator<SubjectDetailsModel> allSubjectcIterator = subjectDetailsList.iterator();
			while (allSubjectcIterator.hasNext())
			{
				boolean checker = false;
				SubjectDetailsModel allSubjectDetailsModel = (SubjectDetailsModel) allSubjectcIterator.next();
				
				Iterator<SubjectDetailsModel> subjectIterator = subjectDetailsModels.iterator();
				while (subjectIterator.hasNext()) 
				{
					SubjectDetailsModel subjectDetailsModel = (SubjectDetailsModel) subjectIterator.next();
					classSubjectJsonModel = new ClassSubjectJsonModel();
					if(subjectDetailsModel.getSubjectCode().equalsIgnoreCase(allSubjectDetailsModel.getSubjectCode()))
					{
						//System.out.println("class have this section: "+allSectionInformationModel.getSectionCode());
						classSubjectJsonModel.setSubjectCode(allSubjectDetailsModel.getSubjectCode());
						classSubjectJsonModel.setSubjectName(allSubjectDetailsModel.getSubjectName());
						if(classSubjectList != null && !classSubjectList.isEmpty()) {
							for(ClassSubjectModel classSubjectModel : classSubjectList) {
								if(classSubjectModel.getSubjectDetails() != null && classSubjectModel.getSubjectDetails().getSubjectCode().equals(allSubjectDetailsModel.getSubjectCode()))
								classSubjectJsonModel.setType(classSubjectModel.getType());
							}
						}
						allocatedClassSubjectJsonList.add(classSubjectJsonModel);
						checker = true;
						break;
					}
				}
				if(!checker)
				{
					classSubjectJsonModel.setSubjectCode(allSubjectDetailsModel.getSubjectCode());
					classSubjectJsonModel.setSubjectName(allSubjectDetailsModel.getSubjectName());
					unAllocatedClassSubjectJsonList.add(classSubjectJsonModel);
				}
				
			}
		}
		dataMap.put("allocated", allocatedClassSubjectJsonList);
		dataMap.put("unAllocated", unAllocatedClassSubjectJsonList);
		return dataMap;
	}
	
}
