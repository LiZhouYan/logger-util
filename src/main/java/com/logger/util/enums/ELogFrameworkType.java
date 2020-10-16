package com.logger.util.enums;

import com.logger.util.service.impl.Log4j2LoggerServiceImpl;
import com.logger.util.service.impl.Log4jLoggerServiceImpl;
import com.logger.util.service.impl.LogbackLoggerServiceImpl;

import java.util.Objects;

/**
 * @version 1.0
 * @Desc    日志框架类型
 * @Author zhouguanglin
 * @Email zhouguanglin_java@163.com
 * @Date 2020/9/28 2:49 下午
 */
public enum ELogFrameworkType {
    /**
     * log4j框架
     */
    LOG4J("LOG4J","org.slf4j.impl.Log4jLoggerFactory", Log4jLoggerServiceImpl.class)
    /**
     * log4j2框架
     */
    , LOG4J2("LOG4J2","org.apache.logging.slf4j.Log4jLoggerFactory", Log4j2LoggerServiceImpl.class)
    /**
     * logback框架
     */
    , LOGBACK("LOGBACK","ch.qos.logback.classic.util.ContextSelectorStaticBinder", LogbackLoggerServiceImpl.class)
    /**
     * 未知日志框架
     */
    , UNKNOWN(null,null,null);

    private String type;

    private String logClassStr;

    private Class<?> className;

    ELogFrameworkType(String type, String logClassStr, Class<?> className) {
        this.type = type;
        this.logClassStr = logClassStr;
        this.className = className;
    }

    public String getType() {
        return type;
    }

    public String getLogClassStr() {
        return logClassStr;
    }

    public Class<?> getClassName() {
        return className;
    }


    public static ELogFrameworkType getELogFrameworkType(String logClassStr) {
        for (ELogFrameworkType type : ELogFrameworkType.values()) {
            if (Objects.equals(type.getLogClassStr(),logClassStr)) {
                return type;
            }
        }
        return null;
    }
}
