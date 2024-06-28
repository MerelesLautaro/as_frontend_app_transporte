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
import com.lautadev.susa_lautadev.model.User;
import com.lautadev.susa_lautadev.network.ConfigUserAPIClient;
import com.lautadev.susa_lautadev.repositories.UserAPIClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbicacionFragment extends Fragment {
    private UserAPIClient userAPIClient;

    private TextView textCountry, textProvincie, textDepartament, textCity, textAddress, textZipCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ubicacion, container, false);

        userAPIClient = ConfigUserAPIClient.getClient().create(UserAPIClient.class);

        textCountry = view.findViewById(R.id.text_pais);
        textProvincie = view.findViewById(R.id.text_provincia);
        textDepartament =  view.findViewById(R.id.text_departamento);
        textCity = view.findViewById(R.id.text_ciudad);
        textAddress = view.findViewById(R.id.text_direccion);
        textZipCode = view.findViewById(R.id.text_codigoPostal);

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
                    textCountry.setText(user.getCountry());
                    textProvincie.setText(user.getProvince());
                    textDepartament.setText(user.getDepartament());
                    textCity.setText(user.getCity());
                    textAddress.setText(user.getAddress());
                    textZipCode.setText(user.getZipCode());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
