package com.example.reapdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    TextView header;
    Button add_ledger, logout, reload;
    RecyclerView recyclerView;
    LedgerAdapter ledgerAdapter;
    List<Transaction> transactionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String user_name = mAuth.getCurrentUser().getDisplayName();

        header = findViewById(R.id.welcome);
        header.setText("Welcome "+user_name+", "+"here's your transactions.");
        add_ledger = findViewById(R.id.ledger_add_button);
        logout = findViewById(R.id.logout_button);
        reload = findViewById(R.id.reload_button);

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

        recyclerView = findViewById(R.id.ledgers_rview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        transactionList = new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        transactionList = new ArrayList<>();

        final DatabaseReference rm = FirebaseDatabase.getInstance().getReference().child("Transactions").child(mAuth.getCurrentUser().getUid());
        rm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        Transaction t = dataSnapshot1.getValue(Transaction.class);
                            transactionList.add(t);
                    }

                    ledgerAdapter = new LedgerAdapter(transactionList,getApplicationContext());
                    recyclerView.setAdapter(ledgerAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                Toast.makeText(getApplicationContext(),"Logout Successful!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        add_ledger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SelectUser.class));
            }
        });

    }
}
