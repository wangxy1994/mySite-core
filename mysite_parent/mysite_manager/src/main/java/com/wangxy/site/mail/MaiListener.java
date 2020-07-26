package com.wangxy.site.mail;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 邮件监听类
 */
@Component
@RabbitListener(queues = "sms")
public class MaiListener {

    @Autowired
    private MailUtil mailUtil;

    @RabbitHandler
    public void sendSms(Map<String,String> map){
        System.out.println("邮箱："+map.get("mail"));
        System.out.println("验证码："+map.get("code"));
        //{"number":"1213213"}
        String content = "<html><head><style>td{border:solid #add9c0; border-width:0px 1px 1px 0px; padding:10px 0px;} table{border:solid #add9c0; border-width:1px 0px 0px 1px;}</style></head><body style=''><h3>欢迎注册！您的验证码为："+
                map.get("code")+"</h3>" +"</body></html>";

            mailUtil.sendMailMsg(map.get("mail"),"wangxy.cn注册验证",content);

    }

}
