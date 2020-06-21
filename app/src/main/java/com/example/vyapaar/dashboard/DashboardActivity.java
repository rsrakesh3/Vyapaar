package com.example.vyapaar.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.vyapaar.R;
import com.example.vyapaar.dashboard.contract.BaseContract;
import com.example.vyapaar.dashboard.ui.dashboard.DashboardItemFragment;
import com.example.vyapaar.dashboard.ui.dashboard.dummy.DummyContent;

public class DashboardActivity extends AppCompatActivity implements DashboardItemFragment.OnListFragmentInteractionListener, BaseContract {

    private LottieAnimationView mLoaderAnimation;
    private View mPageLevelLoaderView;
    private AppCompatTextView mPageLevelLoadingTextView;
    private boolean allowBackPress = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        mLoaderAnimation = findViewById(R.id.page_level_progress_bar);
        setUpLoaderViews();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DashboardItemFragment.newInstance(1))
                    .commitNow();
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if(fragment instanceof DashboardItemFragment){
            ((DashboardItemFragment) fragment).setContract(this);
        }
    }

    @Override
    public void onBackPressed() {
        if(allowBackPress){
            super.onBackPressed();
        }else{

        }
    }

    protected void setUpLoaderViews() {
        if (mPageLevelLoaderView == null || mLoaderAnimation == null) {
            mPageLevelLoaderView = findViewById(R.id.page_level_loader_view);
            if (mPageLevelLoaderView != null)
                mPageLevelLoadingTextView = mPageLevelLoaderView.findViewById(R.id.loading_text_view);
            mLoaderAnimation = findViewById(R.id.page_level_progress_bar);
        }
    }


    public void startAnimation(){
        allowBackPress = false;
        mLoaderAnimation.playAnimation();
        mPageLevelLoaderView.setVisibility(View.VISIBLE);
    }

    public void stopAnimation(){
        allowBackPress = true;
        mLoaderAnimation.pauseAnimation();
        mPageLevelLoaderView.setVisibility(View.GONE);
    }


    @Override
    public void onListFragmentInteraction(String item) {

    }

    @Override
    public void startLoader() {
        startAnimation();
    }

    @Override
    public void stopLoader() {
        stopAnimation();
    }
}
