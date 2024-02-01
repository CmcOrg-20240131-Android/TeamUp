package com.cmcorg20230301.teamup.activity.sign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.R;

/**
 * 注册页
 */
public class SignInActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(getColor(R.color.black2));

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.sign_in);

        doInit();

    }

    private void doInit() {

        TextView signUp = findViewById(R.id.signUp);

        signUp.setOnClickListener(v -> {

            startActivity(new Intent(this, SignUpActivity.class));

        });

    }

}
