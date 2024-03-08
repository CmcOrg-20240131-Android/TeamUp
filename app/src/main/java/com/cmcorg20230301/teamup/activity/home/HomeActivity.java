package com.cmcorg20230301.teamup.activity.home;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.home.chat.HomeChatSessionFragment;
import com.cmcorg20230301.teamup.activity.home.contact.HomeContactFragment;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.util.MyExceptionUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import cn.hutool.core.util.StrUtil;

/**
 * 主页
 */
public class HomeActivity extends BaseActivity {

    @Override
    public @ColorRes Integer getStatusBarColorId() {
        return R.color.white1;
    }

    @Override
    public boolean getStatusBarLightFlag() {
        return true;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {

            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.bnm1) {

                    getSupportFragmentManager().beginTransaction()
                        .replace(R.id.homeFragment, HomeChatSessionFragment.class, null).commit();

                } else if (itemId == R.id.bnm2) {

                    getSupportFragmentManager().beginTransaction()
                        .replace(R.id.homeFragment, HomeContactFragment.class, null).commit();

                } else if (itemId == R.id.bnm3) {

                }

            }

        });

        Intent intent = getIntent();

        String fragmentClassName = intent.getStringExtra(CommonConstant.EXTRA);

        Class<? extends Fragment> fClass = null;

        if (StrUtil.isNotBlank(fragmentClassName)) {

            try {

                fClass = (Class<? extends Fragment>)Class.forName(fragmentClassName);

            } catch (ClassNotFoundException e) {

                MyExceptionUtil.printError(e);

            }

        }

        if (fClass == null) {

            fClass = HomeChatSessionFragment.class;

        }

        getSupportFragmentManager().beginTransaction().add(R.id.homeFragment, fClass, null).commit();

    }

}
