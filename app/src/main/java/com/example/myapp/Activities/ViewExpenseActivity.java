package com.example.myapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myapp.Adapters.ExpenseAdapter;
import com.example.myapp.Model.DatabaseHelper;
import com.example.myapp.Model.Expense;
import com.example.myapp.R;

import java.util.ArrayList;

public class ViewExpenseActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expense);
        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);
        ArrayList<Expense> arrayList = (ArrayList<Expense>) databaseHelper.expenseDAO().getAllExpense();
        for (int i=0;i<arrayList.size();i++){

            Log.d("Data", "Title:"+arrayList.get(i).getTitle() + " Amount: " + arrayList.get(i).getPrice());
        }
        recyclerView = findViewById(R.id.recyclerView);
        ExpenseAdapter adapter = new ExpenseAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}