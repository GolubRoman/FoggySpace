package com.golubroman.golub.foggyspace.GetStartedPackage;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by User on 14.03.2017.
 */

public class MyVideoView extends VideoView{

    private int leftAdjustment, topAdjustment;

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            int videoWidth = getMeasuredWidth();
            int videoHeight = getMeasuredHeight();

            int width = getDefaultSize(0, widthMeasureSpec);
            int height = getDefaultSize(0, heightMeasureSpec);

            leftAdjustment = 0;
            topAdjustment = 0;

            if(videoWidth == width){
                int newWidth = (int) ((float) videoWidth / videoHeight * width);
                setMeasuredDimension(newWidth, height);
                leftAdjustment = -(newWidth - width) / 2;
            }else{
                int newHeight = (int) ((float) videoHeight / videoWidth * height);
                setMeasuredDimension(width, newHeight);
                topAdjustment = -(newHeight - height) / 2;
            }
            setMeasuredDimension(width * 2, height);
        }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l + leftAdjustment, t + topAdjustment, r + leftAdjustment, b + topAdjustment);
    }


}
