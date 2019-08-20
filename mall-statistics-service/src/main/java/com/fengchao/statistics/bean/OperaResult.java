package com.fengchao.statistics.bean;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class OperaResult implements Serializable {
	
	// 结果码
	private Integer code = 200;
	
	// 结果描述(消息框的内容)
	private String msg = "Success";
	
	//封装的对象
	private Map<String,Object> data = new HashMap<String,Object>();
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
