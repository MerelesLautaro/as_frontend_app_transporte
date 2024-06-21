package com.lautadev.susa_lautadev.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lautadev.susa_lautadev.R;
import com.lautadev.susa_lautadev.model.Transactions;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Transactions> listTransactions;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<Transactions> itemsList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.listTransactions = itemsList;
    }

    @Override
    public int getItemCount() {return listTransactions.size(); }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(listTransactions.get(position));
    }

    public void setItems(List<Transactions> newItems) { listTransactions = newItems;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_balance;
        TextView operation, dateoperation, amount;

        ViewHolder(View itemView){
            super(itemView);
            img_balance = itemView.findViewById(R.id.img_balance_view);
            operation = itemView.findViewById(R.id.text_operation);
            dateoperation = itemView.findViewById(R.id.text_operation);
            amount = itemView.findViewById(R.id.text_amount);
        }

        void bindData(final Transactions transactions){
            // Convertir ENUM a String
            operation.setText(transactions.getTypeOfOperation().toString());

            // Convertir LocalDate a String
            DateTimeFormatter formatter = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            }
            String formattedDate = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                formattedDate = transactions.getDateOfOperation().format(formatter);
            }
            dateoperation.setText(formattedDate);

            // Convertir double a String con formato
            String formattedAmount = String.format(Locale.getDefault(), "%.2f", transactions.getAmount());
            amount.setText(formattedAmount);
        }
    }

}
