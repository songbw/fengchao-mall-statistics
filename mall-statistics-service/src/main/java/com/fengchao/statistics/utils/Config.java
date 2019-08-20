package com.fengchao.statistics.utils;

import java.util.ResourceBundle;

public class Config {
  /**
   * 
   * @author songbw
   */
  private static final ResourceBundle config = ResourceBundle.getBundle("config");
  

  /**
     *
     */
  private Config() {
  }
  
  /**
   * 
   * @param key
   * @return
   */
  public static String getString(String key) {
    return config.getString(key);
  }

  public static int getInt(String key) {
    String temp = config.getString(key);
    int value = Integer.parseInt(temp);
    return value;
  }
  
  public static boolean getBoolean(String key) {
	    String temp = config.getString(key);
	    Boolean value = Boolean.parseBoolean(temp);
	    return value;
	  }
  


}