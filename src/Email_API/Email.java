package Email_API;

import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
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

    public static void send_Email(String recipient, String subject, String text) {
        setup_email();
        try {
            if (recipient.isEmpty()) {
                Alert empty = new Alert(Alert.AlertType.ERROR);
                empty.setContentText("Please fill the recipient field");
                empty.setHeaderText("Alert");
                empty.showAndWait();
            }
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            String email_text = text;
            message.setContent(email_text, "text/plain");
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "Email sent successfully");
            //System.out.println("Message sent successfully");
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "Cannot send email");
            System.err.println("Cannot send email. " + ex);
        }
    }


    public static void send_username_and_password (String recipient, String username,String password){
        String subject = "Welcome to Software Company";
        String text = "Your username is " + username + "\n" +"Your password is " + password;
        try {
            send_Email(recipient,subject,text);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Couldn't send credentials Email");
            //Logger.getLogger(Email_API.Email.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Couldn't send email");
        }
    }

    public static void send_project_creation_invoice(String recipient, String project_name, String description, String type, String delivery_date, String cost){
        String subject = "Project Invoice";
        String text = "Thank you for choosing Software Company"+"\n\n"+
                "Here are the Project details" + "\n\n" +
                "Project Name: " + project_name + "\n" +
                "Project Description: " + description + "\n" +
                "Project Type: " + type + "\n" +
                "Total Cost: " + cost + " EGP";
        try {
            send_Email(recipient,subject,text);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Couldn't send Project Invoice Email");
            //Logger.getLogger(Email_API.Email.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Couldn't send email");
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
                "Total Cost: " + cost + " EGP";
        try {
            send_Email(recipient,subject,text);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Couldn't send Project Update Email");
            //Logger.getLogger(Email_API.Email.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Couldn't send email");
        }
    }

    public static void send_password_update (String recipient,String password){
        String subject = "Password Rest";
        String text = "Your password has been reset by a manager" + "\n" +"Your new password is " + password;
        try {
            send_Email(recipient,subject,text);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Couldn't send password reset Email");
            //Logger.getLogger(Email_API.Email.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Couldn't send email");
        }
    }

}
