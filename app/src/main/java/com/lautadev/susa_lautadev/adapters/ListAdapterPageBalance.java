package com.lautadev.susa_lautadev.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lautadev.susa_lautadev.R;
import com.lautadev.susa_lautadev.model.Transaction;
import com.lautadev.susa_lautadev.model.TypeOfOperation;

import java.util.List;
import java.util.Locale;

public class ListAdapterPageBalance extends RecyclerView.Adapter<ListAdapterPageBalance.ViewHolder> {
    private List<Transaction> listTransactions;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapterPageBalance(List<Transaction> itemsList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.listTransactions = itemsList;
    }

    @Override
    public int getItemCount() {return listTransactions.size(); }

    @Override
    public ListAdapterPageBalance.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListAdapterPageBalance.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapterPageBalance.ViewHolder holder, final int position){
        holder.bindData(listTransactions.get(position));
    }

    public void setItems(List<Transaction> newItems) { listTransactions = newItems;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_balance, img_type_of_operation;
        TextView operation, dateoperation, amount;

        ViewHolder(View itemView){
            super(itemView);
            img_balance = itemView.findViewById(R.id.img_balance_view);
            img_type_of_operation = itemView.findViewById(R.id.img_type_of_operation);
            operation = itemView.findViewById(R.id.text_operation);
            dateoperation = itemView.findViewById(R.id.text_dateoperation);
            amount = itemView.findViewById(R.id.text_amount);
        }

        void bindData(final Transaction transactions){
            // Convertir ENUM a String
            operation.setText(transactions.getTypeOfOperation().toString());
            dateoperation.setText(transactions.getDateOfOperation());
            // Convertir double a String con formato
            String formattedAmount = String.format(Locale.getDefault(), "%.2f", transactions.getAmount());
            amount.setText("AR$ "+formattedAmount);

            if (transactions.getTypeOfOperation() == TypeOfOperation.MoneyReceived ||
                    transactions.getTypeOfOperation() == TypeOfOperation.BalanceTopUp) {
                img_type_of_operation.setImageResource(R.drawable.ic_arrow_up);
            } else {
                img_type_of_operation.setImageResource(R.drawable.ic_arrow_down);
            }
        }
    }

}