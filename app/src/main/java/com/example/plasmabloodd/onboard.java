package com.example.plasmabloodd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.plasmabloodd.R.*;

public class onboard extends AppCompatActivity {

    Button donor_butt, done_butt;
    Button recipient_butt;
    RelativeLayout r_u_options;
    ImageView search_image;
    LinearLayout form_fill;
    TextView plasma_blood_tv;

    EditText u_name,u_city,u_mob;
    RadioGroup rd_grp;
    RadioButton rd_button;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_onboard);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        intialize_params();

        donor_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r_u_options.setVisibility(View.GONE);
                form_fill.setVisibility(View.VISIBLE);

                Animation scale_down= AnimationUtils.loadAnimation(getApplicationContext(), anim.scale_down_anim);
                scale_down.setFillAfter(true);
                search_image.startAnimation(scale_down);

                Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
                form_fill.startAnimation(slide_up);

                plasma_blood_tv.setVisibility(View.VISIBLE);
                Animation alpha_onboard = AnimationUtils.loadAnimation(getApplicationContext(), anim.alpha_onboard);

                plasma_blood_tv.startAnimation(alpha_onboard);
            }
        });

        done_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = rd_grp.getCheckedRadioButtonId();
                rd_button = findViewById(selectedRadioButtonId);
                String selectedRbText = rd_button.getText().toString();

                fire_user fu = new fire_user(u_city.getText().toString(), u_mob.getText().toString());
                if(selectedRbText.equals("Both")) {
                   // Toast.makeText(getApplicationContext(),"dafda",Toast.LENGTH_SHORT).show();
                    mDatabase.child("Blood").child(u_name.getText().toString()).setValue(fu);
                    mDatabase.child("Plasma").child(u_name.getText().toString()).setValue(fu);
                    mDatabase.child(selectedRbText).child(u_name.getText().toString()).setValue(fu);
                }
                else{
                    mDatabase.child(selectedRbText).child(u_name.getText().toString()).setValue(fu);
                }
                onboard.this.finish();
                overridePendingTransition(R.anim.fade_in_activity, anim.fade_out_activity);
                //update data to firebase
            }
        });

        recipient_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             onboard.this.finish();
             overridePendingTransition(R.anim.fade_in_activity, anim.fade_out_activity);
            }
        });
    }




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void intialize_params(){

        donor_butt = findViewById(R.id.donor_butt);
        done_butt = findViewById(R.id.done_butt);
        recipient_butt = findViewById(R.id.recipient_butt);
        r_u_options =findViewById(R.id.r_u_option);
        search_image = findViewById(R.id.search_img);
        form_fill = findViewById(R.id.form_fill);
        plasma_blood_tv = findViewById(id.plasma_blood_tv);

        u_name = findViewById(id.name_edt);
        u_city = findViewById(id.city_edt);
        u_mob = findViewById(id.mob_edt);
        rd_grp = findViewById(id.rd_grp);
    }
}



