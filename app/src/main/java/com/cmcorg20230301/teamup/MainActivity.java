package com.cmcorg20230301.teamup;

import android.content.Intent;
import android.os.Bundle;

import com.cmcorg20230301.teamup.activity.sign.SignInActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, SignInActivity.class));

        finish();

    }

}