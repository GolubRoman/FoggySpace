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
 * Created by User on 14.03.2017.
 */

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.btn_sign_in) ImageView button_sign_in;
    @BindView(R.id.welcome_back_sign_in) TextView welcome_back_sign_in;
    @BindView(R.id.sign_in_sign_in) TextView sign_in_sign_in;
    @BindView(R.id.email_sign_in) TextView email_sign_in;
    @BindView(R.id.password_sign_in) TextView password_sign_in;
    @BindView(R.id.forgot_password_sign_in) TextView forgot_password_sign_in;
    @BindView(R.id.create_account_sign_in) TextView create_account_sign_in;

    @OnClick(R.id.btn_sign_in) public void onSignInClick(){
        Intent intent = new Intent(SignInActivity.this, NavigationActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.create_account_sign_in) public  void onCreateAccountClick(){
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        changeFont();

    }
    private void changeFont(){
        Typeface segoe = Typeface.createFromAsset(this.getResources().getAssets(), "segoe.ttf");
        welcome_back_sign_in.setTypeface(segoe);
        sign_in_sign_in.setTypeface(segoe);
        email_sign_in.setTypeface(segoe);
        password_sign_in.setTypeface(segoe);
        forgot_password_sign_in.setTypeface(segoe);
        create_account_sign_in.setTypeface(segoe);
    }
}
