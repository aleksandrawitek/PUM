package pl.edu.uwr.pum.recyclerviewwordlistjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private List<Crime> crimeList = CrimeLab.get(this).getCrimes();
    private RecyclerView recyclerView;
    public static CrimeAdapter crimeAdapter;
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
                UUID Id = crimeList.get(position).getId();
                intent = intent.putExtra("Id", Id.toString());
                startActivity(intent);

            }
        };
    }

    public void add(View view) {
        Date date = new Date();
        Crime crime = new Crime();
        crime.setDate(date);
        crime.setTitle("Crime Title");
        UUID Id = UUID.randomUUID();
        crime.setId(Id);
        CrimeLab.addCrime(crime);
        int last = crimeAdapter.getItemCount()-1;
        crimeAdapter.notifyDataSetChanged();
        Intent intent = new Intent(getApplicationContext(),CrimeActivity.class);
        intent = intent.putExtra("Id", Id.toString());
        startActivity(intent);
        recyclerView.scrollToPosition(last);


    }
}