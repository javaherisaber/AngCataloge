package ir.highroid.catalog.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.highroid.catalog.R;

/**
 * Created by mohammad on 6/20/2016.
 */
public class FragmentBadgeRecycler extends Fragment {

    View rootView;
    private RecyclerView galleryRecycler;
    private GridLayoutManager gridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_badge_recycler, container, false);
        return rootView;
    }
}
