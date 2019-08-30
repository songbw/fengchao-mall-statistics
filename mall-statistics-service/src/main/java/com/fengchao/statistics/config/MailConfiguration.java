package com.fengchao.statistics.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * @Author tom
 * @Date 19-8-30 下午6:30
 */
@Configuration
@PropertySource("classpath:mailreceivers.properties")
@Setter
@Getter
public class MailConfiguration {

    /**
     *
     */
    List<String> receivers;
}
