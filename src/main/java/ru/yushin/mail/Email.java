package ru.yushin.mail;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class Email {

    public String sendEmail(String address) throws MessagingException{
        String host = "smtp.gmail.com";
        String Password = "ehabrsvdyvmqkpal";
        String from = "autoreport11999@gmail.com";
        String toAddress = address;
        String filename = "actual.xlsx";
        // Get system properties
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, null);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, toAddress);
        message.setSubject("JavaMail Attachment");
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Here's the file");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        String succes = "Mail not sended to [" + address + "]";
        try {
            Transport tr = session.getTransport("smtps");
            tr.connect(host, from, Password);
            tr.sendMessage(message, message.getAllRecipients());
            succes = "Mail Sent Successfully to email [" + address + "]";
            tr.close();
        } catch (SendFailedException sfe) {
            System.out.println(sfe);
        }
        return succes;
    }

}