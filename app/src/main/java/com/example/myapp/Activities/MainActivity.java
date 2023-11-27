package com.example.myapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapp.Model.DatabaseHelper;
import com.example.myapp.Model.Expense;
import com.example.myapp.Model.MusicService;
import com.example.myapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextInputEditText titleET, priceET;
    Button saveBT,startBT,stopBT,viewBT,scratchBT,recyclerBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleET = findViewById(R.id.titleET);
        priceET = findViewById(R.id.priceET);
        saveBT = findViewById(R.id.saveBT);
        startBT = findViewById(R.id.startBT);
        stopBT = findViewById(R.id.stopBT);
        viewBT = findViewById(R.id.viewBT);
        scratchBT = findViewById(R.id.scratchBT);
        recyclerBT = findViewById(R.id.recyclerBT);

        recyclerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OnScrollRecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        scratchBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ScratchActivity.class);
                startActivity(intent);
            }
        });

        viewBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ViewExpenseActivity.class);
                startActivity(intent);
            }
        });

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);

        saveBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleValue = Objects.requireNonNull(titleET.getText()).toString().trim();
                String priceValue = Objects.requireNonNull(priceET.getText()).toString().trim();

                if (titleValue.equals("") && priceValue.equals("")){

                    Toast.makeText(getApplicationContext(), "Fill All Fields", Toast.LENGTH_SHORT).show();
                }else {
                    databaseHelper.expenseDAO().addTransaction( new Expense(titleValue, priceValue));

                    ArrayList<Expense> arrayList = (ArrayList<Expense>) databaseHelper.expenseDAO().getAllExpense();
                    for (int i=0;i<arrayList.size();i++){

                        Log.d("Data", "Title:"+arrayList.get(i).getTitle() + " Amount: " + arrayList.get(i).getPrice());
                    }
                }

            }
        });

        startBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MusicService.class));
            }
        });
        stopBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, MusicService.class));
            }
        });
    }
}