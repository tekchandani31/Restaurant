package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Spinner spinner;
    //TextView txtbgroup,txtgender;
    EditText edtemail,edtname,edtgst,edtpass,edtconpass,edtmobile;
    Button btnregis;
    userData user;
    FirebaseFirestore firebaseFirestore;
    ProgressDialog loading;
    private FirebaseAuth mAuth;
    //RadioGroup radioGroup;
    //RadioButton radiomale,radiofemale,genderradiob;
    //String group[] = {"A+","B+","AB+","O+","A-","B-","AB-","O-"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
      //  txtbgroup = findViewById(R.id.txt_ph);
        //txtgender = findViewById(R.id.txt_gen);
        edtemail = findViewById(R.id.edt_mail);
        edtname = findViewById(R.id.edt_name);
        edtgst = findViewById(R.id.edt_gst);
        edtpass = findViewById(R.id.edt_pass);
        edtconpass = findViewById(R.id.edt_conf);
        edtmobile = findViewById(R.id.edt_mob);
        btnregis = findViewById(R.id.btn_regis);
        mAuth=FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        loading=new ProgressDialog(SignUp.this);
        /*radiomale = findViewById(R.id.radio_male);
        radiofemale = findViewById(R.id.radio_female);
        radioGroup = findViewById(R.id.radio_group);

        spinner = findViewById(R.id.spin_ph);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, group);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);*/


        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String name =edtname.getText().toString();
                String email =edtemail.getText().toString();
                String gst =edtgst.getText().toString();
                String pass =edtpass.getText().toString();
                String conpass =edtconpass.getText().toString();
                String mobile =edtmobile.getText().toString();
                /*int selectId= radioGroup.getCheckedRadioButtonId();
                genderradiob = findViewById(selectId);
                String gender = genderradiob.getText().toString();*/

                if(name.isEmpty())
                {
                    edtname.setError("Enter the name");
                    edtname.requestFocus();
                }
                else if(gst.isEmpty() || gst.length()<12)
                {
                    edtgst.setError("Enter a valid gst number");
                    edtgst.requestFocus();
                }
                else if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    edtemail.setError("Enter a valid email address");
                    edtemail.requestFocus();
                }
                else if(mobile.isEmpty() || mobile.length()<10)
                {
                    edtmobile.setError("Incorrect Mobile no.");
                    edtmobile.requestFocus();
                }
                else if(pass.isEmpty() || pass.length()<6)
                {
                    edtpass.setError("Incorrect password");
                    edtpass.requestFocus();
                }
                else if(conpass.isEmpty() || !conpass.equals(pass))
                {
                    edtconpass.setError("Incorrect password");
                    edtconpass.requestFocus();
                }

                else {
                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUp.this, "Sign Up Complete", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    user= new userData(name,email,gst,pass,conpass,mobile);
                    firebaseFirestore.collection("userData").document(email).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Toast.makeText(SignUp.this, "Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, Login.class);
                            SignUp.this.startActivity(intent);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(SignUp.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //Toast.makeText(this,group[position],Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}