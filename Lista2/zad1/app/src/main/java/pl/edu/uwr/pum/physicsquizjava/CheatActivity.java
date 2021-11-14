package pl.edu.uwr.pum.physicsquizjava;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity
{
    private String resume;
    private TextView textViewresume;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        textViewresume = findViewById(R.id.question_resume);
        Bundle extras = getIntent().getExtras();
        Boolean value = extras.getBoolean("key");
        resume = "Answer: \n" + Boolean.toString(value);
        textViewresume.setText(resume);

    }

    public void back(View view)
    {
       super.onBackPressed();
    }
}