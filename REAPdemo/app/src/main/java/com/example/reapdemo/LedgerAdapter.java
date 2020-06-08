package com.example.reapdemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class LedgerAdapter extends RecyclerView.Adapter<LedgerAdapter.ViewHolder> {

    private List<Transaction> transactionList;
    private Context context;
    public LedgerAdapter(List<Transaction> transactionList, Context context){
        this.transactionList = transactionList;
        this.context = context;
    }

    @NonNull
    @Override
    public LedgerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ledger_cards, parent,false);
        return new LedgerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LedgerAdapter.ViewHolder holder, int position) {
        Transaction u = transactionList.get(position);
        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(u.getUid1()))
        holder.Name.setText("Name: "+u.getUserName2());
        else
        holder.Name.setText("Name: "+u.getUserName1());

        holder.Date.setText("Date: "+u.getDateTime());
        holder.Amount.setText("Amount: "+u.getAmount());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Name, Date, Amount;
        private CardView card;
        public ViewHolder(View view) {
            super(view);
            Name = view.findViewById(R.id.name);
            Date = view.findViewById(R.id.date);
            Amount = view.findViewById(R.id.amount);
            card = view.findViewById(R.id.ledgers_card);
        }
    }
}

