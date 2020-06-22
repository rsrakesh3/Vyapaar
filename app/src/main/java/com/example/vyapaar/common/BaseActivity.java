package com.example.vyapaar.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.vyapaar.R;

public class BaseActivity extends AppCompatActivity {

    private LottieAnimationView mLoaderAnimation;
    private View mPageLevelLoaderView;
    private AppCompatTextView mPageLevelLoadingTextView;
    private boolean allowBackPress = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        /*mLoaderAnimation = findViewById(R.id.page_level_progress_bar);
        setUpLoaderViews();*/
    }

    /*public void startAnimation(){
        allowBackPress = false;
        mLoaderAnimation.playAnimation();
        mPageLevelLoaderView.setVisibility(View.VISIBLE);
    }

    public void stopAnimation(){
        allowBackPress = true;
        mLoaderAnimation.pauseAnimation();
        mPageLevelLoaderView.setVisibility(View.GONE);
    }

    protected void setUpLoaderViews() {
        if (mPageLevelLoaderView == null || mLoaderAnimation == null) {
            mPageLevelLoaderView = findViewById(R.id.page_level_loader_view);
            if (mPageLevelLoaderView != null)
                mPageLevelLoadingTextView = mPageLevelLoaderView.findViewById(R.id.loading_text_view);
            mLoaderAnimation = findViewById(R.id.page_level_progress_bar);
        }
    }*/

   /* @Override
    public void onBackPressed() {
        if(allowBackPress){
            super.onBackPressed();
        }else{

        }
    }*/
}
