package service.custom;

import service.SuperService;

import java.util.Properties;

public interface EmailService extends SuperService {
    Properties loadConfig();
    void sendMail(String to,String subject,String content);
    Integer otpGenerator();
}
