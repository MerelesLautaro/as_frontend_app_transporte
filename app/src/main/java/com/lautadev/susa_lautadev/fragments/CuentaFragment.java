package com.lautadev.susa_lautadev.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lautadev.susa_lautadev.Activitys.ActivityHome;
import com.lautadev.susa_lautadev.R;
import com.lautadev.susa_lautadev.model.Account;
import com.lautadev.susa_lautadev.model.User;
import com.lautadev.susa_lautadev.network.ConfigAccountAPIClient;
import com.lautadev.susa_lautadev.network.ConfigUserAPIClient;
import com.lautadev.susa_lautadev.repositories.AccountAPIClient;
import com.lautadev.susa_lautadev.repositories.UserAPIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuentaFragment extends Fragment {
    private TextView textAlias, textCvu, textEmail, textCel;

    private UserAPIClient userAPIClient;
    private AccountAPIClient accountAPIClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuenta, container, false);

        textAlias = view.findViewById(R.id.text_alias);
        textCvu = view.findViewById(R.id.text_cvu);
        textEmail = view.findViewById(R.id.text_email);
        textCel = view.findViewById(R.id.text_telefono);

        userAPIClient = ConfigUserAPIClient.getClient().create(UserAPIClient.class);
        accountAPIClient = ConfigAccountAPIClient.getClient().create(AccountAPIClient.class);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String userIdString = sharedPreferences.getString("UserID", null);
        if (userIdString != null) {
            Long idUser = Long.parseLong(userIdString);

            loadUserData(idUser);
            loadAccountData(idUser);
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
                    textEmail.setText(user.getEmail());
                    textCel.setText(user.getCel());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadAccountData(Long idUser) {
        Call<Account> call = accountAPIClient.findAccount(idUser);
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Account account = response.body();
                    textAlias.setText(account.getAlias());
                    textCvu.setText(account.getCvu());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
