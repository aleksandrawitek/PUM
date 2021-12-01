package pl.edu.uwr.pum.recyclerviewwordlistjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<String> crimeList = new LinkedList<>();
    private RecyclerView recyclerView;
    private Crime crime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for (int i = 0; i < 50; i++)
            //crimeList.addLast("Word" + i);

        recyclerView = findViewById(R.id.recyclerView);
        crime = new Crime(this, crimeList);
        recyclerView.setAdapter(crime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}