package com.example.plasmabloodd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class detail_info extends AppCompatActivity {

    ImageView u_iv;
    TextView uname_tv , ugender_tv, ucity_tv, mob_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);

        u_iv = findViewById(R.id.u_iv);
        uname_tv = findViewById(R.id.u_name_tv);
        ucity_tv = findViewById(R.id.u_city_tv);
        mob_tv = findViewById(R.id.u_mob_tv);

        Intent intent = getIntent();
        user_list_item ult = intent.getParcelableExtra("example item");

        String u_name = ult.getUser_name();
        String u_city = ult.getUser_city();
        int u_image = ult.getmImageResource();
        String u_mob = ult.getUser_mob();

        u_iv.setImageResource(u_image);
        uname_tv.setText(u_name);
        ucity_tv.setText(u_city);
        mob_tv.setText(u_mob);
    }
}