package com.example.himankyadav.forgetmiknot;

/**
 * Created by himankyadav on 4/11/16.
 */
    import android.app.Activity;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.ImageView;
    import android.widget.TextView;

    import java.util.ArrayList;
    import java.util.List;

public class CustomListAdapter extends ArrayAdapter<String> {

        private final Activity context;

//        private List<ItemMaster> listOfItems = ItemMaster.getItems();

        public CustomListAdapter(Activity context) {
            super(context, R.layout.mylist);
            // TODO Auto-generated constructor stub

            this.context=context;

        }

        public View getView(int position,View view,ViewGroup parent) {
            LayoutInflater inflater=context.getLayoutInflater();
            View rowView=inflater.inflate(R.layout.mylist, null,true);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.Itemname);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
            TextView extratxt = (TextView) rowView.findViewById(R.id.ItemnameDetail);

            List<ItemMaster> listOfItems = ItemMaster.getItems();
            txtTitle.setText(listOfItems.get(position).getItemName());
            imageView.setImageBitmap(listOfItems.get(position).getImage());
            extratxt.setText(listOfItems.get(position).getDateandtime());
            return rowView;

        };
}
