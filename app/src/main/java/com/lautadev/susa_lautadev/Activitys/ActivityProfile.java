package com.lautadev.susa_lautadev.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lautadev.susa_lautadev.R;
import com.lautadev.susa_lautadev.adapters.SectionsPagerAdapter;
import com.lautadev.susa_lautadev.model.Account;
import com.lautadev.susa_lautadev.model.User;
import com.lautadev.susa_lautadev.network.ConfigUserAPIClient;
import com.lautadev.susa_lautadev.repositories.UserAPIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProfile extends AppCompatActivity {

    private UserAPIClient userAPIClient;

    private TextView textName, textCuit;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProfile.this, ActivityHome.class);
                startActivity(intent);
                finish();
            }
        });


        userAPIClient = ConfigUserAPIClient.getClient().create(UserAPIClient.class);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabLayout);
        tabs.setupWithViewPager(viewPager);

        tabs.getTabAt(0).setText("Cuenta");
        tabs.getTabAt(1).setText("Ubicación");
        tabs.getTabAt(2).setText("Beneficio");

        // Recuperar el ID del usuario de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userIdString = sharedPreferences.getString("UserID", null);
        if (userIdString != null) {
            Long idUser = Long.parseLong(userIdString);

            // Buscar al usuario por su ID
            Call<User> callUser = userAPIClient.findUser(idUser);
            textName = findViewById(R.id.text_username);
            textCuit = findViewById(R.id.text_cuit);

            callUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        User user = response.body();
                        textName.setText(user.getName());
                        textCuit.setText(user.getCuit());
                    } else {
                        Toast.makeText(ActivityProfile.this, "Error al obtener los datos del usuario", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(ActivityProfile.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}