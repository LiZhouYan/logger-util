package com.logger.util.service.impl;

import com.logger.util.bean.LoggerLevel;
import com.logger.util.constant.LogConstant;
import com.logger.util.enums.ELogFrameworkType;
import com.logger.util.service.AbstractLoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version 3.0
 * @Desc Log4j日志类型
 * @Author zhouguanglin
 * @Email zhouguanglin_java@163.com
 * @Date 2020/9/28 2:54 下午
 */
public class Log4jLoggerServiceImpl extends AbstractLoggerService {
    private Logger log = LoggerFactory.getLogger(Log4jLoggerServiceImpl.class);
    /**
     * 初始化日志列表
     */
    public Log4jLoggerServiceImpl() {
        eLogFrameworkType = ELogFrameworkType.LOG4J;
        Enumeration<?> enumeration = org.apache.log4j.LogManager.getCurrentLoggers();
        while (enumeration.hasMoreElements()) {
            org.apache.log4j.Logger logger = (org.apache.log4j.Logger) enumeration.nextElement();
            //若无等级则设置默认 info
            if (logger.getLevel() == null) {
                org.apache.log4j.Level targetLevel = org.apache.log4j.Level.toLevel(defaultLevel);
                logger.setLevel(targetLevel);
            }
            loggerMap.put(logger.getName(), logger);
        }
        org.apache.log4j.Logger rootLogger = org.apache.log4j.LogManager.getRootLogger();
        loggerMap.put(rootLogger.getName(), rootLogger);
    }

    @Override
    public void setLogLevel(String logLevel) {
        logLevel = super.checkAllLoggerStatus(logLevel);
        Set<Map.Entry<String, Object>> entries = loggerMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            Object logger = entry.getValue();
            if (null == logger) {
                throw new RuntimeException(LogConstant.LOGGER_NOT_EXSIT);
            }
            org.apache.log4j.Logger targetLogger = (org.apache.log4j.Logger) logger;
            org.apache.log4j.Level targetLevel = org.apache.log4j.Level.toLevel(logLevel);
            targetLogger.setLevel(targetLevel);
        }
    }

    @Override
    public void setLogLevel(List<LoggerLevel> loggerLevels) {
        log.info("LoggerService start: loggerLevels = 【{}】", loggerLevels.toString());
        for (LoggerLevel loggerLevel : loggerLevels) {
            super.checkLoggerStatus(loggerLevel);
            Object logger = loggerMap.get(loggerLevel.getName());
            if (null == logger) {
                throw new RuntimeException(LogConstant.LOGGER_NOT_EXSIT);
            }
            org.apache.log4j.Logger targetLogger = (org.apache.log4j.Logger) logger;
            org.apache.log4j.Level targetLevel = org.apache.log4j.Level.toLevel(loggerLevel.getLevel());
            targetLogger.setLevel(targetLevel);
        }
        log.info("LoggerService end: loggerLevels = 【{}】", loggerLevels.toString());
    }
}
