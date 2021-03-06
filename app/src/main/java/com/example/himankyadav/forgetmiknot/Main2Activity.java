package com.example.himankyadav.forgetmiknot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Objects;

import static java.util.Objects.*;

public class Main2Activity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("List of Items");
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add a new Knot", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(context,AddItem.class);
                startActivity(intent);
            }
        });

        lv = (ListView) findViewById(R.id.listView);
//        lv.setAdapter(new ArrayAdapter<String>(
//                this, R.layout.mylist,
//                R.id.Itemname,itemname));


        final CustomListAdapter adapter=new CustomListAdapter(this,R.layout.mylist, ItemMaster.getItems());
        lv.setAdapter(adapter);
        Log.d("GETTING HERE", "********** SETTING ADAPTER");
        addListenerToList();
        final Vibrator vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE) ;
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long arg3) {

                ItemMaster.getItems().remove(position);
                adapter.notifyDataSetChanged();
                new ThreadStuff().start();
                vibe.vibrate(50);
                return false;
            }

        });
    }
//    public void onStart(){
//        super.onStart();
//        CustomListAdapter adapter=new CustomListAdapter(this);
//        lv.setAdapter(adapter);
//        addListenerToList();
//        adapter.notifyDataSetChanged();
//    }

    public void addListenerToList(){
        final Context context = this;
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= ItemMaster.getItems().get(+position).getItemName();
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,ItemDetail.class);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
    }

}
