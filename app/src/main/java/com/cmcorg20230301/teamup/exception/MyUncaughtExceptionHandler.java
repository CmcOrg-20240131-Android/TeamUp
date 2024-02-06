package com.cmcorg20230301.teamup.exception;

import androidx.annotation.NonNull;

/**
 * 线程异常捕获
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {

    }

}
