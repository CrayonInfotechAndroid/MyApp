package com.example.myapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapp.Adapters.ExpenseAdapter;
import com.example.myapp.Adapters.RecyclerAdapter;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.Arrays;

public class OnScrollRecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> data = new ArrayList<>();
    public ProgressBar loadingPB;

    boolean isScrolling = false;

    int currentItems, totalItems, scrollOutItems;
    LinearLayoutManager manager;
    RecyclerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_scroll_recycler_view);


        recyclerView =  findViewById(R.id.recyclerView);
        manager = new LinearLayoutManager(this);


        String[] a = {"1","2","3","4", "5","6","7","1","2","3","4", "5","6","7","7","1","2","3","4", "5","6","7"};
        data= new ArrayList<>(Arrays.asList(a));

        adapter = new RecyclerAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                Log.d("recyclerData: ","current: " + currentItems + " scrollItems: " + scrollOutItems + " totalItems: " + totalItems);

                if (isScrolling && ( currentItems+scrollOutItems >= totalItems )){

                    isScrolling =false;
                    getData();
                }
            }
        });
    }
    private void getData(){
        loadingPB.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<5;i++){
                    data.add(Math.random() * 100 + "");
                    adapter.notifyDataSetChanged();
                    loadingPB.setVisibility(View.GONE);
                }
            }
        },5000);
    }

}