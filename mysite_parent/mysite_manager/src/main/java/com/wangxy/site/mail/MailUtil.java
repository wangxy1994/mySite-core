package com.wangxy.site.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * 邮件工具类
 * @author Administrator
 *
 */
@Component
public class MailUtil {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment env;

    public Boolean sendMailMsg(String targetUser,String subject,String content){
        String host = env.getProperty("mail.host");
        String protocol = env.getProperty("spring.mail.transport.protocol");
        Integer port = env.getProperty("spring.mail.smtp.port", Integer.class);
        String username = env.getProperty("spring.mail.username");
        String password = env.getProperty("spring.mail.password");
        String charset = env.getProperty("spring.mail.default-encoding");

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            sendHtmlMail(message, username, targetUser, subject, content);
            javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }
    //发送普通文本格式邮件
    public static void sendTextMail(MimeMessage message, String username, String recipient, String subject, List<String> content){
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(recipient);
            helper.setSubject(subject);
            StringBuffer sb = new StringBuffer();
            sb.append(content);
            helper.setText(sb.toString());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    //发送html格式邮件
    public void sendHtmlMail(MimeMessage message,String username,String recipient, String subject, String content) throws MessagingException {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(content, true);
    }

/*
    public  QuerySendDetailsResponse querySendDetails(String mail,String bizId) throws ClientException {
        String accessKeyId =env.getProperty("accessKeyId");
        String accessKeySecret = env.getProperty("accessKeySecret");
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(mail);
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);
        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
        return querySendDetailsResponse;
    }*/
}