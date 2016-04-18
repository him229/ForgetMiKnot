package com.example.himankyadav.forgetmiknot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void loginButtonClick(View view){
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File (root.getAbsolutePath() + "/forgetmiknot");
        dir.mkdirs();
        File file = new File(dir, "myData.txt");
        if (file.exists()){

        }
        else {
            Toast.makeText(MainActivity.this, "No user records found. Please Register.", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(this, Main2Activity.class);
        EditText userID = (EditText) findViewById(R.id.userIDText);
        String message = userID.getText().toString();
        intent.putExtra("UserID", message);
        startActivity(intent);
    }

    public void signupButtonClick(View view){
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File (root.getAbsolutePath() + "/forgetmiknot");
        dir.mkdirs();
        File file = new File(dir, "myData.txt");
        String username = ((EditText) findViewById(R.id.userIDText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordText)).getText().toString();
        if (file.exists()){


        }
        else {
            try {
                FileOutputStream f = new FileOutputStream(file,true);
                PrintWriter pw = new PrintWriter(f);
                StringBuffer dataXML = new StringBuffer();
                dataXML.append("<?xml version=\"1.0\"?>\n");
                dataXML.append("\t<users>\n");
                dataXML.append("\t\t<user>" + username+":"+password + "</user>\n");
                dataXML.append("\t</users>");
                dataXML.append("\t<items>");
                dataXML.append("\t</items>");
                pw.append(dataXML.toString());
                pw.flush();
                pw.close();
                f.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.i("ERR: ", "******* File not found. Did you" +
                        " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(this, Main2Activity.class);
//        EditText userID = (EditText) findViewById(R.id.userIDText);
//        String message = userID.getText().toString();
        intent.putExtra("UserID", username);
        startActivity(intent);
    }
}
