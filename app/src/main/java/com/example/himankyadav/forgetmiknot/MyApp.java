package com.example.himankyadav.forgetmiknot;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by himankyadav on 4/18/16.
 */
public class MyApp extends Application {
    public void onCreate(){
        super.onCreate();
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/forgetmiknot");
        dir.mkdirs();
        File file = new File(dir, "myData.txt");
        if (file.exists()){
            try {
                FileInputStream is = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                line = reader.readLine();
                while (line!=null){
                    if (line.startsWith("<items>")){
//                        for (int i = 0; i<6; i++){
//                            line = reader.readLine();
//                            String[] words = line.split(" ");
//                            String text = "";
//                            for (int j=1;j<words.length;j++){
//
//                            }
//                        }

                    }
                    line = reader.readLine();
                }

            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.i("ERR: ", "******* File not found. Dammit");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {

        }
        //dahdah parse
        ItemMaster.getItems();
    }
    public void onTerminate() {
        super.onTerminate();
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/forgetmiknot");
        dir.mkdirs();
        File file = new File(dir, "myData.txt");
        StringBuffer dataXML = new StringBuffer();
        dataXML.append("<?xml version=\"1.0\"?>\n");
        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            String space =" ";
            for (ItemMaster i : ItemMaster.getItems()) {
                dataXML.append("<item>\n");
                dataXML.append("\t<name>" + space + i.getItemName() + space + "</name>\n");
                dataXML.append("\t<description>" + space + i.getDescription() + space + "</description>\n");
                dataXML.append("\t<dateandtime>" + space + i.getDateandtime()+ space + "</dateandtime>\n");
                dataXML.append("\t<latitude>" + space + i.getLatitute() + space + "</latitude>\n");
                dataXML.append("\t<longitude>" + space + i.getLongitute() + space + "</longitude>\n");
                if (i.getImage()==null){
                    Bitmap converted = BitmapFactory.decodeResource(getResources(), R.drawable.baby);
                    dataXML.append("\t<image>" + space + BitMapToString(converted) + space + "</image>\n");
                }
                else {
                    dataXML.append("\t<image>" + space + BitMapToString(i.getImage()) + space + "</image>\n");
                }
                dataXML.append("</item>\n");
            }
            pw.append(dataXML.toString());
            pw.flush();
            pw.close();
            f.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("ERR: ", "******* File not found. Dammit");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

}
