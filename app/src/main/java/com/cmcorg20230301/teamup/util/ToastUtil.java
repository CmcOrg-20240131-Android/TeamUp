package com.cmcorg20230301.teamup.util;

import android.widget.Toast;

import com.cmcorg20230301.teamup.BaseActivity;

/**
 * 提示工具类
 */
public class ToastUtil {

    private static Toast oldToast = null; // 记录上一个 toast

    /**
     * 短显示
     */
    public static void makeTextShort(CharSequence text) {
        makeText(text, Toast.LENGTH_SHORT);
    }

    /**
     * 长显示
     */
    public static void makeTextLong(CharSequence text) {
        makeText(text, Toast.LENGTH_LONG);
    }

    public synchronized static void makeText(CharSequence text, int duration) {

        Toast toast = Toast.makeText(BaseActivity.CURRENT_ACTIVITY, text, duration);

        if (oldToast != null) {
            oldToast.cancel();
        }

        oldToast = toast;

        toast.show();

    }

}
