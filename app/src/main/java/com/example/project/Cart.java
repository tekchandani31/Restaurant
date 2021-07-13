package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    Button gotobill;
    private cartAdapter cartadapt;
    private ArrayList<Cartgetset> cartArrayList;
    FirebaseFirestore db;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = findViewById(R.id.cartrecyc);
        loading = findViewById(R.id.progbar);
        db = FirebaseFirestore.getInstance();
        cartArrayList = new ArrayList<>();
        //  Bundle bundle = getIntent().getExtras();
        gotobill = findViewById(R.id.btn_gotobill);
        /*if(bundle != null){
            String name = bundle.getString("name");
            String type = bundle.getString("type");
            String cost = bundle.getString("money");
            Cartgetset cartItems = new Cartgetset(name,type,cost);
            cartArrayList.add(cartItems);


        }*/


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartadapt = new cartAdapter(this,cartArrayList);
        recyclerView.setAdapter(cartadapt);

        CollectionReference cr = db.collection("cartItem");
        cr.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    loading.setVisibility(View.GONE);
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        Cartgetset i = d.toObject(Cartgetset.class);
                        /*String docId = i.getDocumentId();
                        i.setDocumentId(docId);*/
                        cartArrayList.add(i);

                    }
                    cartadapt.notifyDataSetChanged();
                }

                else {
                    Toast.makeText(Cart.this, "No data found in database", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(Cart.this, "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });

    }

}