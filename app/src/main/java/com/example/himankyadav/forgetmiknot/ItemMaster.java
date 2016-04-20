package com.example.himankyadav.forgetmiknot;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by himankyadav on 4/16/16.
 */
public class ItemMaster {
    static public List<ItemMaster> items = new ArrayList<>();
    static public List<ItemMaster> getItems(){
        return items;
    }
    String itemName = null;
    String description = null;
    String dateandtime = null;
    String image = null;
    String latitute = null;
    String longitute = null;

    public ItemMaster(String longitute, String image, String latitute, String dateandtime, String description, String itemName) {
        this.longitute = longitute;
        this.image = image;
        this.latitute = latitute;
        this.dateandtime = dateandtime;
        this.description = description;
        this.itemName = itemName;
    }

    public String getLatitute() {
        return latitute;
    }

    public void setLatitute(String latitute) {
        this.latitute = latitute;
    }

    public String getLongitute() {
        return longitute;
    }

    public void setLongitute(String longitute) {
        this.longitute = longitute;
    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDateandtime() {
        return dateandtime;
    }
    public void setDateandtime(String dateandtime) {
        this.dateandtime = dateandtime;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
