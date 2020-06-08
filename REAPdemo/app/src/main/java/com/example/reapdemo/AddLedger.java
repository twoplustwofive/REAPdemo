package com.example.reapdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class AddLedger extends AppCompatActivity {
    EditText amount;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ledger);
        amount = findViewById(R.id.amount);
        add = findViewById(R.id.ledger_add_button);
        Bundle bundle = getIntent().getExtras();
        final String UserName2 = bundle.getString("UserName2");
        final String Email2 = bundle.getString("Email2");
        final String Uid2 = bundle.getString("Uid2");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"adding transaction, please wait...",Toast.LENGTH_SHORT).show();
                String string_amount = amount.getText().toString();
                String currentTime = Calendar.getInstance().getTime().toString();
                String UserName1 = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                String Email1 = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String Uid1 = FirebaseAuth.getInstance().getCurrentUser().getUid();
                Transaction t = new Transaction(UserName1,UserName2,Uid1,Uid2,Email1,Email2,string_amount,currentTime);
                DatabaseReference root = FirebaseDatabase.getInstance().getReference();
                root.child("Transactions").child(Uid1).child(currentTime).setValue(t);
                root.child("Transactions").child(Uid2).child(currentTime).setValue(t);
                Toast.makeText(getApplicationContext(),"Transaction added succesfully!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
