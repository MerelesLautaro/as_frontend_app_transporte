package com.lautadev.susa_lautadev.Activitys;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lautadev.susa_lautadev.R;
import com.lautadev.susa_lautadev.model.Transactions;
import com.lautadev.susa_lautadev.model.TypeOfOperation;
import com.lautadev.susa_lautadev.ui.ListAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActivityHome extends AppCompatActivity {
    List<Transactions> listTransactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    private void init(){
        listTransactions = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            listTransactions.add(new Transactions(1L, TypeOfOperation.MoneyReceived, 100.50, LocalDate.of(2023, 6, 1), 101L));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            listTransactions.add(new Transactions(2L, TypeOfOperation.BalanceTopUp, 200.00, LocalDate.of(2023, 6, 2), 102L));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            listTransactions.add(new Transactions(3L, TypeOfOperation.MoneyTransfer, 300.75, LocalDate.of(2023, 6, 3), 103L));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            listTransactions.add(new Transactions(4L, TypeOfOperation.QRpayment, 50.25, LocalDate.of(2023, 6, 4), 104L));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            listTransactions.add(new Transactions(5L, TypeOfOperation.WithDrawalOfMoney, 150.00, LocalDate.of(2023, 6, 5), 105L));
        }

        ListAdapter listAdapter = new ListAdapter(listTransactions, this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }


}
