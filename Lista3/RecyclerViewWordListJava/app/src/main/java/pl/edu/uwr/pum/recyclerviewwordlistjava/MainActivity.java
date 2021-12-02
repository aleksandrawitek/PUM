package pl.edu.uwr.pum.recyclerviewwordlistjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Crime> crimeList = CrimeLab.get(this).getCrimes();
    private RecyclerView recyclerView;
    private CrimeAdapter crimeAdapter;
    private CrimeAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCrimeAdapter();

    }

    private void setCrimeAdapter(){
        setOnClickListener();
        recyclerView = findViewById(R.id.recyclerView);
        crimeAdapter = new CrimeAdapter(this, crimeList, listener);
        recyclerView.setAdapter(crimeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    private void setOnClickListener(){
        listener = new CrimeAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(),CrimeActivity.class);
                intent.putExtra("Title", crimeList.get(position).getTitle());
                startActivity(intent);

            }
        };
    }
}