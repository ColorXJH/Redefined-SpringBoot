package com.example.springboottask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ColorXJH
 * @version 1.0
 * @description:
 * @date 2022/9/7 15:19
 */
@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;
    private static final String SEND_FROM="1429805417@qq.com";
    private static final String SEND_TO="1204332316@qq.com";
    /**
     * Description: 发送普通邮件
     * @Author: ColorXJH
     * @Date: 2022/9/7 15:32
     * @param
     * @Return: void
     **/
    public void sendSimpleMail(){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(SEND_FROM);
        message.setTo(SEND_TO);
        message.setSubject("---发送普通邮件主题---");
        message.setText("---发送普通邮件文本内容---");
        try {
            mailSender.send(message);
            System.out.println("邮件发送成功");
        } catch (MailException e) {
            System.out.println("邮件发送失败");
        }

    }
    /**
     * Description: 发送html邮件
     * @Author: ColorXJH
     * @Date: 2022/9/7 15:49
     * @param
     * @Return: void
     **/
    public void sendHTMLMail(){
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper=new MimeMessageHelper(message,true);
            helper.setFrom(SEND_FROM);
            helper.setTo(SEND_TO);
            message.setSubject("--这是一封html邮件--");
            String htmlStr="<a href='https://www.baidu.com'>你好啊百度</a>";
            helper.setText(htmlStr,true);
            mailSender.send(message);
            System.out.println("--html消息发送成功--");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("--html消息发送失败--");
        }
    }

    /**
     * Description: 发送带附件邮件
     * @Author: ColorXJH
     * @Date: 2022/9/7 15:49
     * @param
     * @Return: void
     **/
    public void sendMineMail(){
        MimeMessage message=mailSender.createMimeMessage();
        String filePath="F:\\BaiduNetdiskDownload\\[JAVA解惑]（美）布洛赫.pdf";
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(SEND_FROM);
            helper.setTo(SEND_TO);
            helper.setSubject("--这是一个带附件的邮件--");
            helper.setText("--邮件内容--",true);
            FileSystemResource fileSystemResource=new FileSystemResource(new File(filePath));
            String fileName=fileSystemResource.getFilename();
            assert fileName !=null;
            helper.addAttachment(fileName,fileSystemResource);
            mailSender.send(message);
            System.out.println("--带附件邮件发送成功--");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("--带附件邮件发送失败--");
        }
    }
    /**
     * Description: 发送给带静态文件邮件
     * @Author: ColorXJH
     * @Date: 2022/9/7 15:49
     * @param
     * @Return: void
     **/
    public void sendStaticResourceMail(){
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(SEND_FROM);
            helper.setTo(SEND_TO);
            helper.setSubject("--发送静态资源邮件主题--");
            String htmlStr = "<html><body>测试：图片1 <br> <img src='cid:pic1'/> <br>图片2 <br> <img src='cid:pic2'/></body></html>";
            Map<String, String> rscIdMap = new HashMap<>(2);
            rscIdMap.put("pic1","F:\\360\\001.jpg");
            rscIdMap.put("pic2", "F:\\360\\002.jpg");
            helper.setText(htmlStr,true);
            for (Map.Entry<String, String> entry : rscIdMap.entrySet()) {
                FileSystemResource file = new FileSystemResource(new File(entry.getValue()));
                helper.addInline(entry.getKey(), file);
            }
            mailSender.send(message);
            System.out.println("--带静态资源邮件发送成功--");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("--带静态资源邮件发送失败--");
        }

    }


}
