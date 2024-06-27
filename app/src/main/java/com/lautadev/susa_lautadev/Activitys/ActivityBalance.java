package com.lautadev.susa_lautadev.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lautadev.susa_lautadev.R;
import com.lautadev.susa_lautadev.adapters.ListAdapterPageBalance;
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
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityBalance extends AppCompatActivity {
    private UserAPIClient userAPIClient;
    private AccountAPIClient accountAPIClient;
    private TransactionsAPIClient transactionAPIClient;
    private ImageView btnBack, btnFilter, btnFilterOff;
    private TextView txtBalance;
    private Long idAccount;
    private RecyclerView recyclerView;

    private static final int REQUEST_CODE_FILTER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        btnBack = findViewById(R.id.btn_back_qr);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityBalance.this, ActivityHome.class);
                startActivity(intent);
                finish();
            }
        });

        btnFilter = findViewById(R.id.btn_filter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityBalance.this, ActivityFilter.class);
                startActivityForResult(intent, REQUEST_CODE_FILTER);
            }
        });

        btnFilterOff = findViewById(R.id.btn_filter_off);

        btnFilterOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
                Toast.makeText(ActivityBalance.this, "Filtros Limpiados" , Toast.LENGTH_SHORT).show();
            }
        });


        txtBalance = findViewById(R.id.text_account_balance);

        userAPIClient = ConfigUserAPIClient.getClient().create(UserAPIClient.class);
        accountAPIClient = ConfigAccountAPIClient.getClient().create(AccountAPIClient.class);
        transactionAPIClient = ConfigTransactionAPIClient.getClient().create(TransactionsAPIClient.class);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userIdString = sharedPreferences.getString("UserID", null);
        if (userIdString != null) {
            Long idUser = Long.parseLong(userIdString);

            Call<User> callUser = userAPIClient.findUser(idUser);

            callUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        User user = response.body();

                        Call<Account> callAccount = accountAPIClient.findAccount(idUser);
                        callAccount.enqueue(new Callback<Account>() {
                            @Override
                            public void onResponse(Call<Account> call, Response<Account> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    Account account = response.body();
                                    idAccount = account.getIdAccount();
                                    String strBalance = String.valueOf(account.getBalance());
                                    txtBalance.setText("AR$ " + strBalance);

                                    init();
                                } else {
                                    Toast.makeText(ActivityBalance.this, "Error al obtener los datos de la cuenta", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Account> call, Throwable t) {
                                Toast.makeText(ActivityBalance.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(ActivityBalance.this, "Error al obtener los datos del usuario", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(ActivityBalance.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "User ID no disponible", Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        fetchTransactions(transactionAPIClient.getTransactionsByAccount(idAccount));
    }

    private void fetchTransactions(Call<List<Transaction>> call) {
        call.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Transaction> listTransactions = response.body();
                    // Ordenar las transacciones por fecha de operación
                    Collections.sort(listTransactions, new Comparator<Transaction>() {
                        @Override
                        public int compare(Transaction t1, Transaction t2) {
                            return t2.getDateOfOperation().compareTo(t1.getDateOfOperation());
                        }
                    });
                    setupRecyclerView(listTransactions); // Configurar el RecyclerView con las transacciones obtenidas
                } else {
                    Toast.makeText(ActivityBalance.this, "Error al obtener las transacciones", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                Toast.makeText(ActivityBalance.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_FILTER && resultCode == RESULT_OK && data != null) {
            String fecha = data.getStringExtra("FILTER_FECHA");
            String operacion = data.getStringExtra("FILTER_OPERACION");

            applyFilters(fecha, operacion);
        }
    }

    private TypeOfOperation getTypeOfOperationFromString(String operationString) {
        switch (operationString) {
            case "Dinero Recibido":
                return TypeOfOperation.MoneyReceived;
            case "Recarga de Saldo":
                return TypeOfOperation.BalanceTopUp;
            case "Envio de Dinero":
                return TypeOfOperation.MoneyTransfer;
            case "Pago con QR":
                return TypeOfOperation.QRpayment;
            case "Retiro de Dinero":
                return TypeOfOperation.WithDrawalOfMoney;
            default:
                return null;
        }
    }

    private void applyFilters(String fecha, String operacion) {
        if (fecha != null && !fecha.isEmpty() && operacion != null && !operacion.isEmpty()) {
            // Ambos filtros seleccionados: por fecha y tipo de operación
            TypeOfOperation typeOfOperation = getTypeOfOperationFromString(operacion);
            if (typeOfOperation != null) {
                Call<List<Transaction>> call = transactionAPIClient.getTransactionsByDateAndOperationAndAccount(fecha, typeOfOperation, idAccount);
                fetchTransactions(call);
            } else {
                Toast.makeText(this, "Tipo de operación no válido", Toast.LENGTH_SHORT).show();
            }
        } else if (fecha != null && !fecha.isEmpty()) {
            // Filtro por fecha seleccionado
            Call<List<Transaction>> call = null;
            switch (fecha) {
                case "Hoy":
                    call = transactionAPIClient.getTransactionsForToday(idAccount);
                    break;
                case "Ayer":
                    call = transactionAPIClient.getTransactionsForYesterday(idAccount);
                    break;
                case "Últimos 7 días":
                    call = transactionAPIClient.getTransactionsForLast7Days(idAccount);
                    break;
                case "Últimos 15 días":
                    call = transactionAPIClient.getTransactionsForLast15Days(idAccount);
                    break;
                case "Último mes":
                    call = transactionAPIClient.getTransactionsForLastMonth(idAccount);
                    break;
                case "Últimos 3 meses":
                    call = transactionAPIClient.getTransactionsForLast3Months(idAccount);
                    break;
            }
            if (call != null) {
                fetchTransactions(call);
            } else {
                Toast.makeText(this, "Filtro de fecha no válido", Toast.LENGTH_SHORT).show();
            }
        } else if (operacion != null && !operacion.isEmpty()) {
            // Filtro por tipo de operación seleccionado
            TypeOfOperation typeOfOperation = getTypeOfOperationFromString(operacion);
            if (typeOfOperation != null) {
                Call<List<Transaction>> call = transactionAPIClient.getTransactionsByOperationAndAccount(typeOfOperation, idAccount);
                fetchTransactions(call);
            } else {
                Toast.makeText(this, "Tipo de operación no válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Ningún filtro seleccionado
            Toast.makeText(this, "Seleccione un filtro", Toast.LENGTH_SHORT).show();
        }
    }


    private void setupRecyclerView(List<Transaction> listTransactions) {
        double totalIngresos = 0.0;
        double totalEgresos = 0.0;

        for (Transaction transaction : listTransactions) {
            if (transaction.getTypeOfOperation() == TypeOfOperation.MoneyReceived ||
                    transaction.getTypeOfOperation() == TypeOfOperation.BalanceTopUp) {
                totalIngresos += transaction.getAmount();
            } else if (transaction.getTypeOfOperation() == TypeOfOperation.MoneyTransfer ||
                    transaction.getTypeOfOperation() == TypeOfOperation.QRpayment ||
                    transaction.getTypeOfOperation() == TypeOfOperation.WithDrawalOfMoney) {
                totalEgresos += transaction.getAmount();
            }
        }

        TextView textMontoIngresos = findViewById(R.id.text_monto_ingresos);
        TextView textMontoEgresos = findViewById(R.id.text_monto_egresos);

        textMontoIngresos.setText(String.format(Locale.getDefault(), "AR$ %.2f", totalIngresos));
        textMontoEgresos.setText(String.format(Locale.getDefault(), "AR$ %.2f", totalEgresos));

        ListAdapterPageBalance listAdapterPageBalance = new ListAdapterPageBalance(listTransactions, this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapterPageBalance);
    }
}