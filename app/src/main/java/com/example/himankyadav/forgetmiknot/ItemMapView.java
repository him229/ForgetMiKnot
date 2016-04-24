package com.example.himankyadav.forgetmiknot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;

public class ItemMapView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int position = intent.getIntExtra("pos", 0);
//        Bundle args = new Bundle();
        ItemMaster p = ItemMaster.getItems().get(position);
        MapFragment.valLatLng = new LatLng(Double.valueOf(p.getLatitute()),Double.valueOf(p.getLongitute()));
        setContentView(R.layout.activity_item_map_view);
//        args.putParcelable("positionLatLng", new LatLng(Double.valueOf(p.getLatitute()),Double.valueOf(p.getLongitute())));
//        Intent i = new Intent(this, MapFragment.class);
//        i.putExtra("bundle",args);
//        startActivity(i);
    }
}
