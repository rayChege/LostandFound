package com.example.ray.lostandfound;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ray.lostsystem.R;

/**
 * Created by Ray on 5/18/2018.
 */

public  class Activity_Details extends AppCompatActivity {
    String gettitle,getname,getwherefound,getdate,getdescription;
    ImageView obj_logo,obj_item;
    TextView  obj_title,obj_name,obj_wherefound,obj_date,obj_description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_details);
         obj_logo = findViewById(R.id.logo_image);
         obj_item =findViewById(R.id.logo_edit_image);
         obj_title = findViewById(R.id.input_txttitle);
         obj_name =findViewById(R.id.input_name);
         obj_wherefound = findViewById(R.id.input_wherefound);
         obj_date =findViewById(R.id.input_date);
         obj_description = findViewById(R.id.input_description);

        Intent login = getIntent();
        Bundle bd = login.getExtras();
        if (bd != null) {

            /*Bitmap logo_bitmap = (Bitmap) login.getParcelableExtra("logoimage");
            Bitmap edit_bitmap = (Bitmap) login.getParcelableExtra("editimage");*/

            gettitle = (String) bd.get("title").toString();
            getname = (String) bd.get("name").toString();
            getwherefound = (String) bd.get("wherefound").toString();
            getdate = (String) bd.get("date").toString();
            getdescription = (String) bd.get("description").toString();


            /*obj_logo.setImageBitmap(logo_bitmap);
            obj_item.setImageBitmap(edit_bitmap);*/
            obj_title.setText(gettitle);
            obj_name.setText(getname);
            obj_wherefound.setText(getwherefound);
            obj_date.setText(getdate);
            obj_description.setText(getdescription);
        }
    }
}
