package com.school.common.services.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeUtility;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.school.common.generic.CommonConstaint;
import com.school.common.generic.Base64;
import com.school.common.generic.UploadFilesUtils;
import com.school.json.model.DatabaseBackupJsonModel;

@Service
public class DatabaseBusiness 
{
	@Autowired
	private DataSource dataSource;
	
	private static Logger log = LoggerFactory.getLogger(DatabaseBusiness.class);
	
	private static Path databaseBackupPath = null;

//	String database = request.getParameter("database");
	// your cPanel username and password here - the user has MySQL LOCK TABLE right
	String username = "root";
	String password = "admin";
	// String dumpdir = "./dumps";
	String urlmysql = "jdbc:mysql://localhost:3306/";
	// String urlpsql = "jdbc:postgresql://localhost/template1";
	
	
	
	@SuppressWarnings("unused")
	private Connection getDataSourceConnection()
	{
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	private Connection getJdbcConnection()
	{
		Connection connection = null;
		try 
		{ 
//		    Class.forName("org.postgresql.Driver").newInstance(); 
		    Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		    connection = DriverManager.getConnection(urlmysql, username, password); 
		    return connection;
		} 
		catch(ClassNotFoundException e) 
		{ 
		    log.error("ERROR: Class not found: "+ e.getMessage());
		}
		catch (InstantiationException | IllegalAccessException e) 
		{
			log.error("ERROR: InstantiationException | IllegalAccessException: "+ e.getMessage());
		} 
		catch (SQLException e) 
		{
		   	log.error("ERROR: SQLException " + e.getMessage());
		}
		return null;

	}
	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	private Path getDatabaseBackupPath()
	{
		if(databaseBackupPath == null)
		{
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			databaseBackupPath = Paths.get(request.getRealPath("/"));
			databaseBackupPath = Paths.get( databaseBackupPath + File.separator + CommonConstaint.DATABASE_BACKUP_PATH);
			log.info("INFO: Database Bachup Path - " + databaseBackupPath);
			return databaseBackupPath;
		}
		else
		{
			return databaseBackupPath;
		}
	}
	
	
	
	
	/*private Connection getJdbcShowConnection() throws InstantiationException, IllegalAccessException
	{
		String database = null;
		// your cPanel username and password here - the user has MySQL LOCK TABLE right
		String username = "root";
		String password = "admin";
		// String dumpdir = "./dumps";
		String urlmysql = "jdbc:mysql://localhost:3306/";
		// String urlpsql = "jdbc:postgresql://localhost/template1";

		String dbtype = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet rset = null;
		int result = -1;


		try { 
		    Class.forName("com.mysql.jdbc.Driver").newInstance(); 
//		     Class.forName("org.postgresql.Driver").newInstance(); 
		} catch(ClassNotFoundException e) { 
		    log.error("Class not found: "+ e.getMessage());
		}

		try 
		{
		    connection = DriverManager.getConnection(urlmysql, username, password); 
		    statement = connection.createStatement();
		    rset = statement.executeQuery("SHOW DATABASES");
		    while (rset.next())   
		    {
		    	log.info("DATABASE: "+ rset.getString(1));
		    }
		    rset.close();
		} 
		catch (SQLException e) 
		{
		   	log.error(e.getMessage());
		} 
		finally 
		    {
		        try {
		            if(connection != null) connection.close();
		        } catch(SQLException e) {}
		    }
		return connection;
	}*/
	
	
	
	
	
//	Database List
	public List<String> searchDatabase()
	{
		List<String> databaseList = new ArrayList<String>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try 
		{
		    connection = getJdbcConnection(); 
		    statement = connection.createStatement();
		    resultSet = statement.executeQuery("SHOW DATABASES");
		    while (resultSet.next())   
		    {
		    	log.info("DATABASE: " + resultSet.getString(1));
		    	databaseList.add(resultSet.getString(1));
		    }
		} 
		catch (SQLException e) 
		{
		   	log.error(e.getMessage());
		} 
		finally 
		{
			try 
			{
				if(resultSet != null) 
					resultSet.close();
				if(statement != null) 
					statement.close();
				if(connection != null) 
					connection.close();
			} catch(SQLException e) {
				log.error("ERROR: SQLException " + e.getMessage());
			}
		}
		return databaseList;
	}
	
	
	
	
//	Table List
	public List<String> searchTable(String database)
	{
		List<String> tableList = new ArrayList<String>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try 
		{
		    connection = getJdbcConnection(); 
		    statement = connection.createStatement();
		    resultSet = statement.executeQuery("SHOW TABLES FROM " + database);
		    while (resultSet.next())   
		    {
		    	log.info("Tables: " + resultSet.getString(1));
		    	tableList.add(resultSet.getString(1));
		    }
		} 
		catch (SQLException e) 
		{
		   	log.error(e.getMessage());
		} 
		finally 
		{
			try 
			{
				if(resultSet != null) 
					resultSet.close();
				if(statement != null) 
					statement.close();
				if(connection != null) 
					connection.close();
			} catch(SQLException e) {
				log.error("ERROR: SQLException " + e.getMessage());
			}
		}
		return tableList;
	}
	
	
	
	
	
	
	
	
	
	public String createDatabaseBackup(String database)
	{
		String filename = database + "_db_" + new SimpleDateFormat("E,dd-MMM-yyyy_HH-mm-ss").format(new Date());
		String saveDirectory = getDatabaseBackupPath().toString() + File.separator;
		log.info("INFO: Database Backup Path: " + saveDirectory);
		
		File dir = new File(saveDirectory);
		if(!dir.exists())
		{
			boolean b = dir.mkdirs();
			if(b)
				log.info("PATH NOT EXIST SO CREATED: " + dir.getPath());
			else
				log.info("PATH NOT CREATED: " + dir.getPath());
		}
		
//		String executeCmd = "mysqldump -u " + username + " -p" + password + " --add-drop-database -B " + database + " -r d:/" + database + ".sql"; //working
		/*String executeCmd = "mysqldump -u " + username + " -p" + password + " -B " + database + " -r d:/" + database + ".sql";*/ //working
//		String executeCmd2 = "mysqldump -u " + username + " -p" + password + " -B " + database + " --tables layout -r d:/layout.sql";	//table backup
		String executeCmd = "mysqldump -u " + username + " -p" + password + " -B " + database + " -r " + saveDirectory + filename + ".sql";
		            
		Process runtimeProcess;
		try {
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
		    int processComplete = runtimeProcess.waitFor();
		    if (processComplete == 0) {
		    	log.info("INFO: Database Backup created successfully...");
		       	return saveDirectory + filename + ".sql";
		    } else {
		       	log.error("ERROR: Database backup could not created !!!");
		       	return null;
		    }
		} catch (Exception ex) {
		  	log.error("ERROR: Exception " + ex.getMessage());
		}
		return null;
	}
	
	
	
	public String createTableBackup(String database, String table)
	{
		String filename = table + "_tb_" + new SimpleDateFormat("E,dd-MMM-yyyy_HH-mm-ss").format(new Date());
		String saveDirectory = getDatabaseBackupPath().toString() + File.separator;
		log.info("INFO: Table Backup Path: " + saveDirectory);
		
		File dir = new File(saveDirectory);
		if(!dir.exists())
		{
			boolean b = dir.mkdirs();
			if(b)
				log.info("PATH NOT EXIST SO CREATED: " + dir.getPath());
			else
				log.info("PATH NOT CREATED: " + dir.getPath());
		}
		
//      String executeCmd = "mysqldump -u " + username + " -p" + password + " --add-drop-database -B " + database + " -r d:/" + database + ".sql"; //working
//     	String executeCmd = "mysqldump -u " + username + " -p" + password + " -B " + database + " -r d:/" + database + ".sql";	//database bachup
		String executeCmd = "mysqldump -u " + username + " -p" + password + " -B " + database + " --tables " + table + " -r " + saveDirectory + filename + ".sql";	//table backup
    
	    Process runtimeProcess;
	    try {
	        runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	        int processComplete = runtimeProcess.waitFor();
	        if (processComplete == 0) {
		    	log.info("INFO: Table Backup created successfully...");
		       	return saveDirectory + filename + ".sql";
		    } else {
		       	log.error("ERROR: Table backup could not created !!!");
		       	return null;
		    }
	    } catch (Exception ex) {
	    	log.error("ERROR: Exception " + ex.getMessage());
	    }
	    return null;
	}
	
	
	
//	Restore Table
	public boolean restoreTable(String database, CommonsMultipartFile file)
	{

		try {
			File tempFile = UploadFilesUtils.uploadTempFileOnServer(file, CommonConstaint.UPLOAD_TEMP_PATH);
			log.info("INFO: Temp path uploaded file: "+tempFile.getPath());
			
	//     	String[] executeCmd = new String[]{"mysql", "--user=" + username, "--password=" + password, "-e", " source d:/"+ FileName + ".sql"};	Restore Database
			/*String[] executeCmd = new String[]{"mysql", "--user=" + username, "--password=" + password, database, "-e", " source d:/" + fileName + ".sql"};*/	// restore table
			String[] executeCmd = new String[]{"mysql", "--user=" + username, "--password=" + password, database, "-e", " source " + tempFile.getPath()};	// restore table
	    
		    Process runtimeProcess;
	    
	        runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	        int processComplete = runtimeProcess.waitFor();
	        if (processComplete == 0) {
		    	log.info("INFO: Table Restore created successfully...");
			 	tempFile.delete();
		       	return true;
		    } else {
		       	log.error("ERROR: Table Restore could not created !!!");
		       	return false;
		    }
	    } catch (Exception ex) {
	    	log.error("ERROR: Exception " + ex.getMessage());
	    }
	    return false;
	}
	
	
//	Restore Database
	public boolean restoreDatabase(CommonsMultipartFile file)
	{		
		try {
			File tempFile = UploadFilesUtils.uploadTempFileOnServer(file, CommonConstaint.UPLOAD_TEMP_PATH);
			log.info("INFO: Temp path uploaded file: "+tempFile.getPath());
			
			/*String[] executeCmd = new String[]{"mysql", "--user=" + username, "--password=" + password, "-e", " source d:/"+ fileName + ".sql"};*/ //working from static path
//			String[] executeCmd2 = new String[]{"mysql", "--user=" + username, "--password=" + password, "uniteddb", "-e", " source d:/layout.sql"};	// restore table
			String[] executeCmd = new String[]{"mysql", "--user=" + username, "--password=" + password, "-e", " source "+ tempFile.getPath()};
	    
		    Process runtimeProcess;
		    
		    runtimeProcess = Runtime.getRuntime().exec(executeCmd);
		    int processComplete = runtimeProcess.waitFor();
		    if (processComplete == 0) {
			  	log.info("INFO: Database Restore created successfully...");
			 	tempFile.delete();
			   	return true;
			} else {
			   	log.error("ERROR: Database Restore could not created !!!");
			   	return false;
			}
		   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return false;
	}
	
	
	public void downloadBackupDirect(String database, String table)
	{
		String filePath = null;
		if(database != null && table == null)
			filePath = createDatabaseBackup(database);
		else if(database != null && table != null)
			filePath = createTableBackup(database, table);
		try {			
			if(filePath == null){
				if (log.isWarnEnabled()) {
					log.warn("Error: Invalid File Name is specified in URL");
				}
			}else{
				log.info("INFO: Backup downloading....");
				File file = new File(filePath);
				InputStream inStrm = new FileInputStream(file);
				String fileName = filePath.substring(filePath.lastIndexOf('\\') + 1);
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
				HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
				final String agent = request.getHeader("USER-AGENT");
				if (agent != null && agent.indexOf("MSIE") != -1) {//For IE
					fileName = URLEncoder.encode(fileName, "UTF8");
					response.setContentType("application/x-download");
					response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
				} else if (agent != null && agent.indexOf("Mozilla") != -1) {//For FireFox, Chrome, Safari
					response.setCharacterEncoding("UTF-8");
					fileName = MimeUtility.encodeText(fileName, "UTF8", "B");
					response.setContentType("application/force-download");
					response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				}
				final ServletOutputStream out = response.getOutputStream();
				final byte bytes[] = new byte[32768];
				int index = inStrm.read(bytes, 0, 32768);
				while (index != -1) {
					out.write(bytes, 0, index);
					index = inStrm.read(bytes, 0, 32768);
				}
				inStrm.close();
				out.flush();
				file.delete();
			}
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("Error: Invalid File Name is specified in URL", e);
			}
		}
	}
	
	
	
	
	public void downloadDatabaseBackup(String filePath)
	{
		try {			
			if(filePath == null){
				if (log.isWarnEnabled()) {
					log.warn("Error: Invalid File Name is specified in URL");
				}
			}else{
				log.info("INFO: Backup downloading....");
				File file = new File(filePath);
				InputStream inStrm = new FileInputStream(file);
				String fileName = filePath.substring(filePath.lastIndexOf('\\') + 1);
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
				HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
				final String agent = request.getHeader("USER-AGENT");
				if (agent != null && agent.indexOf("MSIE") != -1) {//For IE
					fileName = URLEncoder.encode(fileName, "UTF8");
					response.setContentType("application/x-download");
					response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
				} else if (agent != null && agent.indexOf("Mozilla") != -1) {//For FireFox, Chrome, Safari
					response.setCharacterEncoding("UTF-8");
					fileName = MimeUtility.encodeText(fileName, "UTF8", "B");
					response.setContentType("application/force-download");
					response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				}
				final ServletOutputStream out = response.getOutputStream();
				final byte bytes[] = new byte[32768];
				int index = inStrm.read(bytes, 0, 32768);
				while (index != -1) {
					out.write(bytes, 0, index);
					index = inStrm.read(bytes, 0, 32768);
				}
				inStrm.close();
				out.flush();
			}
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("Error: Invalid File Name is specified in URL", e);
			}
		}
	}
	
	
	
