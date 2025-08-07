package service.custom.impl;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import service.custom.EmailService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class EmailServiceImpl implements EmailService {
    @Override
    public Properties loadConfig(){
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/config/config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    @Override
    public void sendMail(String to,String subject,String content) {
        Email from = new Email("nix.rapt@gmail.com");
//        String subject = "3rd test";
//        Email to = new Email("nimantha.bt@gmail.com");
//        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, new Email(to), new Content("text/plain", content));


        Properties properties = loadConfig();//load properties file
        SendGrid sg = new SendGrid(properties.getProperty("sendgrid.api.key"));//get api from properties
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Integer otpGenerator() {
        Random random = new Random();
        Integer otp = 100000 + random.nextInt(9000000);
        return otp;
    }
}
