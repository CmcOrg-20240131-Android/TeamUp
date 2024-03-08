package com.cmcorg20230301.teamup.util.common;

import cn.hutool.core.date.DateUtil;
import java.util.Date;

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

}
