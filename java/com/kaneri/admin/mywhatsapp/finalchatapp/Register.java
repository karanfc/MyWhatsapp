package com.kaneri.admin.mywhatsapp.finalchatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kaneri.admin.mywhatsapp.R;

public class Register extends AppCompatActivity {

    public String email,password,confirm_password,phone_number,name;
    private EditText inputEmail, inputPassword, inputConfirmPassword, inputPhoneNumber, inputName;
    private Button btnLogin, btnSignUp;
    private ProgressBar progressBar;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessageDataReference, ref;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessageDataReference = mFirebaseDatabase.getReference().child("registrations");
        auth = FirebaseAuth.getInstance();
        btnLogin = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputConfirmPassword = (EditText) findViewById(R.id.password_confirm);
        inputPhoneNumber = (EditText) findViewById(R.id.phone_number);
        inputName = (EditText)findViewById(R.id.edt_name);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //On clicking login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });

        //On click register
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 email = inputEmail.getText().toString().trim();
                 password = inputPassword.getText().toString().trim();
                 confirm_password = inputConfirmPassword.getText().toString().trim();
                  phone_number = inputPhoneNumber.getText().toString().trim();
                name = inputName.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                } else if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(getApplicationContext(),"Enter name",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!password.equals(confirm_password)) {
                    Toast.makeText(getApplicationContext(), "Passwords vary in the two fields", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(phone_number)) {
                    Toast.makeText(getApplicationContext(), "Enter phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //Check if the phonenumber exists in database
               FirebaseDatabase.getInstance().getReference().child("registrations")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Registrations registrations = snapshot.getValue(Registrations.class);
                                    String phonenumberfromdb = registrations.getPhonenumber();
                                    if(phonenumberfromdb.equals(phone_number))
                                    {
                                        Toast.makeText(getApplicationContext(), "Phone number already exists", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        return;
                                    }
                                }
                            }
                           @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });

                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    //Entering email id and corresponding phone number in database
                                    Registrations registration = new Registrations(email,phone_number,name);
                                    mMessageDataReference.push().setValue(registration);

                                    startActivity(new Intent(Register.this, UserListingActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });

    }
}
