package com.lautadev.susa_lautadev.repositories;

import com.lautadev.susa_lautadev.dto.AccountDTO;
import com.lautadev.susa_lautadev.dto.UpdateBalanceDTO;
import com.lautadev.susa_lautadev.model.Account;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Header;

import java.util.List;
public interface AccountAPIClient {
    @POST("/api/account/save")
    Call<Void> saveAccount(@Body Account account);

    @GET("/api/account/get")
    Call<List<Account>> getAccounts();

    @GET("/api/account/get/{id}")
    Call<Account> findAccount(@Path("id") Long id);

    @GET("/api/account/get/accountAndUser/{id}")
    Call<AccountDTO> findAccountAndUser(@Path("id") Long id);

    @GET("/api/account/get/alias/{alias}")
    Call<Account> findByAlias(@Path("alias") String alias);

    @GET("/api/account/get/cvu/{cvu}")
    Call<Account> findByCvu(@Path("cvu") String cvu);

    @GET("/api/account/get/user/{id}")
    Call<Account> findAccountByUser(@Path("id") Long id);

    @DELETE("/api/account/delete/{id}")
    Call<Void> deleteAccount(@Path("id") Long idAccount);

    @PUT("/api/account/edit/{id}")
    Call<AccountDTO> editAccount(@Path("id") Long idAccount, @Body Account account);

    @POST("/api/account/updateBalance/{id}")
    Call<AccountDTO> updateBalance(@Path("id") Long id, @Body UpdateBalanceDTO updateBalanceDTO,
                                   @Header("X-HTTP-Method-Override") String methodOverride);
}
