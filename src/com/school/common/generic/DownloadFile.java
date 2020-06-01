package com.school.common.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class DownloadFile {

	private static Logger LOG = LoggerFactory.getLogger(DownloadFile.class);
	public static final String DOWNLOAD_ERROR = "downloadError";
	
	public static String getFile() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		String forward = null;
		try {
			final String filePathEnc = request.getParameter("filePathEnc");
			final String filePath = Base64.decode(filePathEnc);

			if (filePath == null) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("The file location provided in the URL {} is invalid ", filePath);
				}
				forward = DOWNLOAD_ERROR;
			} else {
				//final String dir = fileDir + filePath;
				final Path absoluteFilePath = Paths.get(filePath);
				String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);

				if (filePath.lastIndexOf('/') != -1) {
					fileName = filePath.substring(filePath.lastIndexOf('/') + 1);
				}
				if (fileName == null) {
					fileName = "" + Calendar.getInstance().getTimeInMillis();
					if (LOG.isWarnEnabled()) {
						LOG.warn(
								"The application is unable to determine a filename, hence assigning a auto-generated value {}",
								fileName);
					}
				}
				/* Setting basic values */
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");

				try (InputStream inputStream = new FileInputStream(absoluteFilePath.toFile());
						OutputStream outputStream = response.getOutputStream()) {
					int index = -1;
					final byte[] byteArray = new byte[32768];

					while ((index = inputStream.read(byteArray, 0, 32768)) != -1) {
						outputStream.write(byteArray, 0, index);
					} // End of while loop
				} catch (FileNotFoundException | SecurityException e) {
					if (LOG.isErrorEnabled()) {
						LOG.error(
								"The file is unavailable or the OS user does not have enough privileges to read the file",
								e);
					}
					forward = DOWNLOAD_ERROR;
				} catch (IOException e) {
					if (LOG.isErrorEnabled()) {
						LOG.error("Error in reading or writing the file", e);
					}
					forward = DOWNLOAD_ERROR;
				}
			}
		} catch (UnsupportedEncodingException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Error in encoding the file name to UTF-8", e);
			}
			forward = DOWNLOAD_ERROR;
		} catch (Exception e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unknown Error in downloading the file.", e);
			}
			forward = DOWNLOAD_ERROR;
		}
		return forward;
	}
	
	
	public static String getFile(final String filePath) {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		String forward = null;
		try {
			if (filePath == null) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("The file location provided in the URL {} is invalid ", filePath);
				}
				forward = DOWNLOAD_ERROR;
			} else {
				//final String dir = fileDir + filePath;
				final Path absoluteFilePath = Paths.get(filePath);
				String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);

				if (filePath.lastIndexOf('/') != -1) {
					fileName = filePath.substring(filePath.lastIndexOf('/') + 1);
				}
				if (fileName == null) {
					fileName = "" + Calendar.getInstance().getTimeInMillis();
					if (LOG.isWarnEnabled()) {
						LOG.warn(
								"The application is unable to determine a filename, hence assigning a auto-generated value {}",
								fileName);
					}
				}
				/* Setting basic values */
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/octet-stream");

				try (InputStream inputStream = new FileInputStream(absoluteFilePath.toFile());
						OutputStream outputStream = response.getOutputStream()) {
					int index = -1;
					final byte[] byteArray = new byte[32768];

					while ((index = inputStream.read(byteArray, 0, 32768)) != -1) {
						outputStream.write(byteArray, 0, index);
					} // End of while loop
				} catch (FileNotFoundException | SecurityException e) {
					if (LOG.isErrorEnabled()) {
						LOG.error(
								"The file is unavailable or the OS user does not have enough privileges to read the file",
								e);
					}
					forward = DOWNLOAD_ERROR;
				} catch (IOException e) {
					if (LOG.isErrorEnabled()) {
						LOG.error("Error in reading or writing the file", e);
					}
					forward = DOWNLOAD_ERROR;
				}
			}
		} catch (UnsupportedEncodingException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Error in encoding the file name to UTF-8", e);
			}
			forward = DOWNLOAD_ERROR;
		} catch (Exception e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Unknown Error in downloading the file.", e);
			}
			forward = DOWNLOAD_ERROR;
		}
		return forward;
	}
}
