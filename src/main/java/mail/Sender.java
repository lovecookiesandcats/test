package mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sender {
	 
    private String username = "opovestitel.1@yandex.ru";
    private String password = "r3f33434g3wffwewefds";
    private Properties props;
    
    public Sender() {
 
        props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }
 

    
    public void send(String subject, String text){
        
    	Session session = Session.getInstance(props, new Authenticator() 
    	{
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(username, password);
            }
        }
    	);
 
        try {
            Message message = new MimeMessage(session);
           
            
            message.setFrom(new InternetAddress(username));
           
           
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("id234179690-989b7c330@vkmessenger.com,pipi1212@yandex.ru,id81779303-b44b63a0b@vkmessenger.com,id284764444-2423f9622@vkmessenger.com,zloykote4ka@gmail.com,kiberaction@yandex.ru, id234179690-989b7c330@vkmessenger.com "));
          

            message.setSubject(subject);
            
           
            message.setText(text);
       
            
            Transport.send(message);
            
        } catch (MessagingException e) {
            
        	
        }
    }
}