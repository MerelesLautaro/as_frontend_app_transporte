package com.lautadev.susa_lautadev.repositories;

import com.lautadev.susa_lautadev.dto.TransactionDTO;
import com.lautadev.susa_lautadev.model.Transaction;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;


import java.util.List;

public interface TransactionsAPIClient {
    @POST("/api/transaction/save")
    Call<Void> saveTransaction(@Body Transaction transaction, @Query("aliasOrCvu") String aliasOrCvu);

    @GET("/api/transaction/get")
    Call<List<Transaction>> getTransactions();

    @GET("/api/transaction/get/{id}")
    Call<Transaction> findTransaction(@Path("id") Long id);

    @GET("/api/transaction/get/account/{id}")
    Call<List<Transaction>> getTransactionsByAccount(@Path("id") Long id);

    @GET("/api/transaction/get/transactionAndAccount/{id}")
    Call<TransactionDTO> findTransactionAndAccount(@Path("id") Long id);

    @DELETE("/api/transaction/delete/{id}")
    Call<Void> deleteTransaction(@Path("id") Long id);

    @PUT("/api/transaction/edit/{id}")
    Call<Transaction> editTransaction(@Path("id") Long id, @Body Transaction transaction);
}

