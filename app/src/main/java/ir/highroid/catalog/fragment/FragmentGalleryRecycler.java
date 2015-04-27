package ir.highroid.catalog.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import ir.highroid.catalog.R;
import ir.highroid.catalog.activity.ActivityGallery;
import ir.highroid.catalog.adapter.AdapterGalleryRecycler;
import ir.highroid.catalog.bundle.BundleCategory;
import ir.highroid.catalog.bundle.BundleGallery;
import ir.highroid.catalog.database.DatabaseInteract;

/**
 * Created by mohammad on 6/19/2016.
 */
public class FragmentGalleryRecycler extends Fragment {
    View rootView;
    private RecyclerView galleryRecycler;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_gallery_recycler, container, false);
        initObjectAndViews();
        initNewsRecycler();
        return rootView;
    }

    private void initNewsRecycler(){

        DatabaseInteract dbInteract = new DatabaseInteract(getContext());
        ArrayList<BundleGallery> galleryList = dbInteract.getGalleries(((ActivityGallery) getActivity()).categID);
        String categTitle = dbInteract.getCategTitleWithID(((ActivityGallery) getActivity()).categID);
        AdapterGalleryRecycler adapterGalleryRecycler = new AdapterGalleryRecycler(galleryList, getActivity().getApplicationContext(),categTitle, getActivity());
        galleryRecycler.setLayoutManager(linearLayoutManager);
        galleryRecycler.setAdapter(adapterGalleryRecycler);

    }

    private void initObjectAndViews(){
        galleryRecycler = (RecyclerView) rootView.findViewById(R.id.galleryRecycler);
        gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
    }
}
