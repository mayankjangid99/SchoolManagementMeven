package com.school.common.generic;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailUtil {

	private static Logger log = LoggerFactory.getLogger(EmailUtil.class);
	private static ExecutorService executor = null;
	
	
	/**
	 * 
	 * @param recipient
	 * @param subject
	 * @param emailContent
	 * @return
	 */
	public int sendEmail(String sender, String recipient, String subject, String emailContent) {
		
		sendEmail(getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER), sender, recipient, "", "", subject,
				emailContent, getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS), null);
		return 0;
	}
	

	
	public int sendEmailWithCC(String sender, String recipient, String ccRecipient, 
			String subject, String emailContent) {
		
		sendEmail(getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER), sender, recipient, ccRecipient, "", subject,
				emailContent, getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS), null);
		return 0;
	}
	
	public int sendEmailWithCCWithAttach(String sender, String recipient, String ccRecipient, 
			String subject, String emailContent, String attachments) {
		
		sendEmail(getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER), sender, recipient, ccRecipient, "", subject,
				emailContent, getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS), parseAttachments(attachments));
		return 0;
	}
	
	public int sendEmailWithCCAndBCC( String sender, String recipient, String ccRecipient, String bccRecipient,
			String subject, String emailContent) {
		
		sendEmail(getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER), sender, recipient, ccRecipient, bccRecipient, subject,
				emailContent, getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS), null);
		return 0;
	}
	
	
	public int sendEmailWithCCAndBCCWithAttach(String sender, String recipient, String ccRecipient, String bccRecipient,
			String subject, String emailContent, String attachments) {
		
		sendEmail(getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER), sender, recipient, ccRecipient, bccRecipient, subject,
				emailContent, getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS), parseAttachments(attachments));
		return 0;
	}
	
	
	public int sendEmailWithAttach(String sender, String recipient, String subject, String emailContent, String attachments) {
		
		sendEmail(getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER), sender, recipient, "", "", subject,
				emailContent, getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS), parseAttachments(attachments));
		return 0;
	}
	
	
	
	
	/**
	 * 
	 * @param server
	 * @param sender
	 * @param recipient
	 * @param ccRecipient
	 * @param bccRecipient
	 * @param subject
	 * @param emailContent
	 * @param attachments
	 * @return
	 */
	public int sendEmail(String server, String sender, String recipient, String ccRecipient, String bccRecipient,
			String subject, String emailContent, String attachments) {
		
		sendEmail(getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER), sender, recipient, ccRecipient, bccRecipient, subject,
				emailContent, getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS), parseAttachments(attachments));
		return 0;
	}

	/**
	 * 
	 * @param server
	 * @param sender
	 * @param recipient
	 * @param ccRecipient
	 * @param bccRecipient
	 * @param subject
	 * @param emailContent
	 * @return
	 */
	public int sendHtmlEmail(String server, String sender, String recipient, String ccRecipient, String bccRecipient,
			String subject, String emailContent) {
		sendEmail(getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER), sender, recipient, ccRecipient, bccRecipient, subject,
				emailContent, getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS), null);
		return 0;
	}

	/**
	 * 
	 * @param server
	 * @param sender
	 * @param recipient
	 * @param ccRecipient
	 * @param bccRecipient
	 * @param subject
	 * @param emailContent
	 * @param name
	 * @return
	 */
	public int sendNameHtmlEmail(String server, String sender, String recipient, String ccRecipient,
			String bccRecipient, String subject, String emailContent, String name) {
		sendEmail(getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER), sender, recipient, ccRecipient, bccRecipient, subject,
				emailContent, getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS), null);
		return 0;
	}

	/**
	 * 
	 * @param server
	 * @param sender
	 * @param recipient
	 * @param ccRecipient
	 * @param bccRecipient
	 * @param subject
	 * @param emailContent
	 * @param attachments
	 * @return
	 */
	public int sendEmailWithMultipleAttachments(String server, String sender, String recipient, String ccRecipient,
			String bccRecipient, String subject, String emailContent, String[] attachments) {
		sendEmail(getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER), sender, recipient, ccRecipient, bccRecipient, subject,
				emailContent, getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS), attachments);
		return 0;
	}

	/**
	 * 
	 * @param server
	 * @param sender
	 * @param recipient
	 * @param ccRecipient
	 * @param bccRecipient
	 * @param htmlFormat
	 * @param subject
	 * @param emailContent
	 */

	public void sendMailWithAuth(String server, String username, String password, String sender, String recipient, String ccRecipient,
			String bccRecipient, boolean htmlFormat, String subject, String emailContent) {
		sendEmail(getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER), sender, recipient, ccRecipient, bccRecipient, subject,
				emailContent, getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS), null);
	}

	/**
	 * 
	 * @param mailServerConfig
	 * @param sender
	 * @param recipient
	 * @param ccRecipient
	 * @param bccRecipient
	 * @param subject
	 * @param emailContent
	 * @param serverAddress
	 * @param attachmentLocation
	 */
	public void sendEmail(String mailServerConfig, final String sender, final String recipient,
			final String ccRecipient, final String bccRecipient, final String subject, final String emailContent,
			final String serverAddress, final String[] attachmentLocation) {

		final Map<String, String> mailConfigProperties = parseAndReadmailConfigProperties(mailServerConfig);

		if ((recipient == null || recipient.equals("")) && (ccRecipient == null || ccRecipient.equals(""))) {
			if (log.isErrorEnabled()) {
				log.error("The TO/ CC address is empty, hence unable to send email.");
			}
			return;
		} else if (mailConfigProperties == null || mailConfigProperties.get("mailserver") == null) {
			if (log.isErrorEnabled()) {
				log.error("The mail server address is not configured in the application.");
			}
			return;
		} 
		if (mailConfigProperties != null) { 
			
			if(executor == null) {
				executor = Executors.newFixedThreadPool(10);
			}
			executor = Executors.newFixedThreadPool(10);
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						final String PROTOCOL = "smtp";
						String hostName = null;
						Properties properties = System.getProperties();
						MimeBodyPart bodyPart1 = new MimeBodyPart();
						MimeBodyPart bodyPart2 = null;
						MimeMultipart multipart = new MimeMultipart();
						MimeMessage message = null;
						Session session = null;
						properties.put("mail.smtp.host", mailConfigProperties.get("mailserver"));
						properties.put("mail.smtp.port", mailConfigProperties.get("port"));
						
//						properties.setProperty("mail.smtp.host","mail.sansoft.in");
						properties.put("mail.transport.protocol", PROTOCOL);
//						properties.put("mail.smtp.port","25");
//						properties.put("mail.smtp.auth","true");
						properties.put("mail.smtp.starttls.enable", "true");
						
						if (mailConfigProperties.get("uid") != null && mailConfigProperties.get("pwd") != null
								&& mailConfigProperties.get("mailserver") != null) {
							properties.put("mail.smtp.auth", "true");
						} else {
							properties.put("mail.smtp.auth", "false");
						}
						
//						Set Other values in Properies
						for (Map.Entry<String, String> entry : mailConfigProperties.entrySet()) {
							if (!"uid".equals(entry.getKey()) && !"pwd".equals(entry.getKey())
									&& !"mailserver".equals(entry.getKey()) && !"port".equals(entry.getKey())) {
								properties.put(entry.getKey(), entry.getValue());
							}
						} // End of for loop
						if (log.isDebugEnabled()) {
							log.debug("Mail Properties set by the function {}", properties);
						}

						session = Session.getDefaultInstance(properties);
						message = new MimeMessage(session);
						message.setFrom(new InternetAddress(sender));
						message.setSentDate(new Date());

						if (recipient != null && !recipient.trim().equals("")) {
							message.addRecipients(RecipientType.TO,
									InternetAddress.parse(recipient.replaceAll(";", ",")));
						}
						if (ccRecipient != null && !ccRecipient.trim().equals("")) {
							message.addRecipients(RecipientType.CC,
									InternetAddress.parse(ccRecipient.replaceAll(";", ",")));
						}
						if (bccRecipient != null && !bccRecipient.trim().equals("")) {
							message.addRecipients(RecipientType.BCC,
									InternetAddress.parse(bccRecipient.replaceAll(";", ",")));
							if (log.isWarnEnabled()) {
								log.warn("A possible security breach. Someone is trying to send email as blind copy to {}",
										bccRecipient);
							}
						}
						try {
							//#2975- By Hemant 2 Dec 2015
							if (serverAddress == null || serverAddress.trim().equals("")) {
								hostName = InetAddress.getLocalHost().getHostName();
							} else {
								hostName = serverAddress;
							}
						} catch (UnknownHostException e) {
							if (log.isErrorEnabled()) {
								log.error("Unable to resolve host name using the InetAddress address class", e);
							}
						}
						if (subject != null && !subject.trim().equals("")) {
							message.setSubject("[" + hostName + "] - " + subject.replaceAll("[\\r\\n ]", " "));
						} else {
							message.setSubject("[" + hostName + "]");
							if (log.isDebugEnabled()) {
								log.debug("Email subject is missing.");
							}
						}
						if (emailContent != null && !emailContent.trim().equals("")) {
							bodyPart1.setDataHandler(
									new DataHandler(new ByteArrayDataSource(emailContent, "text/html")));
							multipart.addBodyPart(bodyPart1);
						}
						
						if (attachmentLocation != null && attachmentLocation.length > 0) {
							for (String location : attachmentLocation) {
								Path filePath = Paths.get(location);
								if (Files.exists(filePath)) {
									bodyPart2 = new MimeBodyPart();
									bodyPart2.attachFile(filePath.toFile());
									multipart.addBodyPart(bodyPart2);
								} else {
									if (log.isErrorEnabled()) {
										log.error("The file {} is missing at the location {}", filePath.getFileName(),
												filePath);
									}
								}
							}
						}
						
						message.setContent(multipart);
						Transport transport = session.getTransport(PROTOCOL);
						if (properties.get("mail.smtp.auth").equals("true")) {
							transport.connect(mailConfigProperties.get("mailserver"), mailConfigProperties.get("uid"),
									mailConfigProperties.get("pwd"));
						}
						transport.sendMessage(message, message.getAllRecipients());
						transport.close();
						log.info("Successfully mail sent to recipient.");
					} catch (Exception e) {
						if (log.isErrorEnabled()) {
							log.error("Error in sending email to recipient", e);
						}
					}
				}
			});
		}
	}

	/**
	 * 
	 * @param mailServerConfig
	 * @return
	 */
	private static Map<String, String> parseAndReadmailConfigProperties(String mailServerConfig) {
		Map<String, String> mailConfigProperties = null;

		if (mailServerConfig != null && !mailServerConfig.trim().equals("")) {
			String[] tempServerDetail = mailServerConfig.trim().split(";");
			if (tempServerDetail != null) {
				mailConfigProperties = new Hashtable<>();
				for (String tempStr : tempServerDetail) {
					String[] property = tempStr.split("=");
					if (property[0] != null && !property[0].trim().equals("")) {
						if (property[1] == null || property[1].trim().equals("")) {
							if (log.isDebugEnabled()) {
								log.debug("The value for configuration paramter {} is empty or null.", property[0]);
							}
							mailConfigProperties.put(property[0].trim(), null);
						} else {
							mailConfigProperties.put(property[0].trim(), property[1].trim());
						}
					}
				} // End of for loop
				if (log.isDebugEnabled()) {
					log.debug("The mail configuration values are {}", mailConfigProperties);
				}
			}
		}
		return mailConfigProperties;
	}

	/**
	 * 
	 * @param confCode
	 * @return
	 */
	private static String getConfigParameter(String confCode) {
		try {
			/*GlobalConfValue globalConfValue = (GlobalConfValue) ServiceLocalLocator.getFacadeUserManager()
					.read(GlobalConfValue.class, confCode);
			if (globalConfValue != null) {
				return globalConfValue.getConfValue();
			} else {
				return null;
			}*/
			
			/*HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			SessionManager sessionManager = (SessionManager) session.getAttribute("SessionManager");
			List<GlobalConfigModel> globalConfigList = sessionManager.getGlobalConfigList();
			for(GlobalConfigModel config : globalConfigList)
			{
				if(confCode.equalsIgnoreCase(config.getConfigCode()))
					return config.getConfigValue();
				else if(confCode.equalsIgnoreCase(config.getConfigCode()))
					return config.getConfigValue();
					
			}
			return null;
			SessionManagerDataHelper sessionManagerDataHelper = new SessionManagerDataHelper(); */
			return SessionManagerDataHelper.getGlobalConfigParameterValue(confCode);
			
			/*if(StaticValue.GLOBAL_CONFIG_MAIL_SERVER.equals(confCode))
				return "uid=mayank@sansoft.in;pwd=knayam200;mailserver=mail.sansoft.in;port=25";
			else
				return "Hard coded Server Name";*/
		} catch (Exception ex) {
			if (log.isErrorEnabled()) {
				log.error("Error in getting facade user manager", ex);
			}
			return null;
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param attachments
	 * @return
	 */
	private static String[] parseAttachments(String attachments) {
		ArrayList<String> attachmentList = new ArrayList<String>();
		if (attachments != null) {
			int startIndex = 0;
			int posIndex = 0;
			while (-1 != (posIndex = attachments.indexOf("///", startIndex))) {

				attachmentList.add(attachments.substring(startIndex, posIndex));
			}
			// last, or only, attachment file;
			if (startIndex < attachments.length()) {
				attachmentList.add(attachments.substring(startIndex));

			}
		}
		return attachmentList.toArray(new String[attachmentList.size()]);
	}
	
	public int sendEmailUsingFreemarker(String mailMessageBody,
			String templateName, String smtpServer, String sender,
			String recipient, String ccRecipient, String bccRecipient,
			boolean html, String referenceId, String saluteName, String subject) {

		int success = 0;
		Session session = null;
		String smtpServerUserName = "";
		String smtpServerPassword = "";
		String mailServer= "";
		if (validateMailServerForAuth(smtpServer)) {
			String[] arr = null;
			arr = parseMailServerForAuth(smtpServer);
			if (arr == null) {
				System.out.println("Error in MAIL_SERVER Parameter Specified in Global Configuration");
			}
			if (arr[2] == null || arr[2].equals("")) {
				System.out.println("SMTP server name is null. Please check MAIL_SERVER Parameter Specified in Global Configuration");
			}
			if (arr[0] == null || arr[0].equals("")) {
				System.out.println("User name is null. Please check MAIL_SERVER Parameter Specified in Global Configuration");
			}
			if (arr[1] == null || arr[1].equals("")) {
				System.out.println("Password is null. Please check MAIL_SERVER Parameter Specified in Global Configuration");
			}

			smtpServerUserName = arr[0];
			smtpServerPassword = arr[1];
			mailServer = arr[2];

			Properties properties = System.getProperties();
			properties.put("mail.smtp.host", mailServer);
			properties.put("mail.smtp.auth", "true");
			session = Session.getInstance(properties);
			session.setDebug(false);

			try {
				// create a message;
				MimeMessage message = new MimeMessage(session);

				if (html) {
					message.setContent(mailMessageBody,"text/html");
				} else {
					message.setContent(mailMessageBody,"text/plain");
				}

				message.setFrom(new InternetAddress(sender));

				// Added the code change for fixing the CRLF injection
				/*ValueHelper valueHelper = new ValueHelper();
				recipient = valueHelper.crlfFilter(recipient);*/
				
				if (recipient != null) {
					message.addRecipients(Message.RecipientType.TO, recipient);
				}

				// extract the Cc-recipients and assign them to the message;
				if (ccRecipient != null) {
					message.addRecipients(Message.RecipientType.CC,
							InternetAddress.parse(ccRecipient));
				}

				// extract the Bcc-recipients and assign them to the message;
				if (bccRecipient != null) {
					message.addRecipients(Message.RecipientType.BCC,
							InternetAddress.parse(bccRecipient));
				}

				// set the Date: header;
				message.setSentDate(new Date());

				// send the message;
				Transport tr = session.getTransport("smtp");
				tr.connect(mailServer, smtpServerUserName, smtpServerPassword);
				tr.sendMessage(message, message.getAllRecipients());
				success = 1;
				tr.close();
			} catch (MessagingException ex) {
				success = 0;
				ex.printStackTrace();
			}
		}
		return success;
	}
	/**
	  * Mail Server Authentication
	  * @param mailServer
	  * @return
	  */
	  private boolean validateMailServerForAuth(String mailServer){
		  //mailServer="uid=username;pwd=password;mailserver=mailserves";
			try {
				if(mailServer != null && !mailServer.equals("")){
					if(mailServer.indexOf("pwd=",0) != -1 && mailServer.indexOf("uid=",0) != -1 && mailServer.indexOf("mailserver=",0) != -1 ){
						return true;
					}
					else return false;
				}else{
					System.out.println("Please check the MAIL_SERVER Parameter in Global Configuration");
				}
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		private String[] parseMailServerForAuth(String mailServerParam){
			String [] arr = new String[3];
			//mailServerParam = "uid=username;pwd=password;mailserver=mailserves".trim();
			try {
				mailServerParam = mailServerParam.trim();
				arr[0] = mailServerParam.substring((mailServerParam.indexOf("uid=")+4),mailServerParam.indexOf(";"));
				arr[1] = mailServerParam.substring((mailServerParam.indexOf("pwd=")+4),mailServerParam.indexOf(";mailserver"));
				arr[2] = mailServerParam.substring((mailServerParam.indexOf("mailserver=")+11),mailServerParam.length());
				return arr;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			
		  }

public int sendEmailXMLBody(String SMTPServer,
			String Sender,
			String Recipient,
			String CcRecipient,
			String BccRecipient,
			String Subject,
			String Body,
			String Attachments) {

		// Error status;
		int ErrorStatus = 0;
		// create some properties and get the default Session;
		Properties props = System.getProperties();

		//implemented for authentication String
		if(validateMailServerForAuth(SMTPServer)){
			String [] arr = null;
			arr =  parseMailServerForAuth(SMTPServer);
			if(arr != null && arr[2] != null){
				SMTPServer =    arr[2];
			}

			///
			if(arr == null){
				System.out.println("Error in MAIL_SERVER Parameter Specified in Global Configuration");
			}
			if(arr[2] == null || arr[2].equals("")){
				System.out.println("SMTP server name is null. Please check MAIL_SERVER Parameter Specified in Global Configuration");
			}
			if(arr[0] == null || arr[0].equals("")){
				System.out.println("User name is null. Please check MAIL_SERVER Parameter Specified in Global Configuration");
			}
			if(arr[1] == null || arr[1].equals("")){
				System.out.println("Password is null. Please check MAIL_SERVER Parameter Specified in Global Configuration");
			}

			Properties properties = System.getProperties();
			properties.put("mail.smtp.host", SMTPServer);
			properties.put("mail.smtp.auth", "true");

			Session session = Session.getInstance(properties);
			try{
				// create a message;
				MimeMessage msg = new MimeMessage(session);

				// extracts the senders and adds them to the message;
				// Sender is a comma-separated list of e-mail addresses
				msg.addFrom(InternetAddress.parse(Sender));

				// extract the recipients and assign them to the message;
				msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(Recipient));

				// extract the Cc-recipients and assign them to the message;
				if (CcRecipient != null) {
					msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(CcRecipient));
				}

				// extract the Bcc-recipients and assign them to the message;
				if (BccRecipient != null) {
					msg.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(BccRecipient));
				}

				// subject field;
				/**
				 * Host Name is added before with subject line as per ENHT-434 by Liju Paul on 12-01-2011
				 */
				String hostname="";
				try{
					hostname=InetAddress.getLocalHost().getHostName();
				}catch(Exception ex){
					hostname="";
				}

				String mailServerName="";
				try{
					mailServerName = getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS);	
				}catch(Exception ex){
					mailServerName="";
				}


				if(mailServerName!=null && !mailServerName.equals(""))
					Subject= "["+mailServerName+"] "+Subject;
				else if(hostname!=null && !hostname.equals(""))
					Subject= "["+hostname+"] "+Subject;

				// create the Multipart to be added the parts to;
				Multipart mp = new MimeMultipart();

				// create and fill the first message part;
				MimeBodyPart mbp = new MimeBodyPart();
				mbp.setContent(Body, "text/plain");
				// attach the part to the multipart;
				mp.addBodyPart(mbp);
				mbp = new MimeBodyPart();

				// attach the files to the message;
				if (Attachments != null) {
					int StartIndex = 0;
					int PosIndex = 0;
//					int i=0;
					while (-1 != (PosIndex = Attachments.indexOf("///", StartIndex))) {

						// create and fill other message parts;
						FileDataSource fds =  new FileDataSource(Attachments.substring(StartIndex, PosIndex));
						mbp.setDataHandler(new DataHandler(fds));
						mbp.setFileName(fds.getName());
						mp.addBodyPart(mbp);
						PosIndex += 3;
						StartIndex = PosIndex;
//						i++;
					}
					// last, or only, attachment file;
					if (StartIndex < Attachments.length()) {
						FileDataSource fds = new FileDataSource(Attachments.substring(StartIndex));
						mbp.setDataHandler(new DataHandler(fds));
						mbp.setFileName(fds.getName());
						mp.addBodyPart(mbp);
					}
				}

				msg.setContent(mp);


				Transport tr = session.getTransport("smtp");
				tr.connect(SMTPServer, arr[0], arr[1]);
				tr.sendMessage(msg, msg.getAllRecipients());
				tr.close();
			}catch(Exception e){
				e.printStackTrace();

				/*
				 * EASE-2889 changed the ErrorStatus value to 1 when
				 * Authentication fails requested by Sreeni modified by
				 * Shyam Mohan T.M on 24/Sep/2012
				 */
				ErrorStatus = 1;
			}

			///
		}else{
			//
			props.put("mail.smtp.host", SMTPServer);
			Session session = Session.getDefaultInstance(props, null);

			try {
				// create a message;
				MimeMessage msg = new MimeMessage(session);

				// extracts the senders and adds them to the message;
				// Sender is a comma-separated list of e-mail addresses
				msg.addFrom(InternetAddress.parse(Sender));

				// extract the recipients and assign them to the message;
				msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(Recipient));

				// extract the Cc-recipients and assign them to the message;
				if (CcRecipient != null) {
					msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(CcRecipient));
				}

				// extract the Bcc-recipients and assign them to the message;
				if (BccRecipient != null) {
					msg.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(BccRecipient));
				}
				/**
				 * Host Name is added before with subject line as per ENHT-434 by Liju Paul on 12-01-2011
				 */
				String hostname="";
				try{
					hostname=InetAddress.getLocalHost().getHostName();
				}catch(Exception ex){
					hostname="";
				}

				String mailServerName="";
				try{
					mailServerName = getConfigParameter(StaticValue.GLOBAL_CONFIG_MAIL_SERVER_ADDRESS);	
				}catch(Exception ex){
					mailServerName="";
				}


				if(mailServerName!=null && !mailServerName.equals(""))
					Subject= "["+mailServerName+"] "+Subject;
				else if(hostname!=null && !hostname.equals(""))
					Subject= "["+hostname+"] "+Subject;        // subject field;

				// create the Multipart to be added the parts to;
				Multipart mp = new MimeMultipart();

				// create and fill the first message part;
				MimeBodyPart mbp = new MimeBodyPart();
				mbp.setContent(Body, "text/plain");
				// attach the part to the multipart;
				mp.addBodyPart(mbp);
				mbp = new MimeBodyPart();

				// attach the files to the message;
				if (Attachments != null) {
					int StartIndex = 0;
					int PosIndex = 0;
//					int i=0;
					while (-1 != (PosIndex = Attachments.indexOf("///", StartIndex))) {

						// create and fill other message parts;
						FileDataSource fds =  new FileDataSource(Attachments.substring(StartIndex, PosIndex));
						mbp.setDataHandler(new DataHandler(fds));
						mbp.setFileName(fds.getName());
						mp.addBodyPart(mbp);
						PosIndex += 3;
						StartIndex = PosIndex;
//						i++;
					}
					// last, or only, attachment file;
					if (StartIndex < Attachments.length()) {
						FileDataSource fds = new FileDataSource(Attachments.substring(StartIndex));
						mbp.setDataHandler(new DataHandler(fds));
						mbp.setFileName(fds.getName());
						mp.addBodyPart(mbp);
					}
				}

				// add the Multipart to the message;
				msg.setContent(mp);

				// set the Date: header;
				msg.setSentDate(new Date());

				// send the message;
				Transport.send(msg);
			} catch (MessagingException ex) {
				ex.printStackTrace();
				ErrorStatus = 1;
			}

		}
		return ErrorStatus;
	}

	public static void main(String[] arg)
	{
		System.out.println("main");
		EmailUtil email = new EmailUtil();
		email.sendEmail("", SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER), "mayankjangid99@gmail.com", "", "", "testing", "hiii", "");
		System.out.println("sent");
	}
}