package com.example.himankyadav.forgetmiknot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        ItemMaster p = ItemMaster.getItems().get(position);
        TextView name = (TextView)findViewById(R.id.ItemDetailItemName);
        name.setText(p.getItemName());
        TextView description = (TextView)findViewById(R.id.ItemDetailDescription);
        description.setText(p.getDescription());
        TextView dandt = (TextView)findViewById(R.id.ItemDetailDateTime);
        dandt.setText(p.getDateandtime());
        ImageView image = (ImageView) findViewById(R.id.ItemDetailImage);
        Log.d("IMGSTUFF",p.getImage() );
        if (p.getImage().equals("null"))
        {
            image.setImageResource(R.drawable.baby);
        }
        else{
          File imgFile = new File(p.getImage());
          if(imgFile.exists())
          {
              Log.d("IMGSTUFF", "DETAIL: Getting into reading user image");
//              Log.d("IMGSTUFF", "abs path "+ imgFile.getAbsolutePath());
//            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//              Log.d("IMGSTUFF", "DETAIL: Got Bitmap");
//            int nh = (int) (myBitmap.getHeight() * (512.0 / myBitmap.getWidth()) );
//            Bitmap scaled = Bitmap.createScaledBitmap(myBitmap, 512, nh, true);
//              Log.d("IMGSTUFF", "DETAIL: scaled bitmap");
//            image.setImageBitmap(myBitmap);
              decodeUri(Uri.parse(imgFile.getAbsolutePath()));
              Log.d("IMGSTUFF", "DETAIL: set bitmap");
          }
        }
    }

    public void viewonmapClick(View view){
        Intent intent = new Intent(this, ItemMapView.class);
        startActivity(intent);
    }


    public void decodeUri(Uri uri) {
        ParcelFileDescriptor parcelFD = null;
        try {
            parcelFD = getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor imageSource = parcelFD.getFileDescriptor();

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(imageSource, null, o);

            // the new size we want to scale to
            final int REQUIRED_SIZE = 1024;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            Log.d("IMGSTUFF", "DETAIL: here");
            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(imageSource, null, o2);

            ImageView image = (ImageView) findViewById(R.id.ItemDetailImage);
            image.setImageBitmap(bitmap);
            Log.d("IMGSTUFF", "DETAIL: Uri function set bitmap");

        } catch (FileNotFoundException e) {
            Log.d("IMGSTUFF", "File404");
            // handle errors
        } catch (IOException e) {
            Log.d("IMGSTUFF", "IO Error");
        } finally {
            if (parcelFD != null)
                try {
                    parcelFD.close();
                } catch (IOException e) {
                    // ignored
                }
        }
    }


}
