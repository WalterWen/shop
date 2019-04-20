package com.shop.Utils;

import java.security.GeneralSecurityException;
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
import javax.mail.internet.MimeMessage.RecipientType;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtils {
//	email是邮箱地址   emailMsg是发送给邮箱的内容
	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException, GeneralSecurityException {
		
	
		String from = "1498592764@qq.com";// 发件人电子邮箱
		String host = "smtp.qq.com"; // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)
		Properties properties = System.getProperties();// 获取系统属性
		properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
		properties.setProperty("mail.smtp.auth", "true");// 打开认证
		//QQ邮箱需要下面这段代码，163邮箱不需要
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);
		
		
		// 1.获取默认session对象（创建连接对象，连接到邮箱服务器）
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("1498592764@qq.com", "yslcusupsvyjfeab"); // 发件人邮箱账号、密码
			}
		});

		// 2.创建邮件对象
		Message message = new MimeMessage(session);
		// 2.1设置发件人
		message.setFrom(new InternetAddress(from));
		// 2.2设置接收人
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		// 2.3设置邮件主题
		message.setSubject("乐购网，欢迎您！用户激活邮件，请注意查收！");
		message.setContent(emailMsg, "text/html;charset=utf-8");
		
		// 3.发送邮件
		Transport.send(message);
		System.out.println("邮件成功发送!");
	}
}
/*public class MailUtils {
//	email是邮箱地址   emailMsg是发送给邮箱的内容
	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("1498592764@qq.com", "");
			}
		};
		Session session = Session.getInstance(props, auth);
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress("1498592764@qq.com")); // 设置发送者

		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者

		message.setSubject("用户激活");
		message.setContent(emailMsg, "text/html;charset=utf-8");
		Transport.send(message);
	}
}*/
