package com.example.himankyadav.forgetmiknot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ItemDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
    }
    public void viewonmapClick(View view){
        Intent intent = new Intent(this, ItemMapView.class);
        startActivity(intent);
    }


}
