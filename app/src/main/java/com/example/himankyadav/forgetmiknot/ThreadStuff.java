package com.example.himankyadav.forgetmiknot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import android.app.Application;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by himankyadav on 4/19/16.
 */
public class ThreadStuff extends Thread  {
    @Override
    public void run() {
        super.run();

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
                dataXML.append("\t<name>" + i.getItemName() + space + "</name>\n");
                dataXML.append("\t<description>" + i.getDescription() + "</description>\n");
                dataXML.append("\t<dateandtime>" + i.getDateandtime()+ "</dateandtime>\n");
                dataXML.append("\t<latitude>" + i.getLatitute() + "</latitude>\n");
                dataXML.append("\t<longitude>" + i.getLongitute() + "</longitude>\n");
                if (i.getImage()==null){
                    dataXML.append("\t<image>" + null + "</image>\n");
                }
                else {
                    dataXML.append("\t<image>" + i.getImage() + "</image>\n");
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

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
