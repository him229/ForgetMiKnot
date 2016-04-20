package com.example.himankyadav.forgetmiknot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Intent intent = getIntent();
        int position = intent.getIntExtra("pos", 0);
        populateFields(position);
        // Dont forget the onStart method in Main2 for refreshing list
    }

    public void populateFields(int position){
        List<ItemMaster> listOfItems = ItemMaster.getItems();
        TextView name = (TextView)findViewById(R.id.ItemDetailItemName);
        name.setText(listOfItems.get(position).getItemName());
        TextView description = (TextView)findViewById(R.id.ItemDetailDescription);
        description.setText(listOfItems.get(position).getDescription());
        TextView dandt = (TextView)findViewById(R.id.ItemDetailDateTime);
        dandt.setText(listOfItems.get(position).getDateandtime());
        ImageView image = (ImageView) findViewById(R.id.ItemDetailImage);
        if (listOfItems.get(position).getImage() == null){
            image.setImageResource(R.drawable.baby);
        }
        else{
//                File imgFile = new  File(listOfItems.get(position).getImage());
//                if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(listOfItems.get(position).getImage());
            image.setImageBitmap(myBitmap);
        }
    }

    public void viewonmapClick(View view){
        Intent intent = new Intent(this, ItemMapView.class);
        startActivity(intent);
    }


}
