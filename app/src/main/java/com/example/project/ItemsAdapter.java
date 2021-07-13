package com.example.project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>
{
    
    private ArrayList<itemData> menuArrayList;
    private AlertDialog.Builder builder;

   // public ArrayList<Cartgetset> cartgetsetArrayList;
    private Context context;

    public ItemsAdapter(ArrayList<itemData> menuArrayList, Context context)
    {
        this.context=context;
        this.menuArrayList=menuArrayList;
      //  this.cartgetsetArrayList=cartgetsetArrayList;
    }
    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position)
    {
        itemData item = menuArrayList.get(position);
        holder.textname.setText(item.getItemName());
        holder.texttype.setText(item.getItemType());
        holder.textcost.setText(item.getItemCost());

        builder = new AlertDialog.Builder(context);
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                builder.setCancelable(false);
                builder.setMessage("Delete this item");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String delItem = item.getDocumentId();
                        holder.dbCollection.document(delItem).delete();
                        menuArrayList.remove(position);
                        notifyItemChanged(position);
                        notifyItemRangeChanged(position, menuArrayList.size());
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(context,"Item not deleted",Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Are you sure");
                alert.show();

                return false;
            }
        });

        holder.addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cartgetset cartdata = new Cartgetset();
                cartdata.setName(item.getItemName());
                cartdata.setType(item.getItemType());
                cartdata.setCost(item.getItemCost());

                holder.ic.set(cartdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(context.getApplicationContext(), "  Item added successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(context.getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        /*holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = item.getItemName();
                String type = item.getItemType();
                String money = item.getItemCost();
                Bundle bundle = new Bundle();
                bundle.putString("name",name);



                Toast.makeText(context, name+" "+type+" "+money+" ", Toast.LENGTH_SHORT).show();

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textname, texttype, textcost;
        private final CardView cardView;
        private final Button addcart;
        FirebaseFirestore db;
        CollectionReference dbCollection;
        DocumentReference ic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            db = FirebaseFirestore.getInstance();
            dbCollection = db.collection("itemData");
            ic = db.collection("cartItem").document();
            textname=itemView.findViewById(R.id.txt_listname);
            texttype=itemView.findViewById(R.id.txt_listtype);
            textcost=itemView.findViewById(R.id.txt_listcost);
            cardView = itemView.findViewById(R.id.idMenuItem);
            addcart = itemView.findViewById(R.id.btn_itmcart);
        }
    }
}
