package com.application.template.util;

import com.application.template.exceptionHandle.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class EmailUtil {

    private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    private EmailUtil() {
        throw new AppException("不允许构造");
    }

    /**
     * 发送纯文本邮件信息
     *
     * @param receiver 接收方
     * @param title 邮件主题
     * @param content 邮件内容（发送内容）
     * @param sender 发送者
     */
    public static void sendEmail(String title,String content, String receiver, String sender) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setText(content);
        message.setSubject(title);
        message.setFrom(sender);
        message.setTo(receiver);
        SpringUtil.getBean(JavaMailSender.class).send(message);
    }

    /**
     * 发送纯文本邮件信息
     *
     * @param receiver 接收方
     * @param title 邮件主题
     * @param content 邮件内容（发送内容）
     * @param sender 发送者
     * @param attachments 所有附件
     */
    public static void sendEmailWithFiles(String title, String content, String receiver, String sender, List<File> attachments) {
        JavaMailSender mailSender = SpringUtil.getBean(JavaMailSender.class);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setText(content);
            helper.setSubject(title);
            helper.setFrom(sender);
            helper.setTo(receiver);
            attachments.forEach(addAttachment(helper));
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.info("发送邮件失败,主题为{},接收者为{}", title, receiver);
            throw new AppException("发送邮件失败");
        }
    }

    private static Consumer<File> addAttachment(MimeMessageHelper helper) {
        return attachment -> {
            try {
                helper.addAttachment(attachment.getName(), attachment);
            } catch (MessagingException e) {
                e.printStackTrace();
                throw new AppException("发送邮件失败");
            }
        };
    }
}
