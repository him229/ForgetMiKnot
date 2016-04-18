package com.example.himankyadav.forgetmiknot;

/**
 * Created by himankyadav on 4/16/16.
 */
public class ItemMaster {
    String itemName = null;
    String description = null;
    String dateandtime = null;
    String imageString = null;
    String latitute = null;
    String longitute = null;

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
    public String getImageString() {
        return imageString;
    }
    public void setImageString(String imageString) {
        this.imageString = imageString;
    }
}
