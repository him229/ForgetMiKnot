package com.example.himankyadav.forgetmiknot;

/**
 * Created by himankyadav on 4/11/16.
 */
    import android.app.Activity;
    import android.content.Context;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;

    import java.io.File;
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
                Log.d("GETTING HERE", "********** INSIDE READING IMAGE");
                Bitmap myBitmap = BitmapFactory.decodeFile(p.getImage());
                imageView.setImageBitmap(myBitmap);
            }
            extratxt.setText(p.getDateandtime());
        }

        return v;
    }

//        public View getView(int position,View view,ViewGroup parent) {
//            LayoutInflater inflater=context.getLayoutInflater();
//            View rowView=inflater.inflate(R.layout.mylist, null,true);
//
//            Log.d("GETTING HERE", "********** INSIDE ADAPTER TEXT");
//            TextView txtTitle = (TextView) rowView.findViewById(R.id.Itemname);
//            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
//            TextView extratxt = (TextView) rowView.findViewById(R.id.ItemnameDetail);
//
//            List<ItemMaster> listOfItems = ItemMaster.getItems();
//            txtTitle.setText(listOfItems.get(position).getItemName());
//            if (listOfItems.get(position).getImage() == "null"){
//                imageView.setImageResource(R.drawable.baby);
//            }
//            else{
////                File imgFile = new  File(listOfItems.get(position).getImage());
////
////                if(imgFile.exists()){
//                Log.d("GETTING HERE", "********** INSIDE READING IMAGE");
//                Bitmap myBitmap = BitmapFactory.decodeFile(listOfItems.get(position).getImage());
//                imageView.setImageBitmap(myBitmap);
//            }
//            extratxt.setText(listOfItems.get(position).getDateandtime());
//            return rowView;
//
//        };
}
