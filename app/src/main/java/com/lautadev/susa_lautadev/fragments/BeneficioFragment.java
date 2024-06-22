package com.lautadev.susa_lautadev.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lautadev.susa_lautadev.R;
import com.lautadev.susa_lautadev.model.Benefit;
import com.lautadev.susa_lautadev.model.User;
import com.lautadev.susa_lautadev.network.ConfigBenefitAPIClient;
import com.lautadev.susa_lautadev.network.ConfigUserAPIClient;
import com.lautadev.susa_lautadev.repositories.BenefitAPIClient;
import com.lautadev.susa_lautadev.repositories.UserAPIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeneficioFragment extends Fragment {

    private UserAPIClient userAPIClient;
    private BenefitAPIClient benefitAPIClient;
    private TextView textNameBenefit, textStartTime, textEndTime, textCountTickets;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beneficio, container, false);

        userAPIClient = ConfigUserAPIClient.getClient().create(UserAPIClient.class);
        benefitAPIClient = ConfigBenefitAPIClient.getClient().create(BenefitAPIClient.class);

        textNameBenefit = view.findViewById(R.id.text_nombreBeneficio);
        textStartTime = view.findViewById(R.id.text_tiempoInicio);
        textEndTime = view.findViewById(R.id.text_tiempoFin);
        textCountTickets = view.findViewById(R.id.text_cantidadBoletos);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String userIdString = sharedPreferences.getString("UserID", null);
        if (userIdString != null) {
            Long idUser = Long.parseLong(userIdString);

            loadUserData(idUser);
        }

        return view;
    }
    private void loadUserData(Long idUser) {
        Call<User> call = userAPIClient.findUser(idUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    int tickets = user.getTickets();
                    String strTickets = String.valueOf(tickets);
                    textCountTickets.setText(strTickets);
                    loadBenefitData(user.getIdBenefit());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadBenefitData(Long idBenefit) {
        Call<Benefit> callBenefit = benefitAPIClient.findBenefit(idBenefit);
        callBenefit.enqueue(new Callback<Benefit>() {
            @Override
            public void onResponse(Call<Benefit> call, Response<Benefit> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Benefit benefit = response.body();
                    textNameBenefit.setText(benefit.getName());
                    textStartTime.setText(benefit.getStartTime());
                    textEndTime.setText(benefit.getEndTime());
                }
            }

            @Override
            public void onFailure(Call<Benefit> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
