package com.cmcorg20230301.teamup.exception;

import com.cmcorg20230301.teamup.util.MyExceptionUtil;

import androidx.annotation.NonNull;

/**
 * 线程异常捕获
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static final MyUncaughtExceptionHandler MY_UNCAUGHT_EXCEPTION_HANDLER = new MyUncaughtExceptionHandler();

    private MyUncaughtExceptionHandler() {}

    public static MyUncaughtExceptionHandler getInstance() {
        return MY_UNCAUGHT_EXCEPTION_HANDLER;
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {

        MyExceptionUtil.printError(e);

    }

}
