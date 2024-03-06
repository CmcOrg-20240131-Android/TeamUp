package com.cmcorg20230301.teamup.util;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.lang.func.VoidFunc0;
import cn.hutool.core.lang.func.VoidFunc1;
import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.NamedThreadFactory;

public class MyThreadUtil {

    private final static ScheduledThreadPoolExecutor SCHEDULED_THREAD_POOL_EXECUTOR;

    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR;

    static {

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        NamedThreadFactory myTaskExecutorThreadFactory = new NamedThreadFactory("myTaskExecutor-",
            false);

        NamedThreadFactory myTaskSchedulerThreadFactory = new NamedThreadFactory("myTaskScheduler-",
            false);

        THREAD_POOL_EXECUTOR = ExecutorBuilder.create().useSynchronousQueue()
            .setCorePoolSize(availableProcessors * 10).setThreadFactory(myTaskExecutorThreadFactory)
            .build();

        SCHEDULED_THREAD_POOL_EXECUTOR = new ScheduledThreadPoolExecutor(availableProcessors * 10,
            myTaskSchedulerThreadFactory);

    }

    /**
     * 异步执行
     */
    public static void execute(VoidFunc0 voidFunc0) {

        THREAD_POOL_EXECUTOR.execute(() -> TryUtil.tryCatch(voidFunc0));

    }

    /**
     * 异步执行
     */
    public static void execute(VoidFunc0 voidFunc0, @Nullable CountDownLatch countDownLatch) {

        execute(voidFunc0, countDownLatch, null, null);

    }

    /**
     * 异步执行
     */
    public static void execute(VoidFunc0 voidFunc0, @Nullable CountDownLatch countDownLatch,
        @Nullable VoidFunc1<Throwable> exceptionVoidFunc1, @Nullable VoidFunc0 finallyVoidFunc0) {

        execute(() -> {

            TryUtil.tryCatchFinally(voidFunc0, exceptionVoidFunc1, () -> {

                if (countDownLatch != null) {

                    countDownLatch.countDown();

                }

                TryUtil.execVoidFunc0(finallyVoidFunc0);

            });

        });

    }

    /**
     * 提交任务调度请求
     *
     * @param voidFunc0 待执行任务
     */
    public static ScheduledFuture<?> schedule(VoidFunc0 voidFunc0,
        long delay, TimeUnit unit) {

        return SCHEDULED_THREAD_POOL_EXECUTOR.schedule(() -> TryUtil.tryCatch(voidFunc0), delay,
            unit);

    }

    /**
     * 提交任务调度请求
     *
     * @param voidFunc0 待执行任务
     */
    public static ScheduledFuture<?> scheduleAtFixedRate(VoidFunc0 voidFunc0,
        long initialDelay,
        long period, TimeUnit unit) {

        return SCHEDULED_THREAD_POOL_EXECUTOR.scheduleAtFixedRate(() -> TryUtil.tryCatch(voidFunc0),
            initialDelay, period, unit);

    }

    /**
     * 提交任务调度请求
     *
     * @param voidFunc0 待执行任务
     */
    public static ScheduledFuture<?> scheduleWithFixedDelay(VoidFunc0 voidFunc0,
        long initialDelay,
        long delay, TimeUnit unit) {

        return SCHEDULED_THREAD_POOL_EXECUTOR.scheduleWithFixedDelay(
            () -> TryUtil.tryCatch(voidFunc0), initialDelay, delay, unit);

    }

}
