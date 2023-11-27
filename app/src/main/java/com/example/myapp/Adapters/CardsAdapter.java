package com.example.myapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Model.CardsList;
import com.example.myapp.R;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.MyViewHolder> {

    private final List<CardsList> cardsLists;
    private final Context context;

    public CardsAdapter(List<CardsList> cardsLists, Context context) {
        this.cardsLists = cardsLists;
        this.context = context;
    }

    @NonNull
    @Override
    public CardsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_adapter_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull CardsAdapter.MyViewHolder holder, int position) {
        CardsList list2 = cardsLists.get(position);
        if (list2.isScratchStatus()){
            holder.cardImageView.setImageResource(R.drawable.white);
            holder.winLayout.setVisibility(View.VISIBLE);
            holder.winAmount.setText("10");
        }else{
            holder.cardImageView.setImageResource(R.drawable.scratch_card);
            holder.winLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return cardsLists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout winLayout;
        ImageView cardImageView;

        TextView winAmount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            winLayout = itemView.findViewById(R.id.winLayout);
            cardImageView = itemView.findViewById(R.id.cardImageView);
            winAmount = itemView.findViewById(R.id.winAmount);


        }
    }
}
