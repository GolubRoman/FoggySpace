package com.golubroman.golub.foggyspace.GetStartedPackage;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.golubroman.golub.foggyspace.SignInActivity;
import com.golubroman.golub.foggyspace.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetStartedActivity extends AppCompatActivity {

    @BindView(R.id.video_get_started) MyVideoView videoView;
    @BindView(R.id.btn_get_started) ImageView button_get_started;
    @BindView(R.id.welcome_get_started) TextView welcome_get_started;

    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        ButterKnife.bind(this);
        videoRunner();
        button_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetStartedActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        changeFont();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoRunner();
    }

    private void videoRunner(){

        uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.movie);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    private void changeFont(){
        Typeface segoe = Typeface.createFromAsset(this.getResources().getAssets(), "segoe.ttf");
        welcome_get_started.setTypeface(segoe);
    }
}
