package com.example.demo.api;

import com.example.demo.service.EmailSenderService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@RestController
@Data
public class api {

    private EmailSenderService emailSenderService;

    public api(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendEmail() throws MessagingException, UnsupportedEncodingException {
        emailSenderService.sendEmail("nguyenducmy1089x@gmail.com");
        return ResponseEntity.ok("sent successfully");
    }

    @GetMapping("/email")
    public String genEmail() throws IOException, TemplateException {
        HashMap<String, String> input = new HashMap();
        input.put("title", "zxvczxvzx");
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(this.getClass(), "/templates");
        Template temp = cfg.getTemplate("mail.ftl");
        String body = FreeMarkerTemplateUtils.processTemplateIntoString(temp, input);
        System.out.println(body);
        return "gened";
    }
}
