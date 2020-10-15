package com.ninebot.apr.service;

import com.ninebot.apr.enums.ELogFrameworkType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

/**
 * @version 1.0
 * @Desc 日志工具工厂
 * @Author zhouguanglin
 * @Email zhouguanglin_java@163.com
 * @Date 2020/9/28 4:59 下午
 */
public class LoggerUtilFactory {
    private static Logger log = LoggerFactory.getLogger(LoggerUtilFactory.class);
    /**
     * 获取日志服务
     * @return
     */
    public static AbstractLoggerService getLoggerService() {
        String type = StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr();
        ELogFrameworkType eLogFrameworkType = ELogFrameworkType.getELogFrameworkType(type);
        log.info("日志框架:" + eLogFrameworkType + ",日志类路径:" + eLogFrameworkType.getLogClassStr() + ",日志实现类:" + eLogFrameworkType.getClassName().getName());
        AbstractLoggerService abstractLoggerService = null;
        try {
            abstractLoggerService = (AbstractLoggerService) Class.forName(eLogFrameworkType.getClassName().getName()).newInstance();
        } catch (Exception e) {
            log.error("创建日志服务失败：", e);
        }
        return abstractLoggerService;
    }
}
