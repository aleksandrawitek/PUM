package pl.edu.uwr.pum.recyclerviewwordlistjava;
import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import pl.edu.uwr.pum.recyclerviewwordlistjava.databinding.CrimeActivityBinding;


public class CrimeActivity extends AppCompatActivity {

    private String Id;
    DBHandler dbHandler;
    private static final int CAMERA_INTENT = 2;
    private CrimeActivityBinding binding;
    private Uri savePicturePath=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crime_activity);
        EditText crimeTxt = findViewById(R.id.crimeTextView);
        CheckBox solvedBox = findViewById(R.id.solved);
        ImageView crimePic = findViewById(R.id.cphoto);
        TextView dateTxt = findViewById(R.id.calendarView);
        binding = CrimeActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        Date date = null;
        String crimeTitle = null;
        Crime crime = null;
        Boolean crimeSolved = null;
        String crimePhoto = null;
        dbHandler = new DBHandler(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Id = extras.getString("Id");
            crime = CrimeLab.getCrime(UUID.fromString(Id));
            crimeTitle = crime.getTitle();
            crimeSolved = crime.getSolved();
            date = crime.getDate();
            crimePhoto = crime.getImage();
        }

        crimeTxt.setText(crimeTitle);
        dateTxt.setText(date.toString());
        //crimePhoto.

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
        dbHandler.updateCrime(crime);
        MainActivity.crimeAdapter.notifyDataSetChanged();
        finish();
        overridePendingTransition(0, 0);
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);


    }
    //super.onBackPressed();}

    public void delete(View view) {
        Bundle extras = getIntent().getExtras();
        Id = extras.getString("Id");
        Crime crime;
        crime = CrimeLab.getCrime(UUID.fromString(Id));
        //CrimeLab.deleteCrime(crime);
        dbHandler.deleteCrime(crime);
        MainActivity.crimeAdapter.notifyDataSetChanged();
        finish();
        overridePendingTransition(0, 0);

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);


    }

    public void photo(View view) {

        Dexter.withContext(this).withPermission(
                Manifest.permission.CAMERA
        ).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Intent intent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_INTENT);

            }
            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }
            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                showRationaleDialog();
            }
        }).onSameThread().check();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == CAMERA_INTENT) {
                Bundle extras = getIntent().getExtras();
                Id = extras.getString("Id");
                Crime crime;
                crime = CrimeLab.getCrime(UUID.fromString(Id));
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                binding.cphoto.setImageBitmap(thumbnail);
                savePicturePath = savePicture(thumbnail);
                crime.setImage(savePicturePath.toString());
                Log.d("PICTURE", "Path" + savePicturePath);
            }
        }}

    private Uri savePicture(Bitmap bitmap) {
        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
        File file = wrapper.getDir("myGallery", Context.MODE_PRIVATE);
        file = new File(file, UUID.randomUUID().toString() + ".jpg");

        try {
            OutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return Uri.parse(file.getAbsolutePath());

    }


    private void showRationaleDialog() {
        new AlertDialog.Builder(this)
                .setMessage("This feature requires permissions")
                .setPositiveButton("Ask me", (dialog, which) -> {
                    try {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }
                })
                .setNegativeButton("CANCEL", (dialog, which) -> dialog.dismiss())
                .show();
    }


}