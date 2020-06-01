package com.school.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.school.common.services.DatabaseServices;
import com.school.json.model.DatabaseBackupJsonModel;

@Controller
@RequestMapping(value = {"/admin", "/supadmin"})
public class AdminDatabaseController 
{
	@Autowired
	private DatabaseServices services;
	
	
	@RequestMapping(value = "/searchDatabase")
	public ModelAndView searchDatabase()
	{
		return services.searchDatabase();
	}
	
	
	@RequestMapping(value = "/createDatabaseBackup", method = RequestMethod.POST)
	public ModelAndView createDatabaseBackup(HttpServletRequest request)
	{
		return services.createDatabaseBackup(request);
	}
	
	
	@RequestMapping(value = "/createdDatabaseBackup", method = RequestMethod.GET)
	public ModelAndView createdDatabaseBackup()
	{
		return services.createdDatabaseBackup();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTablesByDatabase", method = RequestMethod.GET)
	public List<String> getTablesByDatabase(HttpServletRequest request)
	{
		return services.getTablesByDatabase(request);
	}
	
	@RequestMapping(value = "/createTableBackup", method = RequestMethod.POST)
	public ModelAndView createTableBackup(HttpServletRequest request)
	{
		return services.createTableBackup(request);
	}
	
	
	@RequestMapping(value = "/createdTableBackup", method = RequestMethod.GET)
	public ModelAndView createdTableBackup()
	{
		return services.createdTableBackup();
	}
	
	
	@RequestMapping(value = "/downloadDatabaseBackup", method = RequestMethod.POST)
	public void downloadDatabaseBackup(HttpServletRequest request)
	{
		services.downloadDatabaseBackup(request);
	}
	
	@RequestMapping(value = "/downloadTableBackupDirect", method = RequestMethod.POST)
	public void downloadTableBackupDirect(HttpServletRequest request)
	{
		services.downloadBackupDirect(request);
	}
	
	
	@RequestMapping(value = "/downloadDatabaseBackupDirect", method = RequestMethod.POST)
	public void downloadDatabaseBackupDirect(HttpServletRequest request)
	{
		services.downloadBackupDirect(request);
	}
	
	
	@RequestMapping(value = "/resultDatabase", method = RequestMethod.POST)
	public String resultDatabase()
	{
		return "admin/resultDatabase";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/fetchDatabaseBackups", method = RequestMethod.GET)
	public List<DatabaseBackupJsonModel> fetchDatabaseBackups() {
		return services.fetchDatabaseBackups();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteDatabaseBackup", method = RequestMethod.POST)
	public String deleteDatabaseBackup(HttpServletRequest request)
	{
		return services.deleteDatabaseBackup(request);
	}
	
	
	@RequestMapping(value = "restoreDatabase", method = RequestMethod.POST)
	public ModelAndView restoreDatabase(@RequestParam CommonsMultipartFile databaseFile)
	{	
		return services.restoreDatabase(databaseFile);
	}
	
	
	@RequestMapping(value = "restoredDatabase", method = RequestMethod.GET)
	public ModelAndView restoredDatabase()
	{
		return services.restoredDatabase();
	}
	
	
	@RequestMapping(value = "restoreTable", method = RequestMethod.POST)
	public ModelAndView restoreTable(HttpServletRequest request, @RequestParam CommonsMultipartFile databaseFile)
	{
		return services.restoreTable(request, databaseFile);
	}
	
	
	@RequestMapping(value = "restoredTable", method = RequestMethod.GET)
	public ModelAndView restoredTable()
	{
		return services.restoredTable();
	}
	
}
