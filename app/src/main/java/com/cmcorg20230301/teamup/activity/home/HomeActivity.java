package com.cmcorg20230301.teamup.activity.home;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.R;
import com.cmcorg20230301.teamup.activity.home.chat.HomeChatSessionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.jetbrains.annotations.Nullable;

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
                            .replace(R.id.homeFragment, HomeChatSessionFragment.class, null)
                            .commit();

                } else if (itemId == R.id.bnm2) {


                } else if (itemId == R.id.bnm3) {


                }

            }

        });

        getSupportFragmentManager().beginTransaction()
                .add(R.id.homeFragment, HomeChatSessionFragment.class, null)
                .commit();

    }

}
