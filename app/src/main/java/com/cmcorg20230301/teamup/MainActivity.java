package com.cmcorg20230301.teamup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cmcorg20230301.teamup.activity.sign.SignInActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(getColor(R.color.black2));

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        startActivity(new Intent(this, SignInActivity.class));

        finish();

    }

}