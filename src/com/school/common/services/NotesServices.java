package com.school.common.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.dao.NotesDao;
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class NotesServices 
{
	@Autowired
	private NotesDao notesDao;
	
	public ModelAndView addNotes(){
		ModelAndView model = new ModelAndView("staff/addNotes");
		return model;
	}
	
	public void saveNotes(){
			
	}
}
