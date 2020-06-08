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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button login, signup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){startActivity(new Intent(MainActivity.this,Home.class));finish();}

        email = findViewById(R.id.user_email);
        password = findViewById(R.id.user_password);
        login = findViewById(R.id.login_button);
        signup = findViewById(R.id.signup_button);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String string_email = email.getText().toString();
                final String string_password = password.getText().toString();
                final ProgressDialog progress = new ProgressDialog(MainActivity.this);
                progress.setMessage("Registering...");
                progress.show();
                if(string_email.length()!=0&&string_password.length()!=0)
                mAuth.signInWithEmailAndPassword(string_email, string_password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            private static final String TAG = "IDK" ;

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startActivity(new Intent(MainActivity.this,Home.class));
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

                                    switch (errorCode) {

                                        case "ERROR_INVALID_CUSTOM_TOKEN":
                                            Toast.makeText(MainActivity.this, "The custom token format is incorrect. Please check the documentation.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_CUSTOM_TOKEN_MISMATCH":
                                            Toast.makeText(MainActivity.this, "The custom token corresponds to a different audience.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_INVALID_CREDENTIAL":
                                            Toast.makeText(MainActivity.this, "The supplied auth credential is malformed or has expired.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_INVALID_EMAIL":
                                            Toast.makeText(MainActivity.this, "The email address is badly formatted.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_WRONG_PASSWORD":
                                            Toast.makeText(MainActivity.this, "The password is invalid or the user does not have a password.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_USER_MISMATCH":
                                            Toast.makeText(MainActivity.this, "The supplied credentials do not correspond to the previously signed in user.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_REQUIRES_RECENT_LOGIN":
                                            Toast.makeText(MainActivity.this, "This operation is sensitive and requires recent authentication. Log in again before retrying this request.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                                            Toast.makeText(MainActivity.this, "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_EMAIL_ALREADY_IN_USE":
                                            Toast.makeText(MainActivity.this, "The email address is already in use by another account.   ", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                                            Toast.makeText(MainActivity.this, "This credential is already associated with a different user account.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_USER_DISABLED":
                                            Toast.makeText(MainActivity.this, "The user account has been disabled by an administrator.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_USER_TOKEN_EXPIRED":
                                            Toast.makeText(MainActivity.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_USER_NOT_FOUND":
                                            Toast.makeText(MainActivity.this, "There is no user record corresponding to this identifier. The user may have been deleted.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_INVALID_USER_TOKEN":
                                            Toast.makeText(MainActivity.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_OPERATION_NOT_ALLOWED":
                                            Toast.makeText(MainActivity.this, "This operation is not allowed. You must enable this service in the console.", Toast.LENGTH_LONG).show();
                                            break;

                                        case "ERROR_WEAK_PASSWORD":
                                            Toast.makeText(MainActivity.this, "The given password is invalid.", Toast.LENGTH_LONG).show();
                                            break;
                                    }
                                }
                            }
                        });
                else
                    Toast.makeText(getApplicationContext(),"Incomplete credentials!",Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Signup.class));
                finish();
            }
        });


    }
}
