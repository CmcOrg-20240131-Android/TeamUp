package com.cmcorg20230301.teamup.util;

import com.cmcorg20230301.teamup.BaseActivity;

public class MyExceptionUtil {

    /**
     * 打印异常日志
     */
    public static void printError(Throwable e) {

        LogUtil.error("异常日志打印：页面：{}，异常：{}", BaseActivity.CURRENT_ACTIVITY.getClass().getName(), e);

    }

}
