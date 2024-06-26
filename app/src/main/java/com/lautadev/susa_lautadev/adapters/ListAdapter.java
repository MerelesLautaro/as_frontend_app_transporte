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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Transaction> listTransactions;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<Transaction> itemsList, Context context){
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
            operation.setText(getReadableTypeOfOperation(transactions.getTypeOfOperation()));
            dateoperation.setText(formatDate(transactions.getDateOfOperation()));
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

        private String getReadableTypeOfOperation(TypeOfOperation type) {
            switch (type) {
                case MoneyReceived:
                    return "Dinero Recibido";
                case BalanceTopUp:
                    return "Recarga de Saldo";
                case MoneyTransfer:
                    return "Envio de Dinero";
                case QRpayment:
                    return "Pago con QR";
                case WithDrawalOfMoney:
                    return "Retiro de Dinero";
                default:
                    return "Operación Desconocida";
            }
        }

        private String formatDate(String dateString) {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = inputFormat.parse(dateString);
                return outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return dateString;
            }
        }
    }

}
