package com.example.myapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Model.Expense;
import com.example.myapp.R;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {
    private final ArrayList<Expense> listData;
    public ExpenseAdapter(ArrayList<Expense> listData){

        this.listData = listData;

    }
    @NonNull
    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View bannerView = layoutInflater.inflate(R.layout.expense_row, parent, false);
        ExpenseAdapter.ViewHolder viewHolder = new ExpenseAdapter.ViewHolder(bannerView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ViewHolder holder, int position) {


            final Expense expense = listData.get(position);

            holder.titleTV.setText(expense.getTitle());
            holder.priceTV.setText(expense.getPrice());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTV, priceTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.titleTV);
            priceTV = itemView.findViewById(R.id.priceTV);
        }
    }
}
