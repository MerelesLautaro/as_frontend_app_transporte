package com.lautadev.susa_lautadev.repositories;

import com.lautadev.susa_lautadev.model.Benefit;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BenefitAPIClient {
    @POST("/api/benefit/save")
    Call<Void> saveBenefit(@Body Benefit benefit);

    @GET("/api/benefit/get")
    Call<List<Benefit>> getBenefits();

    @GET("/api/benefit/get/{id}")
    Call<Benefit> findBenefit(@Path("id") Long id);

    @DELETE("/api/benefit/delete/{id}")
    Call<Void> deleteBenefit(@Path("id") Long id);

    @PUT("/api/benefit/edit/{id}")
    Call<Benefit> editBenefit(@Path("id") Long id, @Body Benefit benefit);
}