	public List<DatabaseBackupJsonModel> getDatabaseBackups() {
		List<DatabaseBackupJsonModel> databaseBackupList = new ArrayList<>();
		List<Path> pathList = new ArrayList<>();
		Path databaseBackup = getDatabaseBackupPath();
		if (databaseBackup != null) {
			try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(databaseBackup)){
				for (Path path : directoryStream) {
					pathList.add(path);
				}
				Collections.sort(pathList, new Comparator<Path>() {
					@Override
					public int compare(Path o1, Path o2) {
						try {
							return Files.getLastModifiedTime(o2).compareTo(Files.getLastModifiedTime(o1));
						} catch (IOException e) {							
							if (log.isErrorEnabled()) {
								log.error("Error in comparing two files last updated time stamp", e);
							}
							return -1;
						}
					}
				});
				for (Path path : pathList) {
					DatabaseBackupJsonModel databaseBackJsonModel = new DatabaseBackupJsonModel();
					databaseBackJsonModel.setBackupPath(Base64.encodeToString(path.toString().getBytes(), true));
					databaseBackJsonModel.setBackupName(path.getFileName().toString());
					databaseBackJsonModel.setBackupLastModifiedTime(new SimpleDateFormat("E,dd MMM yyyy HH:mm:ss")
							.format(Files.getLastModifiedTime(path, LinkOption.NOFOLLOW_LINKS).toMillis()));
					
					databaseBackJsonModel.setBackupSize((Files.size(path) / 1024) + "KB");
					databaseBackupList.add(databaseBackJsonModel);
				}
			} catch (Exception e) {
				if (log.isErrorEnabled()) {
					log.error("Error in getting Backup files: ", e);
				}
			}
		}
		return databaseBackupList;
	}
	
	
	public boolean deleteDatabaseBackup(String path)
	{
		File file = new File(path);
		if(file.exists()) {
			file.delete();
			log.info("INFO : File successfully deleted.");
		} else {
			log.error("ERROR : File does not exist.");
		}
		return false;
	}
	
	
	
	
}
