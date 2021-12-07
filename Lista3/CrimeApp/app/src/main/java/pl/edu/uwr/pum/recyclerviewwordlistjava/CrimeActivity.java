package pl.edu.uwr.pum.recyclerviewwordlistjava;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
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
        CheckBox solvedBox = findViewById(R.id.solved);
        TextView dateTxt = findViewById(R.id.calendarView);
        Date date = null;
        String crimeTitle = null;
        Crime crime = null;
        Boolean crimeSolved = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Id = extras.getString("Id");
            crime = CrimeLab.getCrime(UUID.fromString(Id));
            crimeTitle = crime.getTitle();
            crimeSolved = crime.getSolved();
            date = crime.getDate();
        }

        crimeTxt.setText(crimeTitle);
        dateTxt.setText(date.toString());

        if (crimeSolved.equals(false)) {
            solvedBox.setChecked(false);

        }
        if (crimeSolved.equals(true)) {
            solvedBox.setChecked(true);
        }

        TextView datePicker = findViewById(R.id.calendarView);
        final Calendar Cal = Calendar.getInstance();
        int mDate, mMonth, mYear;

        datePicker.setOnClickListener(new View.OnClickListener() {
            private int mDate,mMonth,mYear;
            @Override
            public void onClick(View v) {
                mDate = Cal.get(Calendar.DATE);
                mMonth = Cal.get(Calendar.MONTH);
                mYear = Cal.get(Calendar.YEAR);
                Bundle extras = getIntent().getExtras();
                Id = extras.getString("Id");
                Crime crime2 = CrimeLab.getCrime(UUID.fromString(Id));
                String pattern = "dd-MM-yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                DatePickerDialog datePickerDialog = new DatePickerDialog(CrimeActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker1, int year, int month, int date) {
                        int month1 = new Integer(month) + 1;
                        String dateDisplay = date+"-"+ String.valueOf(month1) +"-"+year;
                        Date dateSet = null;
                        try {
                            dateSet = simpleDateFormat.parse(dateDisplay);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        crime2.setDate(dateSet);
                        MainActivity.crimeAdapter.notifyDataSetChanged();
                        datePicker.setText(crime2.getDate().toString());

                    }
                }, mYear, mMonth, mDate);
                datePickerDialog.show();


            }
        });

    }

    @Override
    public void onBackPressed() {
        EditText editText = findViewById(R.id.crimeTextView);
        CheckBox checkBox = findViewById(R.id.solved);
        ImageView image = findViewById(R.id.warning);
        String newTitle = editText.getText().toString();
        Bundle extras = getIntent().getExtras();
        Id = extras.getString("Id");
        Crime crime;
        crime = CrimeLab.getCrime(UUID.fromString(Id));
        crime.setTitle(newTitle);
        boolean checked = checkBox.isChecked();
        crime.setSolved(checked);
        MainActivity.crimeAdapter.notifyDataSetChanged();
        finish();
    }
    //super.onBackPressed();}

    public void delete(View view) {
        Bundle extras = getIntent().getExtras();
        Id = extras.getString("Id");
        Crime crime;
        crime = CrimeLab.getCrime(UUID.fromString(Id));
        CrimeLab.deleteCrime(crime);
        MainActivity.crimeAdapter.notifyDataSetChanged();
        finish();
    }










}