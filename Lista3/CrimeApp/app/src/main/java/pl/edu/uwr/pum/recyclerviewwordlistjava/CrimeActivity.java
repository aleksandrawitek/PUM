package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class CrimeActivity extends AppCompatActivity {

    private String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crime_activity);
        EditText crimeTxt = findViewById(R.id.crimeTextView);
        CalendarView dateView = findViewById(R.id.calendarView);
        String crimeTitle = null;
        Date crimeDate = null;
        Crime crime = null;
        Boolean crimeSolved;
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Id = extras.getString("Id");
            crime = CrimeLab.getCrime(UUID.fromString(Id));
            crimeTitle = crime.getTitle();
            crimeDate = crime.getDate();
            crimeSolved = crime.getSolved();

        }
        crimeTxt.setText(crimeDate.toString());
        //dateView.setDate(crimeDate);

    }

}
