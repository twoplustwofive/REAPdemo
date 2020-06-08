package com.example.reapdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    TextView header;
    Button add_ledger, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        header = findViewById(R.id.welcome);
        add_ledger = findViewById(R.id.ledger_add_button);
        logout = findViewById(R.id.logout_button);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String user_name = mAuth.getCurrentUser().getDisplayName();
        header.setText("Welcome "+user_name+"!");

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
                startActivity(new Intent(getApplicationContext(),AddLedger.class));
            }
        });

    }
}
