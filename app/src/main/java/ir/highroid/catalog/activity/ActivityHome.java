package ir.highroid.catalog.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import ir.highroid.catalog.R;
import ir.highroid.catalog.adapter.AdapterHomeViewPager;
import ir.highroid.catalog.view.NonSwipeViewPager;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private NonSwipeViewPager viewPager;
    private AdapterHomeViewPager adapterHomeViewPager;
    ImageView btnHome, btnCall, btnInfo, btnBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initToolbar();
        initObjectAndView();
        initViewPager();
        initListenerAndEvent();
    }

    public void initObjectAndView(){
        viewPager = (NonSwipeViewPager) findViewById(R.id.viewpager);
        btnHome = (ImageView) findViewById(R.id.btnHome);
        btnBadge = (ImageView) findViewById(R.id.btnBadge);
        btnCall = (ImageView) findViewById(R.id.btnCall);
        btnInfo = (ImageView) findViewById(R.id.btnInfo);
    }

    private void initViewPager(){
        adapterHomeViewPager = new AdapterHomeViewPager(getSupportFragmentManager());
        viewPager.setAdapter(adapterHomeViewPager);
    }

    private void initListenerAndEvent(){
        btnHome.setOnClickListener(this);
        btnBadge.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
    }


    public void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.btnHome:
                changeRibbonBar(viewId);
                viewPager.setCurrentItem(0, false);
                break;

            case R.id.btnBadge:
//                changeRibbonBar(viewId);
//                viewPager.setCurrentItem(1);
                break;

            case R.id.btnCall:
                changeRibbonBar(viewId);
                viewPager.setCurrentItem(1, false);
                break;

            case R.id.btnInfo:
                changeRibbonBar(viewId);
                viewPager.setCurrentItem(2, false);
                break;
        }
    }

    private void changeRibbonBar(int viewId){
        switch (viewId){
            case R.id.btnHome:
                btnHome.setImageResource(R.drawable.home_selected);
                btnBadge.setImageResource(R.drawable.badge);
                btnCall.setImageResource(R.drawable.call);
                btnInfo.setImageResource(R.drawable.info);
                break;

            case R.id.btnBadge:
                btnHome.setImageResource(R.drawable.home);
                btnBadge.setImageResource(R.drawable.badge_selected);
                btnCall.setImageResource(R.drawable.call);
                btnInfo.setImageResource(R.drawable.info);
                break;

            case R.id.btnCall:
                btnHome.setImageResource(R.drawable.home);
                btnBadge.setImageResource(R.drawable.badge);
                btnCall.setImageResource(R.drawable.call_selected);
                btnInfo.setImageResource(R.drawable.info);
                break;

            case R.id.btnInfo:
                btnHome.setImageResource(R.drawable.home);
                btnBadge.setImageResource(R.drawable.badge);
                btnCall.setImageResource(R.drawable.call);
                btnInfo.setImageResource(R.drawable.info_selected);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
    }
}
