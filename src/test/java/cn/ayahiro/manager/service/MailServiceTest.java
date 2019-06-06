package cn.ayahiro.manager.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleMail() {
        String content = "这是一封测试邮件" + new Date().toString();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo("601789175@qq.com");
        message.setSubject("测试邮箱发送");
        message.setText(content);
        mailSender.send(message);
    }

}