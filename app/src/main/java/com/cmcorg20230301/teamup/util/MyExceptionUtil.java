package com.cmcorg20230301.teamup.util;

import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.util.common.LogUtil;

public class MyExceptionUtil {

    /**
     * 打印异常日志
     */
    public static void printError(Throwable e) {

        LogUtil.error("异常日志打印：页面：{}", BaseActivity.CURRENT_ACTIVITY.getClass().getName(), e);

    }

    /**
     * 打印异常日志-http
     */
    public static void printHttpError(Throwable e, String url) {

        LogUtil.error("异常日志打印：页面：{}，url：{}", BaseActivity.CURRENT_ACTIVITY.getClass().getName(), url, e);

    }

}
