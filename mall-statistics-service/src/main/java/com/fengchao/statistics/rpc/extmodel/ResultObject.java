package com.fengchao.statistics.rpc.extmodel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
//import lombok.AllArgsConstructor;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
//@AllArgsConstructor
@Getter
public class ResultObject<T>{
    private Integer code;
    private String msg;
    private T data;

    public ResultObject(Integer code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String message) {
        this.msg = message;
    }

    public void setData(T data) {
        this.data= data;
    }
}
