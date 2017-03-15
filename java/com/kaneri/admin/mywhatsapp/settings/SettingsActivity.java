package com.kaneri.admin.mywhatsapp.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kaneri.admin.mywhatsapp.R;
import com.kaneri.admin.mywhatsapp.finalchatapp.Login;

public class SettingsActivity extends AppCompatActivity {

    TextView changePassword, changeEmail, signout, deleteProfile;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(SettingsActivity.this, Login.class));
                    finish();
                }
            }
        };
        changeEmail = (TextView) findViewById(R.id.changeEmail);
        changePassword = (TextView) findViewById(R.id.changePassword);
        signout = (TextView) findViewById(R.id.signOut);
        deleteProfile = (TextView) findViewById(R.id.deleteAccount);

        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsActivity.this, DeleteProfile.class));
            }
        });

        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //signOut();
                startActivity(new Intent(SettingsActivity.this,ChangeEmail.class));
            }
        });


        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                startActivity(new Intent(SettingsActivity.this, ChangePassword.class));
            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                startActivity(new Intent(SettingsActivity.this, Login.class));
            }
        });


    }

    public void signOut() {
        auth.signOut();
    }
}