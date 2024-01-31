package com.cmcorg20230301.teamup.activity.sign;

import android.content.Intent;
import android.os.Bundle;
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
