package com.thejing.admin;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = AdminApplication.class)
@RunWith(SpringRunner.class)
public class MailTest {


    @Autowired
    private JavaMailSender javaMailSender;

    private String from = "tj15676818429@qq.com";

    private String to = "tj1567681";

    private String subject = "测试邮件";

    private String text = "java定时任务发邮件";

    @Test
    public void sendMail() {
        System.out.println("kaishil");

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(from+"(阿姐)");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            javaMailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("邮件发送成功!");
        }

    }


}
