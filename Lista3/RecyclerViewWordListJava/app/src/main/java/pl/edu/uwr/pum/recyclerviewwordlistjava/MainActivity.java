package pl.edu.uwr.pum.recyclerviewwordlistjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Crime> crimeList = CrimeLab.get(this).getCrimes();
    private RecyclerView recyclerView;
    private CrimeAdapter crimeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        crimeAdapter = new CrimeAdapter(this, crimeList);
        recyclerView.setAdapter(crimeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}