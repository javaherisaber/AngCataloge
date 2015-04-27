package ir.highroid.catalog.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ir.highroid.catalog.fragment.FragmentBadgeRecycler;
import ir.highroid.catalog.fragment.FragmentCall;
import ir.highroid.catalog.fragment.FragmentCategoryRecycler;
import ir.highroid.catalog.fragment.FragmentGalleryRecycler;
import ir.highroid.catalog.fragment.FragmentInfo;

/**
 * Created by mohammad on 6/19/2016.
 */
public class AdapterHomeViewPager extends FragmentStatePagerAdapter {


    public AdapterHomeViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        if(position == 0){
            return new FragmentCategoryRecycler();
        }

        else if (position == 1){
            return new FragmentCall();
        }

        return new FragmentInfo();

    }

    @Override
    public int getCount() {
        return 3;
    }

}
