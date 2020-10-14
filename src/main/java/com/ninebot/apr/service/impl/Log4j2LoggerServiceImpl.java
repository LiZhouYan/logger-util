package com.ninebot.apr.service.impl;

import com.ninebot.apr.bean.LoggerLevel;
import com.ninebot.apr.constant.LogConstant;
import com.ninebot.apr.enums.ELogFrameworkType;
import com.ninebot.apr.service.AbstractLoggerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * @version 3.0
 * @Desc    Log4j日志
 * @Author zhouguanglin
 * @Email guanglin.zhou@ninebot.com
 * @Date 2020/9/28 3:11 下午
 */
public class Log4j2LoggerServiceImpl extends AbstractLoggerService {
    private Logger log = LoggerFactory.getLogger(Log4j2LoggerServiceImpl.class);
    /**
     * 初始化日志列表
     */
    public Log4j2LoggerServiceImpl() {
        eLogFrameworkType = ELogFrameworkType.LOG4J2;
        org.apache.logging.log4j.core.LoggerContext loggerContext = (org.apache.logging.log4j.core.LoggerContext) org.apache.logging.log4j.LogManager.getContext(false);
        Collection<org.apache.logging.log4j.core.Logger> loggers = loggerContext.getLoggers();
        for (org.apache.logging.log4j.core.Logger logger : loggers) {
            String key = logger.getName();
            if (StringUtils.isBlank(key)) {
                key = LogConstant.ROOT_KEY;
            }
            loggerMap.put(key, logger);
        }
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
            org.apache.logging.log4j.core.Logger logger0 = (org.apache.logging.log4j.core.Logger) logger;
            org.apache.logging.log4j.Level targetLevel = org.apache.logging.log4j.Level.toLevel(logLevel);
            logger0.setLevel(targetLevel);
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
            org.apache.logging.log4j.core.Logger logger0 = (org.apache.logging.log4j.core.Logger) logger;
            org.apache.logging.log4j.Level targetLevel = org.apache.logging.log4j.Level.toLevel(loggerLevel.getLevel());
            logger0.setLevel(targetLevel);
        }
        log.info("LoggerService end: loggerLevels = 【{}】", loggerLevels.toString());
    }
}
