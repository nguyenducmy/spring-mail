package com.example.demo.service;

import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;

@Configuration
public class EmailSenderService {
    @Autowired
    Session session;

    public void sendEmail(String email) throws MessagingException, UnsupportedEncodingException {
        try
        {
            MimeMessage msg = new MimeMessage(session);
            MimeMessageHelper helper = new MimeMessageHelper(msg);
            helper.setFrom("nguyenducmy1089x@gmail.com", "Nguyen Duc My");
            helper.setTo(email);
            String subject = "Link to Reset Password";
            //fake data
            HashMap<String, String> input = new HashMap();
            input.put("firstName", "Nguyen");
            input.put("lastName", "Duc My");
            freemarker.template.Configuration cfg = new freemarker.template.Configuration();
            cfg.setClassForTemplateLoading(this.getClass(), "/templates");
            Template temp = cfg.getTemplate("mail.ftl");
            String body = FreeMarkerTemplateUtils.processTemplateIntoString(temp, input);
//            String emailBody = "<a>Dear User,</a><br/><br/>";
//            emailBody += "<a>Please Click On link to reset your password.</a><br/><br/>";
//            emailBody += "<br/><a>Thanks,</a><br/>";
//            emailBody += "<a>Corsiva Lab Teams</a><br/><br/>";
            helper.setSubject(subject);
            helper.setText(body,true);
            System.out.println("Message is ready");
            Transport.send(helper.getMimeMessage());

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
