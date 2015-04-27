package ir.highroid.catalog.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.ArrayList;
import java.util.List;

import ir.highroid.catalog.R;
import ir.highroid.catalog.adapter.AdapterCategoryRecycler;
import ir.highroid.catalog.bundle.BundleCategory;
import ir.highroid.catalog.database.DatabaseInteract;

/**
 * Created by mohammad on 6/19/2016.
 */
public class FragmentCategoryRecycler extends Fragment {

    View rootView;
    private RecyclerView categoryRecycler;
    private LinearLayoutManager linearLayoutManager;
    SliderLayout sliderShow;
    TextView txtCategText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_category_recycler, container, false);
        initObjectAndViews();
        initNewsRecycler();
        initSlider();
        return rootView;
    }

    private void initNewsRecycler(){

        DatabaseInteract dbInteract = new DatabaseInteract(getContext());
        ArrayList<BundleCategory> categories = dbInteract.getCategories();

        AdapterCategoryRecycler adapterCategoryRecycler = new AdapterCategoryRecycler(categories, getActivity().getApplicationContext(), getActivity());
        categoryRecycler.setLayoutManager(linearLayoutManager);
        categoryRecycler.setAdapter(adapterCategoryRecycler);
        categoryRecycler.setNestedScrollingEnabled(false);
    }

    private void initObjectAndViews(){
        categoryRecycler = (RecyclerView) rootView.findViewById(R.id.categoryRecycler);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        sliderShow = (SliderLayout) rootView.findViewById(R.id.slider);
        txtCategText = (TextView) rootView.findViewById(R.id.txtCategText);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "IRAN Sans.ttf");
        txtCategText.setTypeface(typeface);
    }

    private void initSlider(){
        DefaultSliderView textSliderView = new DefaultSliderView(getActivity());
        textSliderView
                .description("Game of Thrones")
                .image(R.drawable.slider01)
                .setScaleType(BaseSliderView.ScaleType.CenterCrop);
        DefaultSliderView textSliderView1 = new DefaultSliderView(getActivity());
        textSliderView1
                .description("Game of Thrones1")
                .image(R.drawable.slider02)
                .setScaleType(BaseSliderView.ScaleType.CenterCrop);
        DefaultSliderView textSliderView2 = new DefaultSliderView(getActivity());
        textSliderView2
                .description("Game of Thrones2")
                .image(R.drawable.slider03)
                .setScaleType(BaseSliderView.ScaleType.CenterCrop);
        DefaultSliderView textSliderView3 = new DefaultSliderView(getActivity());
        textSliderView3
                .description("Game of Thrones3")
                .image(R.drawable.slider04)
                .setScaleType(BaseSliderView.ScaleType.CenterCrop);
        sliderShow.addSlider(textSliderView);
        sliderShow.addSlider(textSliderView1);
        sliderShow.addSlider(textSliderView2);
        sliderShow.addSlider(textSliderView3);
    }

    @Override
    public void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }

}
