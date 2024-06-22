package com.lautadev.susa_lautadev.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lautadev.susa_lautadev.R;
import com.lautadev.susa_lautadev.model.Account;
import com.lautadev.susa_lautadev.model.Transaction;
import com.lautadev.susa_lautadev.model.TypeOfOperation;
import com.lautadev.susa_lautadev.model.User;
import com.lautadev.susa_lautadev.network.ConfigAccountAPIClient;
import com.lautadev.susa_lautadev.network.ConfigTransactionAPIClient;
import com.lautadev.susa_lautadev.network.ConfigUserAPIClient;
import com.lautadev.susa_lautadev.repositories.AccountAPIClient;
import com.lautadev.susa_lautadev.repositories.TransactionsAPIClient;
import com.lautadev.susa_lautadev.repositories.UserAPIClient;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityQR extends AppCompatActivity {
    private AccountAPIClient accountAPIClient;
    private TransactionsAPIClient transactionAPIClient;
    private ImageView btnBack;
    private Long idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrpayment);

        accountAPIClient = ConfigAccountAPIClient.getClient().create(AccountAPIClient.class);
        transactionAPIClient = ConfigTransactionAPIClient.getClient().create(TransactionsAPIClient.class);

        btnBack = findViewById(R.id.btn_back_qr);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityQR.this, ActivityHome.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnBackPage = findViewById(R.id.button_back);
        btnBackPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityQR.this, ActivityHome.class);
                startActivity(intent);
                finish();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userIdString = sharedPreferences.getString("UserID", null);
        if (userIdString != null) {
            idUser = Long.parseLong(userIdString); }

        Button btnPayment = findViewById(R.id.btn_payment);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idUser != null) {
                    getAccountAndMakePayment(idUser);
                } else {
                    Toast.makeText(ActivityQR.this, "User ID is not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getAccountAndMakePayment(Long userId) {
        Call<Account> call = accountAPIClient.findAccountByUser(userId);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Account account = response.body();
                    makePayment(account.getIdAccount());
                } else {
                    Toast.makeText(ActivityQR.this, "Failed to get account: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(ActivityQR.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void makePayment(Long idAccount) {
        Transaction transaction = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            transaction = new Transaction(
                    TypeOfOperation.QRpayment,
                    1000,
                    LocalDate.now().toString(),
                    idAccount
            );
        }

        // Llama al endpoint
        Call<Void> call = transactionAPIClient.saveTransaction(transaction,null);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ActivityQR.this, "Payment successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityQR.this, "Payment failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ActivityQR.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
