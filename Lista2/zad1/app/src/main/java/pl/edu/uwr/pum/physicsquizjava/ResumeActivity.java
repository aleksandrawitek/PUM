package pl.edu.uwr.pum.physicsquizjava;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


public class ResumeActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textViewresume;
    private int i;
    private int points;
    private final int points_possible=10;
    private int incorrect;
    private int answered;
    private View trueButton;
    private View falseButton;
    private String resume;




    private final Question[] questions = new Question[]{
            new Question(R.string.question1, true, false),
            new Question(R.string.question2, true, false),
            new Question(R.string.question3, true, false),
            new Question(R.string.question4, false, false),
            new Question(R.string.question5, true, false),
            new Question(R.string.question6, false, false),
            new Question(R.string.question7, false, false),
            new Question(R.string.question8, false, false),
            new Question(R.string.question9, true, false),
            new Question(R.string.question10, false, false),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.question_text);
        i=0;
        points = 0;
        incorrect = 0;
        answered = 0;
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        if (savedInstanceState != null)
        {
            i = savedInstanceState.getInt("i");
            points = savedInstanceState.getInt("points");
            incorrect = savedInstanceState.getInt("incorrect");


        }
        textView.setText(questions[i].getTextId());
    }


    public void next(View view)
    {
        i+=1;

        if (Objects.equals(i,10))
        {
            i = 0;
        }

        if (Objects.equals(questions[i].isChecked(), true))
        {
            trueButton.setVisibility(View.GONE);
            trueButton.setClickable(false);
            falseButton.setVisibility(View.GONE);
            falseButton.setClickable(false);
        }

        else
        {
            trueButton.setVisibility(View.VISIBLE);
            trueButton.setClickable(true);
            falseButton.setVisibility(View.VISIBLE);
            falseButton.setClickable(true);
        }

        textView.setText(questions[i].getTextId());

    }

    public void previous(View view)
    {
        i-=1;

        if (Objects.equals(i,-1))
        {
            i = 9;
        }

        if (Objects.equals(questions[i].isChecked(), true))
        {
            trueButton.setVisibility(View.GONE);
            trueButton.setClickable(false);
            falseButton.setVisibility(View.GONE);
            falseButton.setClickable(false);
        }

        else
        {
            trueButton.setVisibility(View.VISIBLE);
            trueButton.setClickable(true);
            falseButton.setVisibility(View.VISIBLE);
            falseButton.setClickable(true);
        }

        textView.setText(questions[i].getTextId());


    }

    public void anstrue(View view)
    {

        if(Objects.equals(true,questions[i].isAnswer()))
        {
            points += 1;
        }
        else
        {
            incorrect +=1;
        }

        answered += 1;

        questions[i].setChecked(true);

        if (Objects.equals(answered, 10))
        {
            setContentView(R.layout.activity_resume);
            textViewresume = findViewById(R.id.question_resume);
            resume = "Resume: \n Correct: " + Integer.toString(points) + "\n Incorrect: " + Integer.toString(incorrect) + "\n Score: " + Integer.toString(points*100/points_possible) + "%";
            textViewresume.setText(resume);
        }

    }

    public void ansfalse(View view)
    {
        if(Objects.equals(false,questions[i].isAnswer()))
        {
            points += 1;
        }
        else
        {
            incorrect +=1;
        }

        answered += 1;
        questions[i].setChecked(true);

        if (Objects.equals(answered, 10))
        {
            setContentView(R.layout.activity_resume);
            textViewresume = findViewById(R.id.question_resume);
            resume = "Resume: \n Correct:" + Integer.toString(points) + "\n Incorrect: " + Integer.toString(incorrect) + "\n Score: " + Integer.toString(points*100/points_possible) + "%";
            textViewresume.setText(resume);

        }

    }

    public void retry(View view)
    {
        setContentView(R.layout.activity_main);

    }


}