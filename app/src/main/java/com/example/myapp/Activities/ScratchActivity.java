package com.example.myapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.TextView;

import com.example.myapp.Adapters.CardsAdapter;
import com.example.myapp.Model.CardsList;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class ScratchActivity extends AppCompatActivity {

    private  final List<CardsList> cardsList =  new ArrayList<>();
    TextView coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch);

        coins = findViewById(R.id.coins);
        RecyclerView recyclerView = findViewById(R.id.cardsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(ScratchActivity.this, 2));

        CardsList card1 = new CardsList("1", "10", true);
        cardsList.add(card1);

        CardsList card2 = new CardsList("2", "10", false);
        cardsList.add(card2);

        CardsList card3 = new CardsList("3", "10", false);
        cardsList.add(card3);
        CardsList card4 = new CardsList("4", "10", false);
        cardsList.add(card4);

        CardsList card5 = new CardsList("5", "10", false);
        cardsList.add(card5);

        CardsList card6 = new CardsList("6", "10", false);
        cardsList.add(card6);

        CardsList card7 = new CardsList("7", "10", false);
        cardsList.add(card7);

        CardsList card8 = new CardsList("8", "10", false);
        cardsList.add(card8);

        recyclerView.setAdapter(new CardsAdapter(cardsList, ScratchActivity.this));

    }
}