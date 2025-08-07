package service.custom.impl;


import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;
import service.custom.EmailService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class EmailServiceImpl implements EmailService {
    @Override
    public Properties loadConfig(){
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config/config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    @Override
    public void sendMail(String to,String subject,String content) {
        String TOKEN = loadConfig().getProperty("mailtrap.api.key");
        final MailtrapConfig config = new MailtrapConfig.Builder()
                .token(TOKEN)
                .build();
        final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);
        final MailtrapMail mail = MailtrapMail.builder()
                .from(new Address("hello@demomailtrap.co", "Clothify"))
                .to(List.of(new Address("nix.rapt@gmail.com")))
                .subject(subject)
                .text(content)
                .category("Integration Test")
                .build();
        try {
            System.out.println(client.send(mail));
        } catch (Exception e) {
            System.out.println("Caught exception : " + e);
        }
    }

    @Override
    public Integer otpGenerator() {
        Random random = new Random();
        Integer otp = 100000 + random.nextInt(9000000);
        return otp;
    }
}
