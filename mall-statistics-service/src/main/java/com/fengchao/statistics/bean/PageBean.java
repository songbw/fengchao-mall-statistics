package com.fengchao.statistics.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Created by song on 2018-03-12.
 */
@Data
public class PageBean {
    private Object list = null;
    private int total = 0;
    private int pages = 0;
    private int pageNo = 0;
    private int pageSize = 0;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object object = null;

    public static Integer getPages(Integer total, Integer pageSize) {
        Integer a = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        return a;
    }

    public static Integer getOffset(Integer pageNo, Integer pageSize) {
        Integer a = (pageNo - 1) * pageSize;
        return a;
    }

    public static <T> PageBean build(PageBean p, List<T> list, int total, int pageNo, int pageSize) {
        p.setList(list);
        p.setPages(PageBean.getPages(total, pageSize));
        p.setTotal(total);
        p.setPageNo(pageNo);
        p.setPageSize(pageSize);
        return p;
    }

    public static <T> PageBean build(PageBean p, Object object, int total, int pageNo, int pageSize) {
        p.setObject(object);
        p.setTotal(total);
        p.setPages(PageBean.getPages(total, pageSize));
        p.setPageNo(pageNo);
        p.setPageSize(pageSize);
        return p;
    }
}
