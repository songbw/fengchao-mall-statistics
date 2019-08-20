package com.fengchao.statistics.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailUtil;
import com.fengchao.statistics.utils.BeanContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @Author tom
 * @Date 19-8-2 下午5:35
 */
@RestController
@RequestMapping(value = "/cat", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class CatAlarmController {

    @GetMapping("/alarm")
    public void alarm(String type, String key, String re, String to, String title, String content) {
        log.info("发送告警邮件; type={}, key={}, re={}, to={}, title={}, content={}", type, key, re, to, title, content);
        String profile = BeanContext.getApplicationContext().getEnvironment().getActiveProfiles()[0];
        title = profile + "-" + title;

        // 发送邮件
        String []mail = to.split(",");

        ArrayList<String> tos = CollUtil.newArrayList(mail);

        MailUtil.send(tos, title, content, false);

        log.info("{} 告警邮件发送成功", title);
    }

    @GetMapping("/mail")
    public String testmail() {
        log.info("======asdfasdfasdf");
        MailUtil.send("peng.zhang@weesharing.com", "测试", "邮件来自Hutool测试", false);
        log.info("============end");

        return "finish ssss";
    }


    public static void main(String args[]) {
        // new CatAlarmController().alarm("1", "2", "3", "tom.jing@weesharing.com,peng.zhang@weesharing.com,jon.zhou@weesharing.com,", "asdf123", "asdf");
    }
}
