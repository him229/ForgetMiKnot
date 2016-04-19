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
                while (line!=null){
                    if (line.startsWith("<items>")){
                        line = reader.readLine();
                        String nameText = line.substring("\t<name>".length(),line.length()-"</name>\n".length());
                        line = reader.readLine();
                        String descriptionText = line.substring("\t<description>".length(),line.length()-"</description>\n".length());
                        line = reader.readLine();
                        String dandtText = line.substring("\t<dateandtime>".length(),line.length()-"</dateandtime>\n".length());
                        line = reader.readLine();
                        String latText = line.substring("\t<latitude>".length(),line.length()-"</latitude>\n".length());
                        line = reader.readLine();
                        String longText = line.substring("\t<longitude>".length(),line.length()-"</longitude>\n".length());
                        line = reader.readLine();
                        String imageString = line.substring("\t<image>".length(),line.length()-"</image>\n".length());
                        Bitmap realImage = StringToBitMap(imageString);
                        ItemMaster newItemToStore = new ItemMaster(longText,realImage,latText,dandtText,descriptionText,nameText);
                        ItemMaster.getItems().add(newItemToStore);
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

            StringBuffer dataXML = new StringBuffer();
            dataXML.append("<?xml version=\"1.0\"?>\n");
            try {
                FileOutputStream f = new FileOutputStream(file);
                PrintWriter pw = new PrintWriter(f);
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
        //dahdah parse

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
                dataXML.append("\t<name>" + i.getItemName() + space + "</name>\n");
                dataXML.append("\t<description>" + i.getDescription() + "</description>\n");
                dataXML.append("\t<dateandtime>" + i.getDateandtime()+ "</dateandtime>\n");
                dataXML.append("\t<latitude>" + i.getLatitute() + "</latitude>\n");
                dataXML.append("\t<longitude>" + i.getLongitute() + "</longitude>\n");
                if (i.getImage()==null){
                    Bitmap converted = BitmapFactory.decodeResource(getResources(), R.drawable.baby);
                    dataXML.append("\t<image>" + BitMapToString(converted) + "</image>\n");
                }
                else {
                    dataXML.append("\t<image>" + BitMapToString(i.getImage()) + "</image>\n");
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
