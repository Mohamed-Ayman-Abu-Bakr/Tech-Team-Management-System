package Email_API;

import Classes.Data_Validation;
import Classes.Popup_Window;
import Exceptions.InvalidEmailException;
import javafx.scene.control.Alert;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {
    private final static String Email = "softwarecompany640@gmail.com";
    private final static String Password = "zhc6fDgqd6";

    private final static Properties properties = new Properties();


    private final static Session session = Session.getInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(Email, Password);
        }
    });

    private final static Message message= new MimeMessage(session);


    private static void setup_email() {
        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");
        try {
            message.setFrom(new InternetAddress(Email));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void send_Email(String recipient, String subject, String text,String type) throws MessagingException, InvalidEmailException {
        Data_Validation.checkEmail(recipient);
        setup_email();
        if (recipient.isEmpty()) {
            Alert empty = new Alert(Alert.AlertType.ERROR);
            empty.setContentText("Please fill the recipient field");
            empty.setHeaderText("Alert");
            empty.showAndWait();
        }
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setContent(text, "text/plain");
        Transport.send(message);
        Popup_Window.confirmation(type + " Email Sent Successfully","Send Email");
        //System.out.println("Message sent successfully");
    }


    public static void send_username_and_password (String recipient, String username,String password){
        String subject = "Welcome to Software Company";
        String text = "Your username is " + username + "\n" +"Your password is " + password;
        try {
            send_Email(recipient,subject,text,"User Credentials");
        } catch (Exception ex) {
            Popup_Window.error("Couldn't send credentials Email");
        }
    }

    public static void send_project_creation_invoice(String recipient, String project_name, String description,
                                                     String type, String delivery_date, String cost){
        String subject = "Project Invoice";
        String text = "Thank you for choosing Software Company"+"\n\n"+
                "Here are the Project details" + "\n\n" +
                "Project Name: " + project_name + "\n" +
                "Project Description: " + description + "\n" +
                "Project Type: " + type + "\n" +
                "Project Delivery Date: " + delivery_date + "\n" +
                "Total Cost: " + cost + " EGP";
        try {
            send_Email(recipient,subject,text,"Project Creation Invoice");
        } catch (Exception ex) {
            Popup_Window.error("Couldn't send Project Invoice Email");
        }
    }

    public static void send_project_modification_invoice(String recipient, String project_name, String description, String type, String delivery_date, String cost){
        String subject = "Project Update";
        String text = "Thank you for choosing Software Company"+"\n\n"+
                "There have been some modifications to the project\n\n" +
                "Here are the new Project details" + "\n\n" +
                "Project Name: " + project_name + "\n" +
                "Project Description: " + description + "\n" +
                "Project Type: " + type + "\n" +
                "Project Delivery Date: " + delivery_date + "\n" +
                "Total Cost: " + cost + " EGP";
        try {
            send_Email(recipient,subject,text,"Project Modification Invoice");
        } catch (Exception ex) {
            Popup_Window.error("Couldn't send Project Update Email");
        }
    }

    public static void send_password_update (String recipient,String password){
        String subject = "Password Rest";
        String text = "Your password has been reset by a manager" + "\n" +"Your new password is " + password;
        try {
            send_Email(recipient,subject,text,"Password Update");
        } catch (Exception ex) {
            Popup_Window.error("Couldn't send password reset Email");
        }
    }

}
