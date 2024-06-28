package com.lautadev.susa_lautadev.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputEditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.lautadev.susa_lautadev.R;
import com.lautadev.susa_lautadev.model.User;
import com.lautadev.susa_lautadev.network.ConfigUserAPIClient;
import com.lautadev.susa_lautadev.repositories.UserAPIClient;
public class MainActivity extends AppCompatActivity {

    private UserAPIClient userApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener la instancia de UserApiService usando Retrofit
        userApiService = ConfigUserAPIClient.getClient().create(UserAPIClient.class);

        Button btnIngresar = findViewById(R.id.btn_ingresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputUsername = findViewById(R.id.input_username);
                TextInputEditText inputPassword = findViewById(R.id.input_password);

                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();

                // Llamar al endpoint de login
                Call<User> call = userApiService.loginUser(username, password);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            // Login exitoso
                            User user = response.body();
                            Toast.makeText(MainActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();

                            // Almacenar el ID del usuario en SharedPreferences
                            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("UserID", String.valueOf(user.getIdUser())); // Convertir Long a String
                            editor.apply();

                            // Cambiar a la siguiente actividad (ActivityHome)
                            Intent intent = new Intent(MainActivity.this, ActivityHome.class);
                            startActivity(intent);
                            finish(); // Cierra la actividad actual para prevenir que el usuario vuelva atrás
                        } else {
                            // Error en el login (por ejemplo, credenciales incorrectas)
                            Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("Error: " + t.getMessage());
                    }
                });
            }
        });
    }
}