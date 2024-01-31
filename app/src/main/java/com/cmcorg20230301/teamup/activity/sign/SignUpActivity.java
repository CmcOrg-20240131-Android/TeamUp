package com.cmcorg20230301.teamup.activity.sign;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cmcorg20230301.teamup.BaseActivity;
import com.cmcorg20230301.teamup.R;

/**
 * 登录页
 */
public class SignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_up);

        doInit();

    }

    private void doInit() {

        TextView signIn = findViewById(R.id.signIn);

        signIn.setOnClickListener(v -> {

            startActivity(new Intent(this, SignInActivity.class));

        });

    }

}
