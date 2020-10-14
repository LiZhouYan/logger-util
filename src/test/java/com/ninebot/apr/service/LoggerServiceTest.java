package com.ninebot.apr.service;

import com.ninebot.apr.bean.LoggerLevel;
import com.ninebot.apr.constant.LogLevelConstant;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 1.0
 * @Desc    日志服务测试
 * @Author zhouguanglin
 * @Email guanglin.zhou@ninebot.com
 * @Date 2020/9/29 10:22 上午
 */
public class LoggerServiceTest {
    Logger log = LoggerFactory.getLogger(LoggerServiceTest.class);
    Logger log2 = LoggerFactory.getLogger("tiantianbanzhuan");

    @Test
    public void loggerUtil(){
        log.info("this is info");
        log.debug("this is debug1");
        log2.info(" log2 this is info");
        log2.debug("log2 this is debug1");
        AbstractLoggerService loggerService = LoggerUtilFactory.getLoggerService();
        loggerService.setLogLevel(LogLevelConstant.INFO);
        log.info("this is info2");
        log.debug("this is debug2");
        log2.info(" log2 this is info2");
        log2.debug("log2 this is debug2");
        loggerService.setLogLevel(LogLevelConstant.DEBUG);
        log.debug("this is debug3");
        log2.debug("log2 this is debug3");
        LoggerLevel loggerLevel = new LoggerLevel(LoggerServiceTest.class,LogLevelConstant.INFO);
        loggerService.setLogLevel(loggerLevel);
        log.debug("this is debug4");
        log2.debug("log2 this is debug4");
    }
}
