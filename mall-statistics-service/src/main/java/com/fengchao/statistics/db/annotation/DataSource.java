package com.fengchao.statistics.db.annotation;

import com.fengchao.statistics.db.config.DataSourceNames;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String value() default DataSourceNames.ONE;
}
