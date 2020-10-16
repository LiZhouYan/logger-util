package com.logger.util.service;

import com.logger.util.bean.LoggerLevel;
import com.logger.util.constant.LogConstant;
import com.logger.util.constant.LogLevelConstant;
import com.logger.util.enums.ELogFrameworkType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @Desc    日志服务抽象类
 * @Author zhouguanglin
 * @Email zhouguanglin_java@163.com
 * @Date 2020/9/28 2:34 下午
 */
public abstract class AbstractLoggerService implements LoggerService {
    private Logger log = LoggerFactory.getLogger(AbstractLoggerService.class);

    /**
     * 存放所有日志  key：日志名称 ，value：不同log类型的Logger类
     */
    protected static final ConcurrentHashMap<String, Object> loggerMap = new ConcurrentHashMap<>();

    /**
     * 默认日志等级
     */
    protected String defaultLevel = LogLevelConstant.INFO;

    /**
     * 日志框架类型
     */
    public static ELogFrameworkType eLogFrameworkType;

    public AbstractLoggerService() {
    }

    @Override
    public void setDefaultLevel(String defaultLevel) {
        this.defaultLevel = defaultLevel;
    }

    /**
     * 检查所有日志状态
     *
     * @param logLevel
     * @return
     */
    public String checkAllLoggerStatus(String logLevel) {
        LoggerLevel loggerLevel = new LoggerLevel(LogConstant.LOGGER_ALL_NAME,logLevel);
        return checkLoggerStatus(loggerLevel);
    }

    /**
     * 检查日志状态
     * @param loggerLevel
     * @return
     */
    public String checkLoggerStatus(LoggerLevel loggerLevel) {
        // 如果为NULL就是默认等级
        if (null == loggerLevel.getLevel()) {
            loggerLevel.setLevel(defaultLevel);
        }
        log.info("日志框架:" + eLogFrameworkType + ",调整对象:" + loggerLevel.getName() + ",LoggerService 设置Log级别:" + loggerLevel.getLevel());
        if (null == loggerMap || loggerMap.isEmpty()) {
            log.warn(" LoggerService 当前工程中不存在任何Logger,无法调整Logger级别");
        }
        return loggerLevel.getLevel();
    }

    /**
     * 检查日志列表状态
     * @param loggerLevels
     */
    public void checkLoggerListStatus(List<LoggerLevel> loggerLevels) {
        if (loggerLevels == null || loggerLevels.isEmpty()) {
            throw new RuntimeException(LogConstant.LOGGER_LIST_IS_NULL);
        }
        for (LoggerLevel loggerLevel:loggerLevels) {
            checkLoggerStatus(loggerLevel);
        }
    }

    @Override
    public void setLogLevel(LoggerLevel loggerLevel) {
        List<LoggerLevel> loggerLevels = new ArrayList<>();
        loggerLevels.add(loggerLevel);
        this.setLogLevel(loggerLevels);
    }
}
