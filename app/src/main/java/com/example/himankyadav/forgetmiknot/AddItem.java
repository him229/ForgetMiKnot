package com.example.himankyadav.forgetmiknot;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddItem extends AppCompatActivity {

    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;

    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "ForgetMiKnot";

    private Uri fileUri = null; // file url to store image/video

    private ImageView imgPreview;

    private Bitmap bitmap;
    static String imgPath = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);


        imgPreview = (ImageView) findViewById(R.id.AddItemImage);
    }

    public void captureItemImage(View view){
        captureImage();
    }

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }

    }
    private void previewCapturedImage() {
        try {
            imgPreview.setVisibility(View.VISIBLE);

//            Bitmap rotatedBitmap;
            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;



            bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

////            try
////            {
//
//                bitmap = ThumbnailUtils.extractThumbnail(bitmap, 50,50);
//                // NOTE incredibly useful trick for cropping/resizing square
//                // http://stackoverflow.com/a/17733530/294884
//
////                Matrix m = new Matrix();
////                m.postRotate( Utils.neededRotation(Utils.tempFileForAnImage()) );
//
//            Matrix matrix = new Matrix();
//            matrix.postRotate(90);
//            rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//
////                bitmap = Bitmap.createBitmap(bitmap,
////                        0, 0, bitmap.getWidth(), bitmap.getHeight(),
////                        m, true);
//
////                yourImageView.setImageBitmap(cameraBmp);
//
////                // to convert to bytes...
////                ByteArrayOutputStream baos = new ByteArrayOutputStream();
////                cameraBmp.compress(Bitmap.CompressFormat.JPEG, 75, baos);
////                //or say cameraBmp.compress(Bitmap.CompressFormat.PNG, 0, baos);
////                imageBytesRESULT = baos.toByteArray();
//
////            } catch (FileNotFoundException e)
////            {
////                e.printStackTrace();
////            } catch (IOException e)
////            {
////                e.printStackTrace();
////            }
//
//
//
//
//
//

            imgPreview.setImageResource(android.R.color.transparent);
            imgPreview.setImageBitmap(null);
            imgPreview.setImageBitmap(bitmap);
            //Make text invisible
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            imgPath = mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg";
//            Log.d("imgpath ",imgPath);
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        }
        else {
            return null;
        }

        return mediaFile;
    }
    public void addItemClick(View view){
        String itemName = ((EditText) findViewById(R.id.AddItemItemName)).getText().toString();
        String itemDateandTime = ((EditText) findViewById(R.id.AddItemDateTime)).getText().toString();
        String description = ((EditText) findViewById(R.id.AddItemDescription)).getText().toString();

        addItemToList(itemName, description, itemDateandTime, fileUri.toString(), "0","0");
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);

    }

    public void addItemToList(String name, String description, String dateandtime, String imagePath, String latitude, String longitude) {
        ItemMaster newItem = new ItemMaster(longitude, imagePath, latitude, dateandtime, description, name);
        ItemMaster.getItems().add(newItem);
        new ThreadStuff().start();
    }
}
