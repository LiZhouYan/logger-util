package com.ninebot.apr.constant;

/**
 * @version 1.0
 * @Desc    日志等级类型
 * @Author zhouguanglin
 * @Email zhouguanglin_java@163.com
 * @Date 2020/9/28 4:20 下午
 */
public class LogLevelConstant {
    /**
     * 是最高等级的，用于关闭所有日志记录
     */
    public static final String OFF = "OFF";
    /**
     * 致命错误：指出每个严重的错误事件将会导致应用程序的退出
     */
    public static final String FATAL = "FATAL";
    /**
     * 错误：指出虽然发生错误事件，但仍然不影响系统的继续运行
     */
    public static final String ERROR = "ERROR";
    /**
     * 警告：表明会出现潜在错误的情形
     */
    public static final String WARN = "WARN";
    /**
     * 信息：粗粒度级别上突出强调应用程序的运行过程
     */
    public static final String INFO = "INFO";
    /**
     * 调试：细粒度信息事件对调试应用程序
     */
    public static final String DEBUG = "DEBUG";
    /**
     * 相比更细致化的记录事件消息
     */
    public static final String TRACE = "TRACE";
    /**
     * 是最低等级的，用于打开所有日志记录
     */
    public static final String ALL = "ALL";
}
