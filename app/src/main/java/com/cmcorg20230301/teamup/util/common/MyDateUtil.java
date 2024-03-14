package com.cmcorg20230301.teamup.util.common;

import java.util.Date;

import cn.hutool.core.date.DateUtil;

public class MyDateUtil {

    /**
     * 通过日期，获取 Cron 表达式
     */
    public static String getCron(Date date) {

        return DateUtil.format(date, "ss mm HH dd MM ? yyyy");

    }

    /**
     * 获取：服务器的时间戳，目的：防止不同地区的时间差，保证和服务器的时间一致
     */
    public static long getServerTimestamp() {

        return System.currentTimeMillis();

    }

    /**
     * 格式化时间，如果是今天，则不显示年月日
     */
    public static String formatDateTimeForCurrentDay(Date date) {

        long currentDay = getServerTimestamp() / 86400000;

        long checkDay = date.getTime() / 86400000;

        if (currentDay == checkDay) {

            return DateUtil.formatTime(date);

        }

        return DateUtil.formatDateTime(date);

    }

}
