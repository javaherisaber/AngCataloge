package ir.highroid.catalog.adapter;

import java.util.ArrayList;
 
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import ir.highroid.catalog.R;
import ir.highroid.catalog.activity.ActivityDetail;
import ir.highroid.catalog.bundle.BundleGallery;
import ir.highroid.catalog.view.TouchImageView;

public class AdapterFullScreenImage extends PagerAdapter {
 
    private Activity activity;
    private ArrayList<BundleGallery> imageList;
    private LayoutInflater layoutInflater;
 
    // constructor
    public AdapterFullScreenImage(Activity activity, ArrayList<BundleGallery> imageList) {
        this.activity = activity;
        this.imageList = imageList;
    }
 
    @Override
    public int getCount() {
        return this.imageList.size();
    }
 
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
     
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = layoutInflater.inflate(R.layout.layout_fullscreen_image, container, false);
        ImageView imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);
        String imageRes = imageList.get(position).pic;
        int id = activity.getResources().getIdentifier(imageRes + "b", "drawable", activity.getPackageName());
        imgDisplay.setImageResource(id);
        container.addView(viewLayout);

        imgDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ActivityDetail) activity).showRibbons();
            }
        });
  
        return viewLayout;
    }
     
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}