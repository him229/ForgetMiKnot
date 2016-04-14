package com.example.himankyadav.forgetmiknot;

/**
 * Created by himankyadav on 4/11/16.
 */
    import android.app.Activity;
    import android.content.Context;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.net.Uri;
    import android.os.ParcelFileDescriptor;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;

    import java.io.File;
    import java.io.FileDescriptor;
    import java.io.FileNotFoundException;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;

public class CustomListAdapter extends ArrayAdapter<ItemMaster> {

//        private final Activity context;

//        private List<ItemMaster> listOfItems = ItemMaster.getItems();
//
//        public CustomListAdapter(Activity context, List<ItemMaster> itemList) {
//            super(context, R.layout.mylist);
//
//
//            this.context=context;
//
//        }

    public CustomListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CustomListAdapter(Context context, int resource, List<ItemMaster> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.mylist, null);
        }

        ItemMaster p = getItem(position);

        if (p != null) {
            Log.d("GETTING HERE", "********** INSIDE ADAPTER TEXT");
            TextView txtTitle = (TextView) v.findViewById(R.id.Itemname);
            ImageView imageView = (ImageView) v.findViewById(R.id.icon);
            TextView extratxt = (TextView) v.findViewById(R.id.ItemnameDetail);

            txtTitle.setText(p.getItemName());
            if (p.getImage() == "null"){
                imageView.setImageResource(R.drawable.baby);
            }
            else{
//                File imgFile = new  File(listOfItems.get(position).getImage());
//                if(imgFile.exists()){
                Uri uri = Uri.parse(p.getImage());
                Log.d("IMGSTUFF", "********** CUSTOMADAP uri "+uri.toString());
                ParcelFileDescriptor parcelFD1 = null;
                try {
                    parcelFD1 = getContext().getContentResolver().openFileDescriptor(uri, "r");
                    Bitmap myBitmap = decodeUri(parcelFD1);
                    imageView.setImageBitmap(myBitmap);
                    Log.d("IMGSTUFF", "********** CUSTOMADAP - after calling decodeuri");
                }
                catch (FileNotFoundException e) {
                    Log.d("IMGSTUFF", "CUSTOMLISTADAP File404");}
            }
            extratxt.setText(p.getDateandtime());
        }

        return v;
    }
    public Bitmap decodeUri(ParcelFileDescriptor parcelFD) {
//        ParcelFileDescriptor parcelFD = null;
//        try {
//            parcelFD = getContentResolver().openFileDescriptor(uri, "r");
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


//            ImageView image = (ImageView)findViewById(R.id.ItemDetailImage);
//            image.setImageBitmap(bitmap);
            Log.d("IMGSTUFF", "CUSTOMLISTADAPTER: Uri function before returning bitmap");
            return bitmap;

//        } catch (FileNotFoundException e) {
//            Log.d("IMGSTUFF", "File404");
//            // handle errors
//        } catch (IOException e) {
//            Log.d("IMGSTUFF", "IO Error");
//        } finally {
//            if (parcelFD != null)
//                try {
//                    parcelFD.close();
//                } catch (IOException e) {
//                    Log.d("IMGSTUFF", "IOException");
//                }
//        }
    }
}
