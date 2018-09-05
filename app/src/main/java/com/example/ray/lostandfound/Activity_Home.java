package com.example.ray.lostandfound;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.example.ray.lostsystem.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Activity_Home extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ImageView obj_logo,obj_item;
    EditText obj_title,obj_name,obj_wherefound,obj_date,obj_description;
    LinearLayout parent_layout;
    Button submit_truck;
    Bitmap myBitmap;
    Uri picUri;
    String encodedCameraImage, encodedGalleryImage;
    private int SELECT_FILE = 1;
    private ArrayList permissionsToRequest;
    private ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();
    private int REQUEST_CAMERA = 0;
    private final static int ALL_PERMISSIONS_RESULT = 107;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    HashMap<String, String> tonnage_values;
    Bitmap photo;
    String id_tonnage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        obj_logo = findViewById(R.id.logo_image);
    }

    public void showImage(View view) {

        PopupMenu popup = new PopupMenu(this, view);

        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.logo_menu);
        popup.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.existing:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);//
                startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);

                return true;
            case R.id.camera_photo:

                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, REQUEST_CAMERA);
                }

                return true;
            default:
                return false;
        }
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {


        //encodedGalleryImage = encodeImage(bm);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                photo = null;
                if (data != null) {
                    try {
                        photo = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                obj_logo.setImageBitmap(photo);
            } else if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
                photo = (Bitmap) data.getExtras().get("data");
                obj_logo.setImageBitmap(photo);

                encodedCameraImage = encodeImage(photo);
            }
        }
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    public void Next(View v){

        Intent submit=new Intent(Activity_Home.this,Activity_Details.class);

        obj_logo=findViewById(R.id.logo_image);
        obj_item=findViewById(R.id.logo_item_image);
        obj_title=findViewById(R.id.input_txttitle);
        obj_name=findViewById(R.id.input_name);
        obj_wherefound=findViewById(R.id.input_wherefound);
        obj_date=findViewById(R.id.input_date);
        obj_description=findViewById(R.id.input_description);

        ByteArrayOutputStream _bs = new ByteArrayOutputStream();
        obj_logo.compress(Bitmap.CompressFormat.PNG, 50, _bs);
        obj_item.compress(Bitmap.CompressFormat.PNG, 50, _bs);
        submit.putExtra("byteArray", _bs.toByteArray());

        /*conversion of image to bitmap*/
       /* Bitmap logo_image = ((BitmapDrawable) obj_logo.getDrawable()).getBitmap();
        Bitmap logo_item_image = ((BitmapDrawable) obj_item.getDrawable()).getBitmap();
*/
       /* submit.putExtra("logoimage", logo_image);
        submit.putExtra("itemimage",item_image);
      */submit.putExtra("title", obj_title.getText().toString());
        submit.putExtra("name",obj_name.getText().toString());
        submit.putExtra("wherefound", obj_wherefound.getText().toString());
        submit.putExtra("date",obj_date.getText().toString());
        submit.putExtra("description", obj_description.getText().toString());
        startActivity(submit);
    }
}
