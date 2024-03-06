package com.cmcorg20230301.teamup.model.base;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import com.cmcorg20230301.teamup.exception.MyUncaughtExceptionHandler;
import com.cmcorg20230301.teamup.util.StatusBarUtil;

import org.jetbrains.annotations.Nullable;

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

}