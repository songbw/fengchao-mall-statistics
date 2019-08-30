package com.fengchao.statistics.utils;

import cn.hutool.extra.mail.MailUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author tom
 * @Date 19-8-30 下午6:24
 */
@Slf4j
public class FengchaoMailUtil {

    public void send(List<String> receivers, String subject, String content) {
        log.info("发送邮件通知; tos={}, title={}, content={}", receivers, subject, content);

        // 发送邮件
        MailUtil.send(receivers, subject, content, false);

        log.info("{} 告警邮件发送成功", subject);
    }
}
