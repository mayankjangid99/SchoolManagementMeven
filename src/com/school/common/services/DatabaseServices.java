package com.school.common.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.generic.Base64;
import com.school.common.services.business.DatabaseBusiness;
import com.school.json.model.DatabaseBackupJsonModel;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DatabaseServices 
{
	@Autowired
	private DatabaseBusiness databaseBusiness;
	
	public ModelAndView searchDatabase()
	{
		ModelAndView model = new ModelAndView("admin/searchDatabase");
		List<String> databaseList = databaseBusiness.searchDatabase();
		model.addObject("Databases", databaseList);
		return model;
	}
	
	
	
	public ModelAndView createDatabaseBackup(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("admin/searchDatabase");
		String database = request.getParameter("database");
		String backupStatus = databaseBusiness.createDatabaseBackup(database);
		if(backupStatus != null)
		{
			model.setViewName("redirect:createdDatabaseBackup");
			return model;
		}
		else
		{
			model.addObject("ErrorMessage", "Database Backup can not successfully created...!!!");
			List<String> databaseList = databaseBusiness.searchDatabase();
			model.addObject("Databases", databaseList);
			return model;
		}		
	}
	
	
	public ModelAndView createdDatabaseBackup()
	{
		ModelAndView model = new ModelAndView("admin/searchDatabase");
		model.addObject("SuccessMessage", "Database Backup successfully created...!!!");
		List<String> databaseList = databaseBusiness.searchDatabase();
		model.addObject("Databases", databaseList);
		return model;
	}
	
	
	
	public List<String> getTablesByDatabase(HttpServletRequest request)
	{
		String database = request.getParameter("database");
		return databaseBusiness.searchTable(database);
	}
	
	
	public void downloadDatabaseBackup(HttpServletRequest request)
	{
		String filePath = request.getParameter("file");
		if(filePath != null)
			filePath = new String(Base64.decode(filePath));
		databaseBusiness.downloadDatabaseBackup(filePath);
		
	}
	
	public ModelAndView createTableBackup(HttpServletRequest request)
	{
		ModelAndView model = new ModelAndView("admin/searchDatabase");
		String table = request.getParameter("table");
		String database = request.getParameter("database");
		String backupStatus = databaseBusiness.createTableBackup(database, table);
		if(backupStatus != null)
		{
			model.setViewName("redirect:createdTableBackup");
			return model;
		}
		else
		{
			model.addObject("ErrorMessage", "Table Backup can not successfully created...!!!");
			List<String> databaseList = databaseBusiness.searchDatabase();
			model.addObject("Databases", databaseList);
			return model;
		}
		
	}
	
	
	public ModelAndView createdTableBackup()
	{
		ModelAndView model = new ModelAndView("admin/searchDatabase");
		model.addObject("SuccessMessage", "Table Backup successfully created...!!!");
		List<String> databaseList = databaseBusiness.searchDatabase();
		model.addObject("Databases", databaseList);
		return model;
	}
	
	
	public void downloadBackupDirect(HttpServletRequest request)
	{
		String database = request.getParameter("database");
		String table = request.getParameter("table");
		databaseBusiness.downloadBackupDirect(database, table);
	}
	
	
	
	public List<DatabaseBackupJsonModel> fetchDatabaseBackups() {
		return databaseBusiness.getDatabaseBackups();
	}
	
	
	public String deleteDatabaseBackup(HttpServletRequest request)
	{
		String filePath = request.getParameter("file");
		if(filePath != null)
			filePath = new String(Base64.decode(filePath));
		boolean fileStatus = databaseBusiness.deleteDatabaseBackup(filePath);
		if(fileStatus)
			return "success";
		else
			return "fail";
	}
	
	
	public ModelAndView restoreDatabase(CommonsMultipartFile databaseFile)
	{
		ModelAndView model = new ModelAndView("admin/searchDatabase");
		boolean restoreStatus = databaseBusiness.restoreDatabase(databaseFile);
		if(restoreStatus)
		{
			model.setViewName("redirect:restoredDatabase");
			return model;
		}
		else
		{
			model.addObject("ErrorMessage", "Database can not restored successfully ...!!!");
			List<String> databaseList = databaseBusiness.searchDatabase();
			model.addObject("Databases", databaseList);
			return model;
		}
	}
	
	
	
	public ModelAndView restoredDatabase()
	{
		ModelAndView model = new ModelAndView("admin/searchDatabase");
		model.addObject("SuccessMessage", "Database restored successfully ...!!!");
		List<String> databaseList = databaseBusiness.searchDatabase();
		model.addObject("Databases", databaseList);
		return model;
	}
	
	
	public ModelAndView restoreTable(HttpServletRequest request, CommonsMultipartFile databaseFile)
	{
		ModelAndView model = new ModelAndView("admin/searchDatabase");
		String database = request.getParameter("database");
		boolean restoreStatus = databaseBusiness.restoreTable(database, databaseFile);
		if(restoreStatus)
		{
			model.setViewName("redirect:restoredTable");
			return model;
		}
		else
		{
			model.addObject("ErrorMessage", "Table can not restored successfully ...!!!");
			List<String> databaseList = databaseBusiness.searchDatabase();
			model.addObject("Databases", databaseList);
			return model;
		}
	}
	
	
	
	public ModelAndView restoredTable()
	{
		ModelAndView model = new ModelAndView("admin/searchDatabase");
		model.addObject("SuccessMessage", "Table restored successfully ...!!!");
		List<String> databaseList = databaseBusiness.searchDatabase();
		model.addObject("Databases", databaseList);
		return model;
	}
	
	
	
}
