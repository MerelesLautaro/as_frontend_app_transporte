package com.lautadev.susa_lautadev.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.lautadev.susa_lautadev.R;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ActivityFilter extends AppCompatActivity {
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityFilter.this, ActivityBalance.class);
                startActivity(intent);
                finish();
            }
        });

        Button applyFiltersButton = findViewById(R.id.button_apply_filter);
        applyFiltersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recoger los valores de los filtros
                RadioGroup radioGroupFecha = findViewById(R.id.radioGroupFecha);
                RadioGroup radioGroupOperacion = findViewById(R.id.radioGroupOperacion);

                int selectedFechaId = radioGroupFecha.getCheckedRadioButtonId();
                int selectedOperacionId = radioGroupOperacion.getCheckedRadioButtonId();

                RadioButton selectedFecha = findViewById(selectedFechaId);
                RadioButton selectedOperacion = findViewById(selectedOperacionId);

                String fecha = selectedFecha != null ? selectedFecha.getText().toString() : "";
                String operacion = selectedOperacion != null ? selectedOperacion.getText().toString() : "";

                // Enviar los filtros de vuelta a la actividad principal
                Intent resultIntent = new Intent();
                resultIntent.putExtra("FILTER_FECHA", fecha);
                resultIntent.putExtra("FILTER_OPERACION", operacion);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}