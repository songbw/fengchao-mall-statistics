package com.fengchao.statistics;

import com.fengchao.statistics.db.config.DynamicDataSourceConfig;
import com.github.ltsopensource.spring.boot.annotation.EnableJobClient;
import com.github.ltsopensource.spring.boot.annotation.EnableTaskTracker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@EnableTaskTracker
@EnableJobClient
@EnableFeignClients
@EnableDiscoveryClient
@Import({DynamicDataSourceConfig.class})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@MapperScan({"com.fengchao.statistics.mapper"})
public class StatisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticsApplication.class, args);
	}

}
