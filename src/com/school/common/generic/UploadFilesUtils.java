package com.school.common.generic;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadFilesUtils 
{
	
	private static Logger LOG = LoggerFactory.getLogger(UploadFilesUtils.class);
	
	@SuppressWarnings("deprecation")
	public static String uploadFileOnServer (String schoolCode, CommonsMultipartFile file, String saveDirectory, String renameFile)
	{
		
		try {
			if(file != null && !"".equals(file.getOriginalFilename()) && saveDirectory != null && !"".equals(saveDirectory))
			{
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
				
//				Check save directory path
				if(saveDirectory.startsWith("\\"))
					saveDirectory = saveDirectory.substring(1, saveDirectory.length());
				if(saveDirectory.endsWith("\\"))
					saveDirectory = saveDirectory.substring(0, saveDirectory.length()-1);	
				
				//saveDirectory = request.getRealPath("/") + saveDirectory + File.separator + schoolCode + File.separator;
				saveDirectory = request.getRealPath("/") + saveDirectory + "\\" + schoolCode + "\\";
				File dir = new File(saveDirectory);
				LOG.info("Upload File Path: " + saveDirectory);
				if(!dir.exists())
				{
					boolean b = dir.mkdirs();
					if(b)
						LOG.info("PATH NOT EXIST SO CREATED: " + dir.getPath());
					else
						LOG.info("PATH NOT CREATED: " + dir.getPath());
				}
				String fileextention = FilenameUtils.getExtension(saveDirectory + file.getOriginalFilename());
				File oldfile = new File(saveDirectory + file.getOriginalFilename());
				File newfile = new File(saveDirectory + renameFile + "." + fileextention);
				if(newfile.exists())
				{
					newfile.delete();
				}
				file.transferTo(new File(saveDirectory + file.getOriginalFilename()));
				oldfile.renameTo(newfile);
				return "/" +schoolCode + "/" + renameFile + "." + fileextention;
			}

			else
			{
				LOG.warn("WARNING: uploadFileOnServer in UploadFilesUtils [File OR Path is Blank]");
				return null;
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public static File uploadTempFileOnServer (CommonsMultipartFile file, String saveDirectory) throws Exception
	{
		
		if(file != null && !"".equals(file.getOriginalFilename()) && saveDirectory != null && !"".equals(saveDirectory))
		{
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

//			Check save directory path
			if(saveDirectory.startsWith("\\"))
				saveDirectory = saveDirectory.substring(1, saveDirectory.length());
			if(saveDirectory.endsWith("\\"))
				saveDirectory = saveDirectory.substring(0, saveDirectory.length()-1);	
			
			//saveDirectory = request.getRealPath("/") + saveDirectory + File.separator + schoolCode + File.separator;
			saveDirectory = request.getRealPath("/") + saveDirectory + File.separator;
			File dir = new File(saveDirectory);
			LOG.info("Upload Temp File Path: " + saveDirectory);
			if(!dir.exists())
			{
				boolean b = dir.mkdirs();
				if(b)
					LOG.info("PATH NOT EXIST SO CREATED: " + dir.getPath());
				else
					LOG.info("PATH NOT CREATED: " + dir.getPath());
			}
			File tempFile = new File(saveDirectory + file.getOriginalFilename());
			if(tempFile.exists())
			{
				tempFile.delete();
			}
			file.transferTo(tempFile);	
			return tempFile;
		}

		else
		{
			LOG.warn("WARNING: uploadFileOnServer in UploadFilesUtils [File OR Path is Blank]");
			return null;
		}
	}
	
	
	
	@SuppressWarnings("deprecation")
	public static String[] uploadFilesOnServer (String schoolCode, CommonsMultipartFile[] files, String saveDirectory, String[] renameFile) throws Exception
	{
		if(files.length > 0 && renameFile.length > 0 && files.length == renameFile.length)
		{
			String[] returnsFilesName = new String[files.length];
			for(int i = 0; i < files.length; i++)
			{
				CommonsMultipartFile file = files[i];
				if(file != null && !"".equals(file.getOriginalFilename()) && saveDirectory != null && !"".equals(saveDirectory))
				{
					HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
					saveDirectory = request.getRealPath("/") + saveDirectory + "\\" + schoolCode + "\\";
					
					File dir = new File(saveDirectory);
					LOG.info("Upload File Path: " + saveDirectory);
					if(!dir.exists())
					{
						boolean b = dir.mkdirs();
						if(b)
							LOG.info("PATH NOT EXIST SO CREATED: " + dir.getPath());
						else
							LOG.info("PATH NOT CREATED: " + dir.getPath());
					}
					String fileextention = FilenameUtils.getExtension(saveDirectory + file.getOriginalFilename());
					File oldfile = new File(saveDirectory + file.getOriginalFilename());
					File newfile = new File(saveDirectory + renameFile[i] + "." + fileextention);
					if(newfile.exists())
					{
						newfile.delete();
					}
					file.transferTo(new File(saveDirectory + file.getOriginalFilename()));
					oldfile.renameTo(newfile);
					returnsFilesName[i] = "/" + schoolCode + "/" + renameFile[i] + "." + fileextention;
				}
				else
				{
					LOG.warn("WARNING: uploadFileOnServer in UploadFilesUtils [File OR Path is Blank]");
					return null;
				}
			}
			return returnsFilesName;
		}
		return new String[]{};
	}
}
