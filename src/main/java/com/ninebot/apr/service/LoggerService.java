package com.ninebot.apr.service;

import com.ninebot.apr.bean.LoggerLevel;

import java.util.List;

/**
 * @version 1.0
 * @Desc
 * @Author zhouguanglin
 * @Email zhouguanglin_java@163.com
 * @Date 2020/9/28 2:27 下午
 */
public interface LoggerService {
    /**
     * 默认等级
     * @param defaultLevel
     */
      void setDefaultLevel(String defaultLevel);

    /**
     * 设置全局日志等级
     * @param logLevel
     */
     void setLogLevel(String logLevel);

    /**
     * 定向设置日志等级
     * @param loggerLevels 日志列表
     */
     void setLogLevel(List<LoggerLevel> loggerLevels);


    /**
     * 定向设置日志等级
     * @param loggerLevel 日志列表
     */
    void setLogLevel(LoggerLevel loggerLevel);


}
