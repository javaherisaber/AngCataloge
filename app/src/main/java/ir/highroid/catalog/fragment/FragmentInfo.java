package ir.highroid.catalog.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ir.highroid.catalog.R;

/**
 * Created by mohammad on 6/20/2016.
 */
public class FragmentInfo extends Fragment {

    View rootView;
    TextView txtDescription, txtAbout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_info, container, false);
        txtDescription = (TextView) rootView.findViewById(R.id.txtDescription);
        txtAbout = (TextView) rootView.findViewById(R.id.txtAbout);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "IRAN Sans.ttf");
        txtDescription.setTypeface(typeface);
        txtAbout.setTypeface(typeface);
        return rootView;
    }
}
