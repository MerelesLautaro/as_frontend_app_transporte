package com.lautadev.susa_lautadev.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.lautadev.susa_lautadev.R;

public class ActivityMenu extends AppCompatActivity {
    ImageView btnMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_menu);

        Button btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this, ActivityHome.class);
                startActivity(intent);
                finish();
            }
        });

        btnMaps = findViewById(R.id.btn_map);

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMenu.this, ActivityMaps.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
