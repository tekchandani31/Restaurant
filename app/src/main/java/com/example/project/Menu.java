package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    private ItemsAdapter itemsAdapter;
    private RecyclerView recyclerView;
    private ArrayList<itemData> menuArrayList;
   // private ArrayList<Cartgetset> cartgetsetArrayList;
    private FirebaseFirestore db;
    ProgressBar loading;
    FloatingActionButton floatingActionButton;
    FirebaseAuth mAuth;
   // private Object cartgetset;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        recyclerView=findViewById(R.id.recyc_menu);
        loading=findViewById(R.id.progbar);
        floatingActionButton=findViewById(R.id.btn_floating);
        db= FirebaseFirestore.getInstance();
        menuArrayList=new ArrayList<>();
        //cartgetsetArrayList=getCartgetset(false, isSelect);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        itemsAdapter = new ItemsAdapter(menuArrayList,this);
        recyclerView.setAdapter(itemsAdapter);
       // cartgetsetArrayList=new ArrayList<>();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Menu.this,Cart.class));
            }
        });

      /*  FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(this,Login.class));
            this.finish();
        }else{
            Toast.makeText(this, currentUser.getEmail(), Toast.LENGTH_SHORT).show();
        }*/

        CollectionReference cr=db.collection("itemData");
        cr.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty())
                {
                    loading.setVisibility(View.GONE);
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d:list)
                    {
                        itemData i= d.toObject(itemData.class);
                        /*String docId = i.getDocumentId();
                        i.setDocumentId(docId);*/
                        menuArrayList.add(i);

                    }
                    itemsAdapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(Menu.this, "No data found in database", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(Menu.this, "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bill:
                Intent i =new Intent(Menu.this,Bill.class);
                Menu.this.startActivity(i);
                Toast.makeText(this, "Bill", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cart:
                Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add:
                Intent i2=new Intent(Menu.this,Add.class);
                Menu.this.startActivity(i2);
                break;
            case R.id.del:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit:
                Toast.makeText(this, "edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.care:
                Toast.makeText(this, "care", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Intent i3=new Intent(Menu.this,Settings.class);
                Menu.this.startActivity(i3);
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.issue:
                Toast.makeText(this, "issue", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

}