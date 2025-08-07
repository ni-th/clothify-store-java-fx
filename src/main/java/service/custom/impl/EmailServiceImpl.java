package service.custom.impl;


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

    }

    @Override
    public Integer otpGenerator() {
        Random random = new Random();
        Integer otp = 100000 + random.nextInt(9000000);
        return otp;
    }
}
