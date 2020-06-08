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

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> userList;
    private Context context;
    public UserAdapter(List<User> users, Context context){
        this.userList = users;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_cards, parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserAdapter.ViewHolder holder, int position) {
        final User u = userList.get(position);
        holder.Name.setText("Name: "+u.getName());
        holder.Email.setText("Email: "+u.getEmail());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,AddLedger.class);
                i.putExtra("UserName2",u.getName());
                i.putExtra("Email2",u.getEmail());
                i.putExtra("Uid2",u.getUid());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView Name, Email;
        private CardView card;
        public ViewHolder(View view) {
            super(view);
            Name = view.findViewById(R.id.name);
            Email = view.findViewById(R.id.email);
            card = view.findViewById(R.id.user_card);
        }
    }
}
