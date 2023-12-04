package com.example.myapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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
    Button saveBT,startBT,stopBT,viewBT,scratchBT,recyclerBT,showAlertBT;

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
        showAlertBT = findViewById(R.id.showAlertBT);

        showAlertBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // instance of alert dialog to build alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.alert);
                builder.setTitle("Alert! Please Update Your App");
                builder.setMessage("Some New Features Available in New update");
                builder.setCancelable(false);

                // set the neutral button to do some actions
                builder.setNeutralButton("DISMISS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Alert Dialog Dismissed", Toast.LENGTH_SHORT).show();
                    }
                });

                // set the positive button to do some actions
                builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "OKAY", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Objects.requireNonNull(alertDialog.getWindow()).setGravity(Gravity.TOP);
            }
        });

        recyclerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OnScrollRecyclerViewActivity.class);
                startActivity(intent);
                //new Comment
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