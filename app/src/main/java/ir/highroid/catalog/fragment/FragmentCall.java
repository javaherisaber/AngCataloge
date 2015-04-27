package ir.highroid.catalog.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import ir.highroid.catalog.activity.ActivityMap;
import ir.highroid.catalog.R;
import ir.highroid.catalog.bundle.BundleLocation;
import ir.highroid.catalog.view.customtabs.CustomTabActivityHelper;

/**
 * Created by mohammad on 6/20/2016.
 */
public class FragmentCall extends Fragment implements View.OnClickListener{

    View rootView;
    TextView txtOfficeText, txtQomOfficeText;
    AppCompatButton btnCall, btnQomCall, btnEmail, btnWeb,btnQomEmail,btnQomWeb,btnAddressQom, btnAddressTehran;
    private Typeface typeface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_call, container, false);
        initObjectAndView();
        initListenerAndEvent();
        return rootView;
    }

    private void initObjectAndView(){
        btnCall = (AppCompatButton) rootView.findViewById(R.id.btnCall);
        btnEmail = (AppCompatButton) rootView.findViewById(R.id.btnEmail);
        btnWeb = (AppCompatButton) rootView.findViewById(R.id.btnWeb);
        btnQomCall = (AppCompatButton) rootView.findViewById(R.id.btnQomCall);
        btnQomEmail = (AppCompatButton) rootView.findViewById(R.id.btnQomEmail);
        btnQomWeb = (AppCompatButton) rootView.findViewById(R.id.btnQomWeb);
        btnAddressQom = (AppCompatButton) rootView.findViewById(R.id.btnAddressQom);
        btnAddressTehran = (AppCompatButton) rootView.findViewById(R.id.btnAddressTehran);
        txtOfficeText = (TextView) rootView.findViewById(R.id.txtOfficeText);
        txtQomOfficeText = (TextView) rootView.findViewById(R.id.txtQomOfficeText);
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "IRAN Sans.ttf");

        btnCall.setTypeface(typeface);
        btnQomCall.setTypeface(typeface);
        txtOfficeText.setTypeface(typeface);
        txtQomOfficeText.setTypeface(typeface);
        btnAddressQom.setTypeface(typeface);
        btnAddressTehran.setTypeface(typeface);
    }

    private void initListenerAndEvent(){
        btnCall.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        btnWeb.setOnClickListener(this);
        btnQomCall.setOnClickListener(this);
        btnQomEmail.setOnClickListener(this);
        btnQomWeb.setOnClickListener(this);
        btnAddressQom.setOnClickListener(this);
        btnAddressTehran.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCall:
                makeCall(getResources().getString(R.string.phone_call_Tehran));
                break;

            case R.id.btnEmail:
                sendEmail();
                break;

            case R.id.btnWeb:
                goToWeb();
                break;

            case R.id.btnQomCall :
                makeCall(getResources().getString(R.string.phone_call_Qom));
                break;

            case R.id.btnQomEmail :
                sendEmail();
                break;

            case R.id.btnQomWeb :
                goToWeb();
                break;

            case R.id.btnAddressQom :
                BundleLocation locationQom = new BundleLocation();
                locationQom.label = getResources().getString(R.string.map_marker_name_ANG_fa);
                locationQom.latitude = Double.parseDouble(getResources().getString(R.string.map_latitude_ANG_Qom));
                locationQom.longitude = Double.parseDouble(getResources().getString(R.string.map_longitude_ANG_Qom));
                goToLocation(locationQom);
                break;

            case R.id.btnAddressTehran :
                BundleLocation locationTehran = new BundleLocation();
                locationTehran.label = getResources().getString(R.string.map_marker_name_ANG_fa);
                locationTehran.latitude = Double.parseDouble(getResources().getString(R.string.map_latitude_ANG_Tehran));
                locationTehran.longitude = Double.parseDouble(getResources().getString(R.string.map_longitude_ANG_Tehran));
                goToLocation(locationTehran);
                break;
        }
    }

    private void goToWeb(){
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
        CustomTabActivityHelper.openCustomTab(getActivity(), customTabsIntent, Uri.parse(getResources().getString(R.string.url_address_text)),
                new CustomTabActivityHelper.CustomTabFallback() {
                    @Override
                    public void openUri(Activity activity, Uri uri) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        getActivity().overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
                    }
                });
    }

    private void sendEmail(){
        String recepientEmail = getResources().getString(R.string.email_address); // either set to destination email or leave empty
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + recepientEmail));
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.email_subject_fa));
        startActivity(intent);
        getActivity().overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
    }

    private void makeCall(String number){
        String uri = "tel:" + number ;
        Intent intent1 = new Intent(Intent.ACTION_DIAL);
        intent1.setData(Uri.parse(uri));
        startActivity(intent1);
        getActivity().overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
    }

    private void goToLocation(BundleLocation location){

        Intent intent = new Intent(getActivity(), ActivityMap.class);
        intent.putExtra("Label",location.label);
        intent.putExtra("Latitude",location.latitude);
        intent.putExtra("Longitude",location.longitude);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
    }
}
