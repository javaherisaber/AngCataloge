package ir.highroid.catalog.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ir.highroid.catalog.R;

public class ActivityGallery extends AppCompatActivity {

    public int categID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categID = getIntent().getIntExtra("categID",0);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( android.R.anim.fade_in, android.R.anim.fade_out );
    }
}
