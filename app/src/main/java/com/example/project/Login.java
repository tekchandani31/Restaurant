package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Helper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    EditText edtemail,edtpass;
    TextView textregister;
    dbhelperlogin helper;
    Button btnlogin;
    userData user;
    FirebaseFirestore firebaseFirestore;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper = new dbhelperlogin(this);
        edtemail=findViewById(R.id.email);
        edtpass=findViewById(R.id.password);
        textregister=findViewById(R.id.register);
        btnlogin=findViewById(R.id.login);
        firebaseFirestore= FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = edtemail.getText().toString();
                String password = edtpass.getText().toString();
                if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    edtemail.setError("Enter a valid email address");
                    edtemail.requestFocus();
                }
                else if(password.isEmpty() || password.length()<6)
                {
                    edtpass.setError("Incorrect password");
                    edtpass.requestFocus();
                }
                else {
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this, "User authenticated", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Login.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    firebaseFirestore.collection("userData").document(email).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            user = documentSnapshot.toObject(userData.class);
                            String newuser = user.getEmail();
                            String newuserpass = user.getPass();
                            if(email.equals(newuser) && password.equals(newuserpass))
                            {
                            Toast.makeText(Login.this, newuser, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Login.this, Add.class);
                            Login.this.startActivity(i);
                            }
                else{
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(Login.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                    /*Boolean insert = helper.insertuser(email, password);
                    if (insert) {
                        Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();

                    }
                    Toast.makeText(getApplicationContext(), "Logging in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, Add.class);
                    Login.this.startActivity(i);
                    finish();*/
                }
            }
        });

        textregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,SignUp.class);
                Login.this.startActivity(intent);
            }
        });
    }
}