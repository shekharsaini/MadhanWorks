package com.myfinances;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.myfinances.database.TransactionData;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {
    Context context;
    private List<TransactionData> data;
    private TransactionData transactionData;
    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(TransactionData cart, int pos);
    }


    public TransactionAdapter(Context applicationContext,
                              OnItemClickListener onItemClickListener) {
        context = applicationContext;
        listener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).
                inflate(R.layout.listitem_transactions, viewGroup, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        transactionData = data.get(position);
        holder.tvAccountType.setText(transactionData.sAccountType);
        holder.tvAccountNumber.setText(transactionData.sAccountNumber);
        if(transactionData.sAccountType.equalsIgnoreCase("CDs")){
            holder.tvInitialBalance.setText(transactionData.sInitialBalance);
            holder.tvCurrentBalance.setText(transactionData.sCurrentBalance);
            holder.tvInterestRate.setText(transactionData.sInterestRate);

            holder.llInitialBalance.setVisibility(View.VISIBLE);
            holder.llInterestRate.setVisibility(View.VISIBLE);
            holder.llPaymentAmount.setVisibility(View.GONE);
        }

        if(transactionData.sAccountType.equalsIgnoreCase("Loans")){
            holder.tvInitialBalance.setText(transactionData.sInitialBalance);
            holder.tvCurrentBalance.setText(transactionData.sCurrentBalance);
            holder.tvPaymentAmount.setText(transactionData.sPaymentAmount);
            holder.llInitialBalance.setVisibility(View.VISIBLE);
            holder.llPaymentAmount.setVisibility(View.VISIBLE);
            holder.llInterestRate.setVisibility(View.GONE);
        }

        if(transactionData.sAccountType.equalsIgnoreCase("Checking")){
            holder.tvCurrentBalance.setText(transactionData.sCurrentBalance);

            holder.llInterestRate.setVisibility(View.GONE);
            holder.llInitialBalance.setVisibility(View.GONE);
            holder.llPaymentAmount.setVisibility(View.GONE);
        }

        holder.imageDelete.setOnClickListener(v->{
            transactionData = data.get(position);
            listener.onItemClick(transactionData,position);
            notifyDataSetChanged();
        });

    }


    @Override
    public int getItemCount() {
        return (null != data ? data.size() : 0);
    }

    public void setData(List<TransactionData> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvAccountType,tvAccountNumber,tvInitialBalance, tvCurrentBalance,tvInterestRate, tvPaymentAmount;
        ImageView imageDelete;

        LinearLayout llInitialBalance,llCurrentBalance,llInterestRate,llPaymentAmount;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvAccountType = itemView.findViewById(R.id.tvAccountType);
            tvAccountNumber = itemView.findViewById(R.id.tvAccountNumber);
            tvInitialBalance = itemView.findViewById(R.id.tvInitialBalance);
            tvCurrentBalance = itemView.findViewById(R.id.tvCurrentBalance);
            tvInterestRate = itemView.findViewById(R.id.tvInterestRate);
            tvPaymentAmount = itemView.findViewById(R.id.tvPaymentAmount);
            imageDelete = itemView.findViewById(R.id.imageDelete);
            llInitialBalance = itemView.findViewById(R.id.llInitialBalance);
            llCurrentBalance = itemView.findViewById(R.id.llCurrentBalance);
            llInterestRate = itemView.findViewById(R.id.llInterestRate);
            llPaymentAmount = itemView.findViewById(R.id.llPaymentAmount);
        }
    }

}