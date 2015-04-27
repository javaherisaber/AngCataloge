package ir.highroid.catalog.activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ir.highroid.catalog.R;
import ir.highroid.catalog.bundle.BundleLocation;
import ir.highroid.catalog.fragment.FragmentCall;

public class ActivityMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private BundleLocation location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        location = new BundleLocation();
        location.label = getIntent().getExtras().getString("Label");
        location.latitude = getIntent().getDoubleExtra("Latitude",0);
        location.longitude = getIntent().getDoubleExtra("Longitude",0);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in ANG and move the camera
        LatLng ANG = new LatLng(location.latitude
                ,location.longitude);
        mMap.addMarker(new MarkerOptions().position(ANG).title(location.label));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ANG));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(ANG)
                .zoom(Float.parseFloat(
                        getResources().getString(R.string.map_zoom_level_ANG)
                )).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
    }
}
