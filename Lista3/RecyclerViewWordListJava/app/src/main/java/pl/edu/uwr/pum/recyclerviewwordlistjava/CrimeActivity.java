package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class CrimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crime_activity);
        TextView crimeTxt = findViewById(R.id.crimeTextView);
        String crimeTitle = "Crime";
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            crimeTitle = extras.getString("Title");

        }
        crimeTxt.setText(crimeTitle);

    }

}
