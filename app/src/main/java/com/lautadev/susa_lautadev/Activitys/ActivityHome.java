package com.lautadev.susa_lautadev.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lautadev.susa_lautadev.R;
import com.lautadev.susa_lautadev.model.Account;
import com.lautadev.susa_lautadev.model.Transaction;
import com.lautadev.susa_lautadev.adapters.ListAdapter;
import com.lautadev.susa_lautadev.model.TypeOfOperation;
import com.lautadev.susa_lautadev.model.User;
import com.lautadev.susa_lautadev.network.ConfigAccountAPIClient;
import com.lautadev.susa_lautadev.network.ConfigTransactionAPIClient;
import com.lautadev.susa_lautadev.network.ConfigUserAPIClient;
import com.lautadev.susa_lautadev.repositories.AccountAPIClient;
import com.lautadev.susa_lautadev.repositories.TransactionsAPIClient;
import com.lautadev.susa_lautadev.repositories.UserAPIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityHome extends AppCompatActivity {
    List<Transaction> listTransactions;

    private UserAPIClient userAPIClient;
    private AccountAPIClient accountAPIClient;
    private TransactionsAPIClient transactionAPIClient;

    private TextView nameUser, aliasUser, cvuUser, balanceAccount;

    private Long idAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Obtener las instancias de las APIs usando Retrofit
        userAPIClient = ConfigUserAPIClient.getClient().create(UserAPIClient.class);
        accountAPIClient = ConfigAccountAPIClient.getClient().create(AccountAPIClient.class);
        transactionAPIClient = ConfigTransactionAPIClient.getClient().create(TransactionsAPIClient.class);

        // Recuperar el ID del usuario de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userIdString = sharedPreferences.getString("UserID", null);
        if (userIdString != null) {
            Long idUser = Long.parseLong(userIdString);

            // Buscar al usuario por su ID
            Call<User> callUser = userAPIClient.findUser(idUser);

            // Cargar los datos del usuario en la pantalla
            nameUser = findViewById(R.id.text_username);
            aliasUser = findViewById(R.id.text_alias);
            cvuUser = findViewById(R.id.text_cvu);
            balanceAccount = findViewById(R.id.text_balance);

            callUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        User user = response.body();
                        nameUser.setText(user.getName() + " " + user.getSurname());

                        // Ahora que tenemos el usuario, buscar la cuenta del usuario
                        Call<Account> callAccount = accountAPIClient.findAccount(idUser);
                        callAccount.enqueue(new Callback<Account>() {
                            @Override
                            public void onResponse(Call<Account> call, Response<Account> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    Account account = response.body();
                                    // Guardar la variable de la cuenta para cargar el RecyclerView
                                    idAccount = account.getIdAccount();
                                    aliasUser.setText(account.getAlias());
                                    cvuUser.setText(account.getCvu());
                                    String balance = String.valueOf(account.getBalance());
                                    balanceAccount.setText("Tú saldo disponible es: \n"+" AR$ "+balance);

                                    // Llamar a la funcion para cargar el recyclerView
                                    init();
                                } else {
                                    Toast.makeText(ActivityHome.this, "Error al obtener los datos de la cuenta", Toast.LENGTH_SHORT).show();
                                    System.out.println("Error al obtener los datos de la cuenta");
                                }
                            }

                            @Override
                            public void onFailure(Call<Account> call, Throwable t) {
                                Toast.makeText(ActivityHome.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                System.out.println("Error en la llamada a la API de cuenta: " + t.getMessage());
                            }
                        });
                    } else {
                        Toast.makeText(ActivityHome.this, "Error al obtener los datos del usuario", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(ActivityHome.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Manejar el caso donde el userId no está disponible
            Toast.makeText(this, "User ID no disponible", Toast.LENGTH_SHORT).show();
        }


        Button btnLogOut = findViewById(R.id.btn_logout);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar la nueva Activity
                Intent intent = new Intent(ActivityHome.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        Call<List<Transaction>> call = transactionAPIClient.getTransactionsByAccount(idAccount);

        call.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Transaction> listTransactions = response.body();
                    System.out.println("Llamado a la funcion del recycler");
                    setupRecyclerView(listTransactions); // Método para configurar RecyclerView
                } else {
                    Toast.makeText(ActivityHome.this, "Error al obtener las transacciones", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                Toast.makeText(ActivityHome.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView(List<Transaction> listTransactions) {
        ListAdapter listAdapter = new ListAdapter(listTransactions, this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
}
