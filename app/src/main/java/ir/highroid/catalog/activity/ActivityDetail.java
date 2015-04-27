package ir.highroid.catalog.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

import ir.highroid.catalog.R;
import ir.highroid.catalog.adapter.AdapterFullScreenImage;
import ir.highroid.catalog.bundle.BundleGallery;
import ir.highroid.catalog.database.DatabaseInteract;

public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private AdapterFullScreenImage viewPagerAdapter;
    ImageView btnCloseImage, btnPreviousImage, btnNextImage, btnPlayImage, btnHomeImage;
    TextView txtTitle;
    private CountDownTimer x = null;
    private ArrayList<BundleGallery> galleryList;
    String[] galleryListNames;
    RelativeLayout ribbonTopBtn, ribbonBottomBtn;
//    FrameLayout rootLayout;
    String categTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        initObjectAndView();
        initViewPager();
        initListenerAndEvent();
    }

    private void initObjectAndView(){
        viewPager = (ViewPager) findViewById(R.id.pager);
        btnCloseImage = (ImageView) findViewById(R.id.btnCloseImage);
        btnNextImage = (ImageView) findViewById(R.id.btnNextImage);
        btnPreviousImage = (ImageView) findViewById(R.id.btnPreviousImage);
        btnPlayImage = (ImageView) findViewById(R.id.btnPlayImage);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        btnHomeImage = (ImageView) findViewById(R.id.btnHomeImage);
        ribbonBottomBtn = (RelativeLayout) findViewById(R.id.ribbonBottomBtn);
        ribbonTopBtn = (RelativeLayout) findViewById(R.id.ribbonTopBtn);
        categTitle = getIntent().getStringExtra("categTitle") + " ";
//        rootLayout = (FrameLayout) findViewById(R.id.rootLayout);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "IRAN Sans.ttf");
    }

    private void initListenerAndEvent(){
        btnCloseImage.setOnClickListener(this);
        btnNextImage.setOnClickListener(this);
        btnPreviousImage.setOnClickListener(this);
        btnPlayImage.setOnClickListener(this);
        btnHomeImage.setOnClickListener(this);
//        rootLayout.setOnClickListener(this);

        btnPlayImage.setTag("play");

        x = new CountDownTimer(100000, 3000) {
            public void onTick(long millisUntilFinished) {
                int currentItemNum = viewPager.getCurrentItem();
                if (currentItemNum != viewPagerAdapter.getCount()-1) {
                    viewPager.setCurrentItem(++currentItemNum);
                    txtTitle.setText(categTitle + galleryListNames[currentItemNum]);
                }
                else{
                    viewPager.setCurrentItem(0);
                    txtTitle.setText(String.valueOf(1));
                }
            }
            public void onFinish() {
                x.start();
            }
        };

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                txtTitle.setText(categTitle + galleryListNames[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViewPager(){

        int currentItem = getIntent().getIntExtra("imageNum", 0);
        int galleryId = Integer.parseInt(getIntent().getStringExtra("categId"));
        DatabaseInteract dbInteract = new DatabaseInteract(getApplicationContext());
        galleryList = dbInteract.getGalleries(galleryId);
        galleryListNames = new String[galleryList.size()];

        for (int i = 0; i < galleryList.size(); i++) {
            galleryListNames[i] = galleryList.get(i).title;
        }

        viewPagerAdapter = new AdapterFullScreenImage(ActivityDetail.this, galleryList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(currentItem);
        txtTitle.setText(categTitle + galleryListNames[currentItem]);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCloseImage:
                ActivityDetail.this.finish();
                overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
                break;

            case R.id.btnNextImage:
                int currentItemNum = viewPager.getCurrentItem();
                if (currentItemNum != viewPagerAdapter.getCount()-1) {
                    viewPager.setCurrentItem(++currentItemNum);
                    txtTitle.setText(categTitle + galleryListNames[currentItemNum]);
                }
                break;

            case R.id.btnPlayImage:
                if (btnPlayImage.getTag().equals(null)||btnPlayImage.getTag().equals("play")){
                    btnPlayImage.setImageResource(R.mipmap.cc_pause);
                    btnPlayImage.setTag("pause");
                    x.start();
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            showRibbons();
                        }
                    }, 4000);
                }
                else{
                    btnPlayImage.setImageResource(R.mipmap.cc_play);
                    btnPlayImage.setTag("play");
                    showRibbons();
                    ribbonTopBtn.setVisibility(View.VISIBLE);
                    ribbonBottomBtn.setVisibility(View.VISIBLE);
                    x.cancel();
                }
                break;

            case R.id.btnPreviousImage:
                int currentItemNum1 = viewPager.getCurrentItem();
                if(currentItemNum1 != 0) {
                    viewPager.setCurrentItem(--currentItemNum1);
                    txtTitle.setText(categTitle + galleryListNames[currentItemNum1]);
                }
                break;
            case R.id.btnHomeImage:
                Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
                break;

            case R.id.rootLayout:
//                showRibbons();
                break;
        }
    }

    public void showRibbons(){
        if (ribbonBottomBtn.getVisibility() == View.VISIBLE){
            ribbonTopBtn.setVisibility(View.INVISIBLE);
            ribbonBottomBtn.setVisibility(View.INVISIBLE);
        }
        else {
            ribbonTopBtn.setVisibility(View.VISIBLE);
            ribbonBottomBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
    }
}
