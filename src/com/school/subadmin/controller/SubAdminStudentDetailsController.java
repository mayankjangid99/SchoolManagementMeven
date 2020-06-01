package com.school.subadmin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.model.AdmissionDetailsModel;
import com.school.common.model.AdmissionNumberModel;
import com.school.common.model.ParentDetailsModel;
import com.school.common.model.StudentDetailsModel;
import com.school.common.model.UserDetailsModel;
import com.school.common.model.UserModel;
import com.school.common.services.StudentDetailsServices;
import com.school.json.response.StudentDetailsJsonResponse;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin" })
public class SubAdminStudentDetailsController 
{

	@Autowired
	private StudentDetailsServices services;
	
	
	/*@RequestMapping(value = "/searchStudentDetails")
	public ModelAndView searchStudentDetailsPage()
	{
		return services.searchStudentDetailsPage();
	}*/
	
//	Add Student Details Or Add New Admission
	@RequestMapping(value = "/addStudentDetails")
	public ModelAndView addStudentDetails() {
		return services.addStudentDetails();
	}
	
	
//	Get Roll No for Add Student Details Or Add New Admission
	@RequestMapping(value = "getRollForNewAdmission")
	public @ResponseBody String getRollNewAdmission(HttpServletRequest request) {
		
		String admissionNoAndrollNo = services.getRollAndAdmissionNoForNewAdmission(request);
		return admissionNoAndrollNo;
	}
	
	
//	Save Student Details Or Add New Admission
	@RequestMapping(value = "/saveStudentDetails", method = RequestMethod.POST)
	public ModelAndView saveStudentDetails(HttpServletRequest request, @RequestParam CommonsMultipartFile psimage, @ModelAttribute StudentDetailsModel studentDetails,
			@ModelAttribute ParentDetailsModel parentdetails, @ModelAttribute AdmissionNumberModel admissionNumber, @ModelAttribute AdmissionDetailsModel admissionDetails, 
			@ModelAttribute UserModel user, @ModelAttribute UserDetailsModel userDetails) throws Exception {
		ModelAndView model = services.saveStudentDetails(request, psimage, studentDetails, parentdetails, admissionNumber, admissionDetails, user, userDetails);		
		return model;
	}
	
	
	@RequestMapping(value = "/savedStudentDetails", method = RequestMethod.GET)
	public ModelAndView savedStudentDetails() {
		return services.savedStudentDetails();
	}
	
	
//	Result of All Student Details
	/*@RequestMapping(value = "/resultStudentDetails", method = RequestMethod.POST)
	public ModelAndView resultStudentDetails()
	{
		return services.resultStudentDetails();
	}*/
	
	
//	Fetch All Student Details on basis of school session, class name, sesction name 
	/*@ResponseBody
	@RequestMapping(value = "/fetchAllStudentDetails")
	public StudentDetailsJsonResponse fetchAllStudentsList(HttpServletRequest request)
	{
		StudentDetailsJsonResponse studentDetailsJsonResponse = services.fetchAllStudentsList(request);
		return studentDetailsJsonResponse;
	}*/
	
	
//	Change Student Status in Grid view@ResponseBody
	@ResponseBody
	@RequestMapping(value = "/changeStudentStatus")
	public String changeStudentStatus(HttpServletRequest request) {
		return services.changeStudentStatus(request);
	}
	
	
//	Edit Student Details
	@RequestMapping(value = "/editStudentDetails", method = RequestMethod.POST)
	public ModelAndView editStudentDetails(HttpServletRequest request) {
		ModelAndView model = services.editStudentDetails(request);
		return model;
	}
	
	
//	Update Student Details
	@RequestMapping(value = "/updateStudentDetails", method = RequestMethod.POST)
	public ModelAndView updateStudentDetails(HttpServletRequest request, @RequestParam CommonsMultipartFile psimage, @ModelAttribute StudentDetailsModel studentDetails, @ModelAttribute UserModel user,
			@ModelAttribute ParentDetailsModel parentdetails, @ModelAttribute AdmissionNumberModel admissionNumber, @ModelAttribute AdmissionDetailsModel admissionDetails) throws Exception {
		ModelAndView model = services.updateStudentDetails(request, psimage, studentDetails, parentdetails, admissionNumber, admissionDetails, user);		
		return model;
	}
	
	
	@RequestMapping(value = "/updatedStudentDetails", method = RequestMethod.GET)
	public ModelAndView updatedStudentDetails() {
		return services.updatedStudentDetails();
	}
	
//	View Student Details
	/*@RequestMapping(value = "/viewStudentDetails", method = RequestMethod.POST)
	public ModelAndView viewStudentDetails(HttpServletRequest request)
	{
		ModelAndView model = services.viewStudentDetails(request);
		return model;
	}*/
	
	/*@ResponseBody
	@RequestMapping(value="/getStudentsDetailsInAutocomplete", method = RequestMethod.GET)
	public JSONArray getStudentsDetailsInAutocomplete(HttpServletRequest request, @ModelAttribute ClassInformationModel classInformation, @ModelAttribute SectionInformationModel sectionInformation)
	{
		JSONArray jsonResponse = services.getStudentsDetailsInAutocomplete(request, classInformation, sectionInformation);
		return jsonResponse;
	}*/
	
	
	
	@RequestMapping(value = "resultPromoteStudentDetails", method = RequestMethod.POST)
	public ModelAndView resultPromoteStudentDetails(HttpServletRequest request) {
		return services.resultPromoteStudentDetails(request);
	}
	

	@ResponseBody
	@RequestMapping(value = "/fetchPromoteStudentsDetails", method = RequestMethod.GET)
	public StudentDetailsJsonResponse fetchPromoteStudentsDetails(HttpServletRequest request) {
		return services.fetchPromoteStudentsDetails(request);
	}
	
	@RequestMapping(value = "/updatePromoteStudentDetails", method = RequestMethod.POST)
	public ModelAndView updatePromoteStudentDetails(HttpServletRequest request) {
		return services.updatePromoteStudentDetails(request);
	}
}
