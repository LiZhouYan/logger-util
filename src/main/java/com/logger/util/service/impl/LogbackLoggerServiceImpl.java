package com.logger.util.service.impl;

import com.logger.util.enums.ELogFrameworkType;
import com.logger.util.service.AbstractLoggerService;
import com.logger.util.bean.LoggerLevel;
import com.logger.util.constant.LogConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0
 * @Desc Logback日志服务
 * @Author zhouguanglin
 * @Email zhouguanglin_java@163.com
 * @Date 2020/9/28 3:18 下午
 */
public class LogbackLoggerServiceImpl extends AbstractLoggerService {
    private Logger log = LoggerFactory.getLogger(LogbackLoggerServiceImpl.class);

    /**
     * 初始化日志列表
     */
    public LogbackLoggerServiceImpl() {
        eLogFrameworkType = ELogFrameworkType.LOGBACK;
        ch.qos.logback.classic.LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory.getILoggerFactory();
        for (ch.qos.logback.classic.Logger logger : loggerContext.getLoggerList()) {
            //若无等级则设置默认 info
            if (logger.getLevel() == null) {
                ch.qos.logback.classic.Level targetLevel = ch.qos.logback.classic.Level.toLevel(defaultLevel);
                logger.setLevel(targetLevel);
            }
            loggerMap.put(logger.getName(), logger);
        }
        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        loggerMap.put(rootLogger.getName(), rootLogger);
    }

    @Override
    public void setLogLevel(String logLevel) {
        logLevel = super.checkAllLoggerStatus(logLevel);
        Set<Map.Entry<String, Object>> entries = loggerMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            Object logger = entry.getValue();
            // 如果为NULL就是默认等级
            if (null == logger) {
                throw new RuntimeException(LogConstant.LOGGER_NOT_EXSIT);
            }
            ch.qos.logback.classic.Logger targetLogger = (ch.qos.logback.classic.Logger) logger;
            ch.qos.logback.classic.Level targetLevel = ch.qos.logback.classic.Level.toLevel(logLevel);
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
            ch.qos.logback.classic.Logger targetLogger = (ch.qos.logback.classic.Logger) logger;
            ch.qos.logback.classic.Level targetLevel = ch.qos.logback.classic.Level.toLevel(loggerLevel.getLevel());
            targetLogger.setLevel(targetLevel);
        }
        log.info("LoggerService end: loggerLevels = 【{}】", loggerLevels.toString());
    }
}
