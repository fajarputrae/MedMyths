package com.coinbkt.medmyth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class FactsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FactsAdapter adapter;

    List<Facts> facts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        ImageButton backButton = findViewById(R.id.backBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FactsActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(FactsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new FactsAdapter(this.facts);
        recyclerView.setAdapter(adapter);

        populate();

    }

    private void populate() {
        int[] factImg = new int[]{
                R.drawable.facts_placeholder
        };

        this.facts.add(new Facts("Rematik", factImg[0]));
        this.facts.add(new Facts("Pilek", factImg[0]));
        this.facts.add(new Facts("Batuk", factImg[0]));
        this.facts.add(new Facts("Demam", factImg[0]));
        this.facts.add(new Facts("Pusing", factImg[0]));

    }
}
