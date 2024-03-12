package com.cmcorg20230301.teamup.layout;

import java.util.Map;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.exception.MyUncaughtExceptionHandler;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.model.enums.AppDispatchKeyEnum;
import com.cmcorg20230301.teamup.util.common.LogUtil;
import com.cmcorg20230301.teamup.util.common.StatusBarUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;

/**
 * 所有 activity都需要继承本类
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static Window WINDOW;

    public static Context CONTEXT;

    public static BaseActivity CURRENT_ACTIVITY;

    /**
     * 状态栏颜色
     */
    public @ColorRes Integer getStatusBarColorId() {
        return null;
    }

    /**
     * 状态栏字体和图标是否显示为黑色，默认：false
     */
    public boolean getStatusBarLightFlag() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        WINDOW = getWindow();

        CONTEXT = getApplicationContext();

        CURRENT_ACTIVITY = this;

        Integer statusBarColorId = getStatusBarColorId();

        if (statusBarColorId != null) {

            StatusBarUtil.setColor(statusBarColorId, getStatusBarLightFlag());

        }

        // 线程报错处理器
        Thread.setDefaultUncaughtExceptionHandler(MyUncaughtExceptionHandler.getInstance());

        // 初始化页面
        initView(savedInstanceState);

    }

    /**
     * 初始化页面
     */
    public abstract void initView(@Nullable Bundle savedInstanceState);

    /**
     * 页面跳转
     */
    public static <T extends BaseActivity> void getAppNav(Class<T> tClass) {

        getAppNav(tClass, null);

    }

    /**
     * 页面跳转
     */
    public static <T extends BaseActivity> void getAppNav(Class<T> tClass, @Nullable Object extra) {

        Intent intent = new Intent(CURRENT_ACTIVITY, tClass);

        String extraStr;

        if (extra instanceof Long) {

            extraStr = extra.toString();

        } else {

            extraStr = JSONUtil.toJsonStr(extra);

        }

        intent.putExtra(CommonConstant.EXTRA, extraStr);

        CURRENT_ACTIVITY.startActivity(intent);

        LogUtil.debug("路由跳转：{}，数据：{}", tClass.getSimpleName(), extraStr);

    }

    public Map<AppDispatchKeyEnum, Consumer<Object>> getAppDispatchMap() {
        return null;
    };

    /**
     * 调度执行方法
     */
    public void getAppDispatch(AppDispatchKeyEnum appDispatchKeyEnum, @Nullable Object data) {

        Map<AppDispatchKeyEnum, Consumer<Object>> appDispatchMap = getAppDispatchMap();

        if (CollUtil.isEmpty(appDispatchMap)) {
            return;
        }

        Consumer<Object> consumer = appDispatchMap.get(appDispatchKeyEnum);

        if (consumer == null) {
            return;
        }

        consumer.accept(data);

    }

}
