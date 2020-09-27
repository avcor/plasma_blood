package com.example.plasmabloodd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private myAdapter madapter;
    private List<user_list_item> list_items;

    private ChipNavigationBar nav_bar2;

    private DatabaseReference mDatabaseRef_Blood,mDatabaseRef_Plasma,mDatabaseRef_Both;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, onboard.class);
        startActivity(intent);

        mDatabaseRef_Blood = FirebaseDatabase.getInstance().getReference("/Blood");
        mDatabaseRef_Plasma = FirebaseDatabase.getInstance().getReference("/Plasma");
        mDatabaseRef_Both = FirebaseDatabase.getInstance().getReference("/Both");

        firebase_read_once(mDatabaseRef_Blood);

        nav_bar2 = findViewById(R.id.nav_bar);
        nav_bar2.setItemSelected(R.id.nav_blood,true);
        nav_bar2.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
            switch (i){
                case R.id.nav_blood:
                    firebase_read_once(mDatabaseRef_Blood);
                    break;

                case R.id.nav_plasma:
                    firebase_read_once(mDatabaseRef_Plasma);
                    break;
                case R.id.nav_both:
                    firebase_read_once(mDatabaseRef_Both);
                    break;
            }
            }
        });

//        check_virgin();

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //outside  oncreate

    public void load_recycler(DataSnapshot dataSnapshot){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list_items = new ArrayList<>();


        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
            fire_user fu = postSnapshot.getValue(fire_user.class);
            user_list_item xyx = new user_list_item(
                    "Name: "+postSnapshot.getKey(),
                    "City: "+fu.city,
                            R.drawable.man3,
                    "Mob :"+fu.mob);
            list_items.add(xyx);
        }


        madapter=new myAdapter(list_items,this);
        recyclerView.setAdapter(madapter);
        madapter.setOnItemClickListener(new myAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, detail_info.class);
                intent.putExtra("example item", list_items.get(position));
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in_activity, R.anim.fade_out_activity);
            }
        });
    }

    public void check_virgin(){
        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart  = prefs.getBoolean("firstStart", true);

        if(firstStart){
            Intent intent = new Intent(this, onboard.class);
            startActivity(intent);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstStart",false);
            editor.apply();
        }
    }

    public  void firebase_read_once(DatabaseReference mDatabaseRef){

        ValueEventListener postListener = new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String child = dataSnapshot.getChildren().toString();
                load_recycler(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseRef.addListenerForSingleValueEvent(postListener);
    }



}


