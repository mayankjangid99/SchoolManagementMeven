package com.school.staff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.services.NotesServices;

@Controller
@RequestMapping(value = {"/supadmin", "/admin", "/subadmin", "/staff" })
public class StaffNotesController {

	@Autowired
	private NotesServices services;
	
	@RequestMapping(value = "addNotes", method = RequestMethod.GET)
	public ModelAndView addNotes(){
		return services.addNotes();
	}
	
	public void saveNotes(){
		
	}
}
