package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class CrimeActivity extends AppCompatActivity {

    private String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crime_activity);
        EditText crimeTxt = findViewById(R.id.crimeTextView);
        CalendarView dateView = findViewById(R.id.calendarView);
        CheckBox solvedBox = findViewById(R.id.solved);
        String crimeTitle = null;
        Date crimeDate = null;
        Crime crime = null;
        Boolean crimeSolved = null;
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Id = extras.getString("Id");
            crime = CrimeLab.getCrime(UUID.fromString(Id));
            crimeTitle = crime.getTitle();
            crimeDate = crime.getDate();
            crimeSolved = crime.getSolved();

        }
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String dateString = df.format(crimeDate);
        crimeTxt.setText(crimeTitle);
        if (crimeSolved.equals(false)){
            solvedBox.setChecked(false);

        }
        if (crimeSolved.equals(true)){
            solvedBox.setChecked(true);
        }

    }

    public void delete(View view) {
        Bundle extras = getIntent().getExtras();
        Id = extras.getString("Id");
        Crime crime;
        crime = CrimeLab.getCrime(UUID.fromString(Id));
        CrimeLab.deleteCrime(crime);
        MainActivity.crimeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        EditText editText = findViewById(R.id.crimeTextView);
        CheckBox checkBox = findViewById(R.id.solved);
        CalendarView dateView = findViewById(R.id.calendarView);
        String newTitle = editText.getText().toString();
        Bundle extras = getIntent().getExtras();
        Id = extras.getString("Id");
        Crime crime;
        crime = CrimeLab.getCrime(UUID.fromString(Id));
        crime.setTitle(newTitle);
        boolean checked = checkBox.isChecked();
        crime.setSolved(checked);
        //Calendar calendar = dateView.getInstance();
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //Date date = new Date(dateView.getDate());
        //crime.setDate(date);
        MainActivity.crimeAdapter.notifyDataSetChanged();
        super.onBackPressed();}



}



