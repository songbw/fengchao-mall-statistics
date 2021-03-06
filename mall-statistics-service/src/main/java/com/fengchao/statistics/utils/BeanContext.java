package com.fengchao.statistics.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 多线程手动加载bean
 */
@Component
public class BeanContext  implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T)applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clz) throws BeansException {
        return (T)applicationContext.getBean(clz);
    }

    /**
     *
     * @return
     */
    public static String getProfile() {
        String profile = BeanContext.getApplicationContext().getEnvironment().getActiveProfiles()[0];

        return profile;
    }
}
