package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.ContentValues.TAG;

public class Add extends AppCompatActivity {
    private EditText itemnameedt, itemtypeedt, priceedt;
    private Button add, viewmenu;
    private String itemName,itemType,itemCost;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        db=FirebaseFirestore.getInstance();
        itemnameedt=findViewById(R.id.edt_itemname);
        itemtypeedt=findViewById(R.id.edt_itemtype);
        priceedt=findViewById(R.id.edt_price);
        add=findViewById(R.id.addnewitem);
        viewmenu=findViewById(R.id.viewitems);
        viewmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Add.this,Menu.class);
                startActivity(i);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemName = itemnameedt.getText().toString().trim();
                itemType = itemtypeedt.getText().toString().trim();
                itemCost = priceedt.getText().toString().trim();

                if(TextUtils.isEmpty(itemName))
                {
                    itemnameedt.setError("Please enter an item");

                }
                else if(TextUtils.isEmpty(itemType))
                {
                    itemtypeedt.setError("Please enter item's type");
                }
                else if(TextUtils.isEmpty(itemCost))
                {
                    priceedt.setError("Please enter cost of the item");
                }
                else
                {
                    addDataToFirestore(itemName,itemType,itemCost);

                }
            }
        });
    }

    private void addDataToFirestore(String itemName, String itemType, String itemCost) {

        DocumentReference dr = db.collection("itemData").document();
        itemData data = new itemData();
        data.setItemName(itemName);
        data.setItemType(itemType);
        data.setItemCost(itemCost);
        data.setDocumentId(dr.getId());

        dr.set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Add.this, "  Item added successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Add.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        /*CollectionReference dbItems = db.collection("itemData");
        itemData data = new itemData(itemName,itemType,itemCost);
        dbItems.add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(Add.this, "  Item added successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(Add.this, "Failed \n"+e, Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}