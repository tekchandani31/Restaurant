package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Cartgetset> cartArrayList;

    public cartAdapter(Context context, ArrayList<Cartgetset> cartArrayList) {
        this.context = context;
        this.cartArrayList= cartArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new cartAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_list,parent,false));
    }

    @Override
    public void onBindViewHolder(cartAdapter.ViewHolder holder, int position) {

        Cartgetset cart = cartArrayList.get(position);
        holder.textname.setText(cart.getName());
        holder.texttype.setText(cart.getType());
        holder.textcost.setText(cart.getCost());



    }


    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textname, texttype, textcost;
        private final CardView cardView;
        public ViewHolder(View itemView) {

            super(itemView);
            textname=itemView.findViewById(R.id.txt_cartlistname);
            texttype=itemView.findViewById(R.id.txt_cartlisttype);
            textcost=itemView.findViewById(R.id.txt_cartlistcost);
            cardView = itemView.findViewById(R.id.idCartItem);
        }
    }
}
