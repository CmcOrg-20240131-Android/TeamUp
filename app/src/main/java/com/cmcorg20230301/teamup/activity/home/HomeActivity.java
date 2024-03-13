package com.cmcorg20230301.teamup.activity.home;

import org.jetbrains.annotations.Nullable;

import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.home.chat.HomeChatSessionFragment;
import com.cmcorg20230301.teamup.activity.home.contact.HomeContactFragment;
import com.cmcorg20230301.teamup.layout.BaseActivity;
import com.cmcorg20230301.teamup.model.constant.CommonConstant;
import com.cmcorg20230301.teamup.util.MyExceptionUtil;
import com.cmcorg20230301.teamup.util.websocket.WebSocketUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import cn.hutool.core.util.StrUtil;

/**
 * 主页
 */
public class HomeActivity extends BaseActivity {

    // 当前选中的菜单 id
    public int currentMenuId = R.id.bnm1;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                Class<? extends Fragment> fragmentClass = null;

                if (itemId == R.id.bnm1) {

                    fragmentClass = HomeChatSessionFragment.class;

                } else if (itemId == R.id.bnm2) {

                    fragmentClass = HomeContactFragment.class;

                } else if (itemId == R.id.bnm3) {

                }

                if (currentMenuId != itemId) {

                    currentMenuId = itemId;

                    if (fragmentClass != null) {

                        getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, fragmentClass, null)
                            .commit();

                    }

                }

                return true;

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

        WebSocketUtil.connectWebSocket();

    }

}
