package com.cmcorg20230301.teamup.util.common;

import android.util.Log;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 日志工具类
 */
public class LogUtil {

    public static final String TAG = "TeamUp";

    public static void debug(CharSequence template, Object... params) {

        Log.d(TAG, "debug: " + StrUtil.format(template, params));

    }

    public static void error(CharSequence template, Object... params) {

        Object[] paramsArr = params;

        if (ArrayUtil.isNotEmpty(paramsArr)) {

            Object lastParam = paramsArr[paramsArr.length - 1];

            if (lastParam instanceof Throwable) {

                paramsArr = ArrayUtil.remove(paramsArr, paramsArr.length - 1);

                Log.e(TAG, "error: " + StrUtil.format(template, paramsArr), (Throwable) lastParam);

                return;

            }

        }

        Log.e(TAG, "error: " + StrUtil.format(template, paramsArr));

    }

}
