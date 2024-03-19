package com.Alquiler.Alquiler_Vehiculo.ControllerTest;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class MailTest {
    public static void main(String[] args) {
        String from = "proyectoautogo@gmail.com";
        String to = "hernan1796@gmail.com";
        String subject = "Test Email";
        String body = "This is a test email sent from JavaMail.";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "uwtnqjosivbicgte");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Email enviado con éxito");
        } catch (MessagingException e) {
            System.err.println("Error al enviar correo electrónico: " + e.getMessage());
        }
    }
}
