package com.cmcorg20230301.teamup;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * 所有 fragment都需要继承本类
 */
public abstract class BaseFragment extends Fragment {

    public abstract @LayoutRes Integer getLayoutId();

    public @StyleRes Integer getThemeId() {

        return null;

    }

    @NonNull
    @Override
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = super.onGetLayoutInflater(savedInstanceState);

        Integer themeId = getThemeId();

        if (themeId == null) {
            return inflater;
        }

        Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), themeId);

        return inflater.cloneInContext(contextThemeWrapper);

    }

    public View view; // 定义 view用来设置 fragment的layout

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // 布局
        view = inflater.inflate(getLayoutId(), container, false);

        // 初始化页面
        initView(inflater, container, savedInstanceState);

        return view;

    }

    /**
     * 初始化页面
     */
    public void initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    }

    /**
     * 通过：id，获取：view
     */
    public <T extends View> T findViewById(@IdRes int id) {
        return view.findViewById(id);
    }

    /**
     * 获取：FragmentManager
     */
    public FragmentManager getSupportFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

}
