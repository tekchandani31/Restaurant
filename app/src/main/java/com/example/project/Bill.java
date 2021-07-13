package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bill extends AppCompatActivity {
    EditText name,address,email;
    Button btnprint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        name=findViewById(R.id.edt_clientname);
        address=findViewById(R.id.edt_clientadd);
        email=findViewById(R.id.edt_clientmail);
        btnprint=findViewById(R.id.btn_print);
        btnprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Bill Printed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}