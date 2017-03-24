package com.golubroman.golub.foggyspace;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 15.03.2017.
 */

public class SignUpActivity extends AppCompatActivity {
    @BindView(R.id.btn_sign_up) ImageView button_sign_up;
    @BindView(R.id.welcome_sign_up) TextView welcome_sign_up;
    @BindView(R.id.sign_up_sign_up) TextView sign_up_sign_up;
    @BindView(R.id.name_sign_up) TextView name_sign_up;
    @BindView(R.id.email_sign_up) TextView email_sign_up;
    @BindView(R.id.password_sign_up) TextView password_sign_up;
    @BindView(R.id.already_have_account_sign_up) TextView already_have_account_sign_up;

    @OnClick(R.id.btn_sign_up) public void onSignUpClick(){
        Intent intent = new Intent(SignUpActivity.this, NavigationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.already_have_account_sign_up) public void onAlreadyHaveAccountClick() {
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        changeFont();

    }
    private void changeFont(){
        Typeface segoe = Typeface.createFromAsset(this.getResources().getAssets(), "segoe.ttf");
        welcome_sign_up.setTypeface(segoe);
        sign_up_sign_up.setTypeface(segoe);
        name_sign_up.setTypeface(segoe);
        email_sign_up.setTypeface(segoe);
        password_sign_up.setTypeface(segoe);
        already_have_account_sign_up.setTypeface(segoe);
    }
}
