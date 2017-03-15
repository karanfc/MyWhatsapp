package com.kaneri.admin.mywhatsapp.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kaneri.admin.mywhatsapp.R;
import com.kaneri.admin.mywhatsapp.finalchatapp.Register;

public class DeleteProfile extends AppCompatActivity {

    public TextView deleteAccount,changeEmail;
    public FirebaseAuth auth;
    public FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_profile);
        deleteAccount = (TextView)findViewById(R.id.deleteAccount1);
        changeEmail = (TextView)findViewById(R.id.changeEmail1);
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(DeleteProfile.this, "Your profile is deleted:( Create an account now!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(DeleteProfile.this, Register.class));
                                        finish();
                                    } else {
                                        Toast.makeText(DeleteProfile.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }


}
