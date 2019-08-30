package com.fengchao.statistics.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author tom
 * @Date 19-8-30 下午6:30
 */
@Configuration
@PropertySource("classpath:mail.setting")
@Slf4j
public class MailConfiguration {

    /**
     *
     */
    @Value("${mail.receivers}")
    public static List<String> receivers;

//    @PostConstruct//在servlet初始化的时候加载，并且只加载一次，和构造代码块的作用类似
//    private void init(){
//        log.info("load env.properties start!");
//
//        log.info("load env.properties end!");
//    }


}
