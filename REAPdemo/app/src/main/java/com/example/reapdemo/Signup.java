package com.example.reapdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    Button Signup;
    EditText email, password1, password2, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.user_email);
        password1 = findViewById(R.id.user_password);
        password2 = findViewById(R.id.confirm_password);
        name  = findViewById(R.id.user_name);
        Signup = findViewById(R.id.signup_button);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String string_email = email.getText().toString();
                final String string_pass1 = password1.getText().toString();
                final String string_pass2 = password2.getText().toString();
                final String string_name = name.getText().toString();
                if(string_email.length()==0)
                    Toast.makeText(Signup.this,"Incomplete email!",Toast.LENGTH_LONG).show();
                else
                if(!string_pass1.equals(string_pass2))
                    Toast.makeText(Signup.this,"Passwords not matched.",Toast.LENGTH_LONG).show();
                else
                if(string_pass1.length()==0)
                    Toast.makeText(Signup.this,"Incorrect Password.",Toast.LENGTH_LONG).show();
                else
                if(string_name.length()==0)
                    Toast.makeText(Signup.this,"Incomplete name.",Toast.LENGTH_LONG).show();
                else
                    doSignup(string_email,string_pass1,string_name);
            }
        });

    }

    public void doSignup(String email, String password, final String name){
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final ProgressDialog progress = new ProgressDialog(Signup.this);
        progress.setMessage("Registering...");
        progress.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                    private static final String TAG = "";

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(Signup.this, "Authentication Successful!",
                                    Toast.LENGTH_SHORT).show();
                            final FirebaseUser user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "User profile updated.");
                                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                                DatabaseReference root = database.getReference();
                                                root.child("Users").child(user.getUid()).child("Name").setValue(user.getDisplayName());
                                                root.child("Users").child(user.getUid()).child("Email").setValue(user.getEmail());
                                                root.child("Users").child(user.getUid()).child("Uid").setValue(user.getUid());

                                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                                finish();
                                            }
                                        }
                                    });

                        } else {
                            // If sign in fails, display a message to the user.
                            if(!task.isSuccessful()) {
                                try {
                                    throw task.getException();
                                } catch(FirebaseAuthWeakPasswordException e) {
                                    Toast.makeText(getApplicationContext(),"Weak Password!",Toast.LENGTH_SHORT).show();
                                } catch(FirebaseAuthInvalidCredentialsException e) {
                                    Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_SHORT).show();
                                } catch(FirebaseAuthUserCollisionException e) {
                                    Toast.makeText(getApplicationContext(),"Already Exist!",Toast.LENGTH_SHORT).show();
                                } catch(Exception e) {
                                    Log.e(TAG, e.getMessage());
                                    Toast.makeText(getApplicationContext(),"Signup unsuccessful!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        // ...
                    }
                });
        progress.dismiss();
    }
}
