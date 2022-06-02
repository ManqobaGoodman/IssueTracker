/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bigone.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import za.co.bigone.DAO.EmailInvoiceDAO;
import za.co.bigone.DAO.EmailInvoiceDAOImpl;
import za.co.bigone.DAO.InvoiceDAO;
import za.co.bigone.DAO.InvoiceDAOImpl;
import za.co.bigone.manager.DBPoolManagerBasic;
import za.co.bigone.model.Invoice;
import za.co.bigone.model.Person;

/**
 *
 * @author Student24
 */
public class MailServiceimpl implements MailService {

    private Properties properties;
    InvoiceDAO invoiceDAO;
     DBPoolManagerBasic dbm;
     EmailInvoiceDAO emailInvoiceDAO;
    public MailServiceimpl( DBPoolManagerBasic dbm) {
        this.invoiceDAO = new InvoiceDAOImpl(dbm);
        this.emailInvoiceDAO = new EmailInvoiceDAOImpl(dbm);
    }
    
    

    @Override
    public boolean sentMail() {
        boolean isSent= false;
        Invoice invoice = invoiceDAO.viewInvoice(order);

        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.startls.enable", "true");

        String toEmail = person.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "manqobamilk@gmail.com";

        final String username = "manqobamilk@gmail.com";//change accordingly
        final String password = "0769192723";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "relay.jangosmtp.net";

        // Get the Session object.
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            // Set Subject: header field
            message.setSubject("Invoice");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            String mailMes = "Good Day " + person.getTitle() + " " + person.getFirstname() + " " + person.getLastname() + "\n"
                    + "Thank you for your support ,your order will be sent to you soon.\n"
                    + "Your Invoice No. " + invoice.getInvoiceid() + "\n"
                    + "Please find the attached invoice.";
            messageBodyPart.setText(mailMes);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            String folder = LocalDate.now().toString();
                Path path = Paths.get("C:\\Users\\Student24\\Desktop\\BakeryProjectV1\\invoicePdf\\"+folder);
                Files.createDirectories(path);
                String location = path.toString();
                String pdfName = person.getFirstname()+"_"+person.getLastname()+"_000"+invoice.getInvoiceid();

            messageBodyPart = new MimeBodyPart();
            String filename = location+"\\"+pdfName+".pdf";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            
            isSent = sendEmail(person.getPersonId(), mailMes);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
