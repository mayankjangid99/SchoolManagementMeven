package com.school.common.generic;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class SpringEmailUtil {

	
	private static JavaMailSenderImpl setGetEmail()
	{
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		javaMailSenderImpl.setHost("mail.sansoft.in");
		javaMailSenderImpl.setPort(25);
		javaMailSenderImpl.setUsername(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER));
		javaMailSenderImpl.setPassword("knayam200");
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.debug", true);
		javaMailSenderImpl.setJavaMailProperties(properties);
		return javaMailSenderImpl;
	}
	
	public void sendEmailViaSpring()
	{
		SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
		crunchifyMsg.setFrom(SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER));
		crunchifyMsg.setTo("mayankjangid99@gmail.com");
		crunchifyMsg.setSubject("Test");
		crunchifyMsg.setText("hiiiii");
		setGetEmail().send(crunchifyMsg);
	}
	
	
	
	public void sendEmailViaNormal()
	{
		//System.out.println(request.getParameter("to"));
//		String[] toall=("mayankjangid99@gmail.com").split(";");			//To Multiple
		String subject="subject";
		String message="message";
		final String from=SessionManagerDataHelper.getGlobalConfigParameterValue(StaticValue.GLOBAL_CONFIG_MAIL_SENDER);
		final String password="knayam200";
		
		//Set Environmnet to connect to Mail Server.
		Properties properties=System.getProperties();
		properties.setProperty("mail.smtp.host","mail.sansoft.in");
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.port","25");
		properties.setProperty("mail.smtp.auth","true");
		properties.setProperty("mail.smtp.starttls.enable", "true");

		//Connect to Mail Server.
		Session ses=Session.getInstance(properties,new Authenticator()
															{
																public PasswordAuthentication getPasswordAuthentication()
																{
																	return new PasswordAuthentication(from,password);
																}
															});
		//Compose Mail.
		MimeMessage mimemessage=new MimeMessage(ses);
		try
		{
			mimemessage.setSentDate(new Date());
			mimemessage.setFrom(new InternetAddress(from));
			//mimemessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));	
			/* mimemessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			mimemessage.addRecipient(Message.RecipientType.CC,new InternetAddress(to));
			mimemessage.addRecipient(Message.RecipientType.BCC,new InternetAddress(to)); */
			mimemessage.setSubject(subject);
			mimemessage.setText(message);
			/*for(String to:toall)
			{

				mimemessage.addRecipient(Message.RecipientType.TO,new InternetAddress("mayankjangid99@gmail.com"));	
				//Send Mail to Mail Server.
				Transport.send(mimemessage);
			}*/
			mimemessage.addRecipient(Message.RecipientType.TO,new InternetAddress("mayankjangid99@gmail.com"));	
			Transport.send(mimemessage);
			System.out.println("<h3>Mail Successfully Sent.</h3>");
		}
		catch(AddressException ae)
		{
			System.out.println(ae);
		}
		catch(MessagingException me)
		{
			System.out.println(me);
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	public static void main(String[] args)
	{
		SpringEmailUtil springEmailUtil =new SpringEmailUtil();
		//springEmailUtil.sendEmailViaSpring();
		System.out.println("send");
		
		springEmailUtil.sendEmailViaNormal();
		
		
		
		
	}
}
