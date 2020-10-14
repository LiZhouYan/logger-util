package com.ninebot.apr.bean;

/**
 * @version 1.0
 * @Desc 日志等级实体
 * @Author zhouguanglin
 * @Email guanglin.zhou@ninebot.com
 * @Date 2020/9/28 2:15 下午
 */
public class LoggerLevel {
    /**
     * 对应日志名称
     */
    private String name;
    /**
     * 日志等级
     */
    private String level;

    public LoggerLevel() {
    }

    public LoggerLevel(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public LoggerLevel(Class<?> className, String level) {
        this.name = className.getName();
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "{\"LoggerBean\":{" + "\"name\":\"" + name +
                "\"" + ",\"level\":\"" + level + "\"" + "}}";
    }
}
