package com.kaneri.admin.mywhatsapp.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kaneri.admin.mywhatsapp.R;

public class ChangeEmail extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText newEmail;
    private Button btn_change_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        btn_change_email = (Button)findViewById(R.id.btn_change_email);
        newEmail = (EditText)findViewById(R.id.edt_change_email);
       /* btn_change_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    progressBar.setVisibility(View.VISIBLE);
                if (user != null && !newEmail.getText().toString().trim().equals("")) {
                    user.updateEmail(newEmail.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ChangeEmail.this, "Email address is updated. Please sign in with new email id!", Toast.LENGTH_LONG).show();
                                        signOut();
                                       // progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(ChangeEmail.this, "Failed to update email!", Toast.LENGTH_LONG).show();
                                       // progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                } else if (newEmail.getText().toString().trim().equals("")) {
                    newEmail.setError("Enter email");
                    //progressBar.setVisibility(View.GONE);
                }
            }
        });*/
    }

   public void signOut()
   {
       auth.signOut();
   }

}
