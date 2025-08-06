
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.connectionprovider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import newpackage.AppSession;
import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.*;

/**
 *
 * @author Junaid Mansuri
 */
class tinfo extends otpgeneration {

    public String throwuseremail() {
        String usergmail = null;

        try {

            Connection con = connectionprovider.getCon();
            int uid = AppSession.userid;
            PreparedStatement pst = con.prepareStatement("select usergmail from usertbl where userid=?");
            pst.setInt(1, uid);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                usergmail = rs.getString("usergmail");

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "An error occured please try again later ! " + ex);
        }

        return usergmail;
    }

}

public class emailservice extends tinfo {

 

    public void sendOTP(String email, String otp) {
        
      
        String ps = "dzuf zhlg rwgu mjmf";
        if (email == null || ps == null) {
            JOptionPane.showMessageDialog(null, "Missing Credentials Email or Password !");
            return;
        }
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, ps);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Your One Time OTP (otp)");
            message.setText("Your otp is \t" + otp + "\t use this to recover your  Password\n Don't share it with anyone.");
            
            Transport.send(message);
            JOptionPane.showMessageDialog(null, "OTP sent successfully to Email Address - " + email);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }
}

