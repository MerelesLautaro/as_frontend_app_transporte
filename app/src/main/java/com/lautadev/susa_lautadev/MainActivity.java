package com.lautadev.susa_lautadev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lautadev.susa_lautadev.Activitys.ActivityHome;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIngresar = findViewById(R.id.btn_ingresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar la nueva Activity
                Intent intent = new Intent(MainActivity.this, ActivityHome.class);
                startActivity(intent);
            }
        });
    }
}