package com.cmcorg20230301.teamup.util;

public class MyExceptionUtil {

    /**
     * 打印异常日志
     */
    public static void printError(Throwable e) {

        LogUtil.error("异常日志打印：{}", e);

    }

}
