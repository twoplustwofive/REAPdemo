package com.example.reapdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditTransaction extends AppCompatActivity {
    EditText new_amount;
    Button save, delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction);
        Bundle bundle = getIntent().getExtras();
        final String Uid1 = bundle.getString("Uid1");
        final String Uid2 = bundle.getString("Uid2");
        final String date = bundle.getString("date");

        new_amount = findViewById(R.id.edited_amount);
        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Saving, please wait...",Toast.LENGTH_SHORT).show();
                String amount = new_amount.getText().toString();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Transactions");
                ref.child(Uid1).child(date).child("amount").setValue(amount);
                ref.child(Uid2).child(date).child("amount").setValue(amount);
                Toast.makeText(getApplicationContext(),"Transaction saved successfully, please reload!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Completing transaction, please wait...",Toast.LENGTH_SHORT).show();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Transactions");
                ref.child(Uid1).child(date).removeValue();
                ref.child(Uid2).child(date).removeValue();
                Toast.makeText(getApplicationContext(),"Transaction completed sucessfully, please reload!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}
