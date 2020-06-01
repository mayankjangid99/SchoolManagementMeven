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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeUtility;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.school.common.generic.Base64;
import com.school.json.model.ServerLogJsonModel;

@Service
public class ServerLogBusiness 
{
	
	private static Logger LOG = LoggerFactory.getLogger(ServerLogBusiness.class);
	
	private static Path tomcatLogPath = null;
	
	
	
	@SuppressWarnings("deprecation")
	private Path getTomcatLogPath()
	{
		if(tomcatLogPath == null)
		{
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			tomcatLogPath = Paths.get(request.getRealPath("/"));
			tomcatLogPath = Paths.get( tomcatLogPath.getParent().getParent()+"/logs/");
			LOG.info("INFO: Tomcat Log Path:- " + tomcatLogPath);
			return tomcatLogPath;
		}
		else
		{
			return tomcatLogPath;
		}
	}


	
	
	public Map<String, String> serverLogs() 
	{
		Path tomcatLogPath = getTomcatLogPath();
		
		Map<String, String> logInfoMap = new HashMap<String, String>();
		if (tomcatLogPath != null) 
		{
			try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(tomcatLogPath, "school.log")) 
			{
				for (Path path : directoryStream) {
					logInfoMap.put("logName", path.getFileName().toString());
					logInfoMap.put("logPath", Base64.encodeToString(path.toString().getBytes(), true));
					logInfoMap.put("logSize", (Files.size(path) / 1024) + "KB");
					logInfoMap.put("logLastModifiedTime", new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss")
					.format(Files.getLastModifiedTime(path, LinkOption.NOFOLLOW_LINKS).toMillis()));
					
					LOG.info("Log Name: " + path.getFileName().toString());
					LOG.info("Log Path: " + path.toString());
					LOG.info("Modified: " + new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss")
					.format(Files.getLastModifiedTime(path, LinkOption.NOFOLLOW_LINKS).toMillis()));
					/*this.serverLogForm.setTomcatLogPath(Base64.encodeToString(path.toString().getBytes(), true));
					this.serverLogForm.setTomcatLog(path.getFileName().toString());
					this.serverLogForm.setTomcatLogDateMod(new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss")
							.format(Files.getLastModifiedTime(path, LinkOption.NOFOLLOW_LINKS).toMillis()));
					
					this.serverLogForm.setTomcatLogSize((Files.size(path) / 1024) + "KB");*/
				}
			} catch (Exception e) {
				if (LOG.isErrorEnabled()) {
					LOG.error("Error in reading list of files", e);
				}
			}
		} else {
			LOG.error("path Error");
		}
		return logInfoMap;
	}
	
	
	
	
	public void downloadLog(String filePath)
	{
		try {			
			if(filePath==null){
				if (LOG.isWarnEnabled()) {
					LOG.warn("Error: Invalid File Name is specified in URL");
				}
			}else{
				LOG.info("INFO: Server Log downloading....");
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
			if (LOG.isErrorEnabled()) {
				LOG.error("Error: Invalid File Name is specified in URL", e);
			}
		}
	}
	
	
	
	
	public List<ServerLogJsonModel> getTomcatHistoryLogs() {
		List<ServerLogJsonModel> serverHistoryList = new ArrayList<>();
		List<Path> pathList = new ArrayList<>();
		Path tomcatLogPath = getTomcatLogPath();
		LOG.info("--------------------- getTomcatHistoryLogs() ---------------------------");
		LOG.info("tomcatLogPath: " +tomcatLogPath);
		
		if (tomcatLogPath != null) {
			try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(tomcatLogPath)){
				for (Path path : directoryStream) {
					pathList.add(path);
				}
				Collections.sort(pathList, new Comparator<Path>() {
					@Override
					public int compare(Path o1, Path o2) {
						try {
							return Files.getLastModifiedTime(o2).compareTo(Files.getLastModifiedTime(o1));
						} catch (IOException e) {							
							if (LOG.isErrorEnabled()) {
								LOG.error("Error in comparing two files last updated time stamp", e);
							}
							return -1;
						}
					}
				});
				for (Path path : pathList) {
					ServerLogJsonModel serverLogJsonModel = new ServerLogJsonModel();
					serverLogJsonModel.setLogPath(Base64.encodeToString(path.toString().getBytes(), true));
					serverLogJsonModel.setLogName(path.getFileName().toString());
					serverLogJsonModel.setLogLastModifiedTime(new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss")
							.format(Files.getLastModifiedTime(path, LinkOption.NOFOLLOW_LINKS).toMillis()));
					
					serverLogJsonModel.setLogSize((Files.size(path) / 1024) + "KB");
					serverHistoryList.add(serverLogJsonModel);
				}
			} catch (Exception e) {
				if (LOG.isErrorEnabled()) {
					LOG.error("Error in getting tomcat history logs", e);
				}
			}
		}
		return serverHistoryList;
	}
	
	
	
}
