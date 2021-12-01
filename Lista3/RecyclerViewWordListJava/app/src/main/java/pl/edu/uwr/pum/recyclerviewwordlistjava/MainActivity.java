package pl.edu.uwr.pum.recyclerviewwordlistjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<String> crimeList = new LinkedList<String>();
    private RecyclerView recyclerView;
    private CrimeAdapter crimeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 50; i++)
            crimeList.addLast("Crime" + i);

        recyclerView = findViewById(R.id.recyclerView);
        crimeAdapter = new CrimeAdapter(this, crimeList);
        recyclerView.setAdapter(crimeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}