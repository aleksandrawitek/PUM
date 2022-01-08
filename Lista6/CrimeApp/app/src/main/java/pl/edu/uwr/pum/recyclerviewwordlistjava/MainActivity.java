package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private List<Crime> crimeList = CrimeLab.get(this).getCrimes();
    private RecyclerView recyclerView;
    public static CrimeAdapter crimeAdapter;
    private CrimeAdapter.RecyclerViewClickListener listener;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new DBHandler(this);
        setCrimeAdapter();
        getCrimes();
    }

    private void setCrimeAdapter(){
        setOnClickListener();
        recyclerView = findViewById(R.id.recyclerView);
        crimeAdapter = new CrimeAdapter(this, crimeList, listener);
        recyclerView.setAdapter(crimeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onDestroy() {
        dbHandler.close();
        super.onDestroy();
    }

    private void getCrimes(){
        crimeList.clear();
        Cursor cursor = dbHandler.getCrime();
        if(cursor.getCount() == 0)
            Toast.makeText(this, "EMPTY", Toast.LENGTH_SHORT).show();
        else{
            while(cursor.moveToNext()){
                int idtable = cursor.getInt(0);
                String title = cursor.getString(1);
                UUID id = UUID.fromString(cursor.getString(2));
                Date date = new Date(cursor.getString(3));
                Boolean solved = cursor.getInt(4) > 0;
                //String photo = cursor.getString(5);
                Crime crime = new Crime();
                crime.setId(id);
                crime.setTitle(title);
                crime.setDate(date);
                crime.setSolved(solved);
                //crime.setImage(photo);
                crimeList.add(crime);
            }

            }
    }

    private void setOnClickListener(){
        listener = new CrimeAdapter.RecyclerViewClickListener() {
            @Override public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(),CrimeActivity.class);
                UUID Id = crimeList.get(position).getId();
                intent = intent.putExtra("Id", Id.toString());
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                overridePendingTransition(0, 0);

            }
        };
    }

    public void add(View view) {
        Date date = new Date();
        Crime crime = new Crime();
        crime.setDate(date);
        crime.setTitle("Crime Title");
        int last = dbHandler.getCrime().getCount();
        UUID Id = UUID.randomUUID();
        crime.setId(Id);
        crimeAdapter.notifyDataSetChanged();
        dbHandler.addCrime(crime);
        getCrimes();
        crimeAdapter.notifyDataSetChanged();
        Intent intent = new Intent(getApplicationContext(),CrimeActivity.class);
        intent = intent.putExtra("Id", Id.toString());
        startActivity(intent);
        overridePendingTransition(0, 0);
        recyclerView.scrollToPosition(last);
    }

    public void search(View view)
    {
        EditText crimeName = findViewById(R.id.crimeName);
        crimeList.clear();
        Cursor cursor = dbHandler.searchCrime(crimeName.getText().toString());
        crimeAdapter.notifyDataSetChanged();
        while(cursor.moveToNext()){

            int idtable = cursor.getInt(0);
            String title = cursor.getString(1);
            UUID id = UUID.fromString(cursor.getString(2));
            Date date = new Date(cursor.getString(3));
            Boolean solved = cursor.getInt(4) > 0;
            //String photo = cursor.getString(5);
            Crime crime = new Crime();
            crime.setId(id);
            crime.setTitle(title);
            crime.setDate(date);
            crime.setSolved(solved);
            //crime.setImage(photo);
            crimeList.add(crime);
        }

    }

    public void clear (View view)
    {
        EditText crimeName = findViewById(R.id.crimeName);
        crimeList.clear();
        crimeName.setText("Crime Name");
        Cursor cursor = dbHandler.searchCrime("");
        crimeAdapter.notifyDataSetChanged();
        while(cursor.moveToNext()){
            int idtable = cursor.getInt(0);
            String title = cursor.getString(1);
            UUID id = UUID.fromString(cursor.getString(2));
            Date date = new Date(cursor.getString(3));
            Boolean solved = cursor.getInt(4) > 0;
            //String photo = cursor.getString(5);
            Crime crime = new Crime();
            crime.setId(id);
            crime.setTitle(title);
            crime.setDate(date);
            crime.setSolved(solved);
            //crime.setImage(photo);
            crimeList.add(crime);
        }
    }

}