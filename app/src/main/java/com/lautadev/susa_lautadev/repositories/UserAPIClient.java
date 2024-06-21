package com.lautadev.susa_lautadev.repositories;

import com.lautadev.susa_lautadev.dto.UserDTO;
import com.lautadev.susa_lautadev.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Header;
public interface UserAPIClient {
    @POST("/api/user/save")
    Call<Void> saveUser(@Body User user);

    @POST("/api/user/login")
    Call<User> loginUser(@Query("email") String email, @Query("password") String password);

    @GET("/api/user/get")
    Call<List<User>> getUsers();

    @GET("/api/user/get/{id}")
    Call<User> findUser(@Path("id") Long id);

    @GET("/api/user/info/{id}")
    Call<UserDTO> findUserAndBenefit(@Path("id") Long id);

    @DELETE("/api/user/delete/{id}")
    Call<Void> deleteUser(@Path("id") Long id);

    @PUT("/api/user/edit/{id}")
    Call<User> editUser(@Path("id") Long id, @Body User user);

    @PATCH("/api/user/updateTickets/{id}")
    Call<User> updateTickets(@Path("id") Long id, @Body int ticket, @Header("X-HTTP-Method-Override") String methodOverride);

    @PATCH("/api/user/assignBenefit/{id}")
    Call<UserDTO> assignBenefit(@Path("id") Long id, @Body Long idBenefit, @Header("X-HTTP-Method-Override") String methodOverride);
}
