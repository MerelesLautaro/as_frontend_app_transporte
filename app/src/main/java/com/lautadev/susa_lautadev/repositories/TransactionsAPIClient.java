package com.lautadev.susa_lautadev.repositories;

import com.lautadev.susa_lautadev.dto.TransactionDTO;
import com.lautadev.susa_lautadev.model.Transaction;
import com.lautadev.susa_lautadev.model.TypeOfOperation;

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

    //Filters
    @GET("/api/transaction/get/operation")
    Call<List<Transaction>> getTransactionsByOperationAndAccount(
            @Query("typeOfOperation") TypeOfOperation typeOfOperation,
            @Query("idAccount") Long idAccount
    );

    @GET("/api/transaction/filter/today")
    Call<List<Transaction>> getTransactionsForToday(
            @Query("idAccount") Long idAccount
    );

    @GET("/api/transaction/filter/yesterday")
    Call<List<Transaction>> getTransactionsForYesterday(
            @Query("idAccount") Long idAccount
    );

    @GET("/api/transaction/filter/last7days")
    Call<List<Transaction>> getTransactionsForLast7Days(
            @Query("idAccount") Long idAccount
    );

    @GET("/api/transaction/filter/last15days")
    Call<List<Transaction>> getTransactionsForLast15Days(
            @Query("idAccount") Long idAccount
    );

    @GET("/api/transaction/filter/lastmonth")
    Call<List<Transaction>> getTransactionsForLastMonth(
            @Query("idAccount") Long idAccount
    );

    @GET("/api/transaction/filter/last3months")
    Call<List<Transaction>> getTransactionsForLast3Months(
            @Query("idAccount") Long idAccount
    );

    @GET("/api/transaction/filter/custom")
    Call<List<Transaction>> getTransactionsByDateAndOperationAndAccount(
            @Query("dateFilter") String dateFilter,
            @Query("typeOfOperation") TypeOfOperation typeOfOperation,
            @Query("idAccount") Long idAccount
    );
}

