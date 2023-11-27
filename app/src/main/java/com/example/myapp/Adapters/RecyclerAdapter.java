package com.example.myapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapp.R;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private final ArrayList<String> listData;
    public RecyclerAdapter(ArrayList<String> listData){

        this.listData = listData;

    }
    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View bannerView = layoutInflater.inflate(R.layout.recycler_row, parent, false);
        RecyclerAdapter.MyViewHolder viewHolder = new RecyclerAdapter.MyViewHolder(bannerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {

        String num = listData.get(position);
        holder.number.setText(num);

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView number;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           // number = itemView.findViewById(R.id.number);
        }
    }
}
