package com.fengchao.statistics.utils;

import cn.hutool.extra.mail.MailUtil;
import com.fengchao.statistics.config.MailConfiguration;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author tom
 * @Date 19-8-30 下午6:24
 */
@Slf4j
public class FengchaoMailUtil {

    public static void send(String subject, String content) {
        // 发送邮件
        send(MailConfiguration.receivers, subject, content);
    }

    public static void send(List<String> receivers, String subject, String content) {
        log.info("发送邮件通知; tos={}, title={}, content={}", receivers, subject, content);

        // 发送邮件
        subject = subject + "-" + BeanContext.getProfile();
        MailUtil.send(receivers, subject, content, false);

        log.info("{} 告警邮件发送成功", subject);
    }
}
