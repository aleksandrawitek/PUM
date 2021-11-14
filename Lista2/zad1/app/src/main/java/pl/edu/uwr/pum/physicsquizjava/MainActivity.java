package pl.edu.uwr.pum.physicsquizjava;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import java.util.*;
import android.content.Intent;
import android.graphics.Color;
import android.widget.TextView;
import androidx.annotation.NonNull;



public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textViewresume;
    private int i;
    private int points;
    private final int points_possible=10;
    private int incorrect;
    private int answered;
    private View trueButton;
    private View falseButton;
    private View cheatButton;
    private TextView correctText;
    private String resume;

    //ilosc zmiennych ze wzgledu na przekazanie ich podczas rotacji ekranu

    private boolean questions0;
    private boolean questions1;
    private boolean questions2;
    private boolean questions3;
    private boolean questions4;
    private boolean questions5;
    private boolean questions6;
    private boolean questions7;
    private boolean questions8;
    private boolean questions9;
    private int k;
    private int cheated;
    private View checkButton;
    private View internetButton;

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
        cheated = 0;
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        cheatButton = findViewById(R.id.cheat_button);
        checkButton = findViewById(R.id.check_button);
        correctText = findViewById(R.id.info_text);
        correctText.setVisibility(View.GONE);
        checkButton.setVisibility(View.GONE);
        checkButton.setClickable(false);
        internetButton = findViewById(R.id.internet_button);
        internetButton.setVisibility(View.GONE);
        internetButton.setClickable(false);

        if (savedInstanceState != null)
        {
            i = savedInstanceState.getInt("i");
            points = savedInstanceState.getInt("points");
            incorrect = savedInstanceState.getInt("incorrect");
            cheated = savedInstanceState.getInt("cheated");
            answered = savedInstanceState.getInt("answered");
            questions0 = savedInstanceState.getBoolean("questions0");
            questions1 = savedInstanceState.getBoolean("questions1");
            questions2 = savedInstanceState.getBoolean("questions2");
            questions3 = savedInstanceState.getBoolean("questions3");
            questions4 = savedInstanceState.getBoolean("questions4");
            questions5 = savedInstanceState.getBoolean("questions5");
            questions6 = savedInstanceState.getBoolean("questions6");
            questions7 = savedInstanceState.getBoolean("questions7");
            questions8 = savedInstanceState.getBoolean("questions8");
            questions9 = savedInstanceState.getBoolean("questions9");

            if (Objects.equals(answered, 10))
            {
                setContentView(R.layout.activity_resume);
                textViewresume = findViewById(R.id.question_resume);
                resume = "Resume: \n Correct: " + Integer.toString(points) + "\n Incorrect: " + Integer.toString(incorrect) +  "\n Cheated (-5% for each): " + Integer.toString(cheated) + "\n Score: " + Integer.toString((points*100-cheated*50)/points_possible) + "%";
                textViewresume.setText(resume);
            }

            if (Objects.equals(questions[i].isChecked(), true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
            if (Objects.equals(i, 0))
            {
                if (Objects.equals(questions0, true))
                {
                    trueButton.setVisibility(View.GONE);
                    trueButton.setClickable(false);
                    falseButton.setVisibility(View.GONE);
                    falseButton.setClickable(false);
                    cheatButton.setVisibility(View.GONE);
                    cheatButton.setClickable(false);
                    checkButton.setVisibility(View.VISIBLE);
                    checkButton.setClickable(true);
                    internetButton.setVisibility(View.VISIBLE);
                    internetButton.setClickable(true);
                }
            }

            if (Objects.equals(i, 1))
            {
                if (Objects.equals(questions1, true))
                {
                    trueButton.setVisibility(View.GONE);
                    trueButton.setClickable(false);
                    falseButton.setVisibility(View.GONE);
                    falseButton.setClickable(false);
                    cheatButton.setVisibility(View.GONE);
                    cheatButton.setClickable(false);
                    checkButton.setVisibility(View.VISIBLE);
                    checkButton.setClickable(true);
                    internetButton.setVisibility(View.VISIBLE);
                    internetButton.setClickable(true);
                }
            }

            if (Objects.equals(i, 2))
            {
                if (Objects.equals(questions2, true))
                {
                    trueButton.setVisibility(View.GONE);
                    trueButton.setClickable(false);
                    falseButton.setVisibility(View.GONE);
                    falseButton.setClickable(false);
                    cheatButton.setVisibility(View.GONE);
                    cheatButton.setClickable(false);
                    checkButton.setVisibility(View.VISIBLE);
                    checkButton.setClickable(true);
                    internetButton.setVisibility(View.VISIBLE);
                    internetButton.setClickable(true);
                }
            }

            if (Objects.equals(i, 3))
            {
                if (Objects.equals(questions3, true))
                {
                    trueButton.setVisibility(View.GONE);
                    trueButton.setClickable(false);
                    falseButton.setVisibility(View.GONE);
                    falseButton.setClickable(false);
                    cheatButton.setVisibility(View.GONE);
                    cheatButton.setClickable(false);
                    checkButton.setVisibility(View.VISIBLE);
                    checkButton.setClickable(true);
                    internetButton.setVisibility(View.VISIBLE);
                    internetButton.setClickable(true);
                }
            }

            if (Objects.equals(i, 4))
            {
                if (Objects.equals(questions4, true))
                {
                    trueButton.setVisibility(View.GONE);
                    trueButton.setClickable(false);
                    falseButton.setVisibility(View.GONE);
                    falseButton.setClickable(false);
                    cheatButton.setVisibility(View.GONE);
                    cheatButton.setClickable(false);
                    checkButton.setVisibility(View.VISIBLE);
                    checkButton.setClickable(true);
                    internetButton.setVisibility(View.VISIBLE);
                    internetButton.setClickable(true);
                }
            }

            if (Objects.equals(i, 5))
            {
                if (Objects.equals(questions5, true))
                {
                    trueButton.setVisibility(View.GONE);
                    trueButton.setClickable(false);
                    falseButton.setVisibility(View.GONE);
                    falseButton.setClickable(false);
                    cheatButton.setVisibility(View.GONE);
                    cheatButton.setClickable(false);
                    checkButton.setVisibility(View.VISIBLE);
                    checkButton.setClickable(true);
                    internetButton.setVisibility(View.VISIBLE);
                    internetButton.setClickable(true);
                }
            }

            if (Objects.equals(i, 6))
            {
                if (Objects.equals(questions6, true))
                {
                    trueButton.setVisibility(View.GONE);
                    trueButton.setClickable(false);
                    falseButton.setVisibility(View.GONE);
                    falseButton.setClickable(false);
                    cheatButton.setVisibility(View.GONE);
                    cheatButton.setClickable(false);
                    checkButton.setVisibility(View.VISIBLE);
                    checkButton.setClickable(true);
                    internetButton.setVisibility(View.VISIBLE);
                    internetButton.setClickable(true);
                }
            }

            if (Objects.equals(i, 7))
            {
                if (Objects.equals(questions7, true))
                {
                    trueButton.setVisibility(View.GONE);
                    trueButton.setClickable(false);
                    falseButton.setVisibility(View.GONE);
                    falseButton.setClickable(false);
                    cheatButton.setVisibility(View.GONE);
                    cheatButton.setClickable(false);
                    checkButton.setVisibility(View.VISIBLE);
                    checkButton.setClickable(true);
                    internetButton.setVisibility(View.VISIBLE);
                    internetButton.setClickable(true);
                }
            }

            if (Objects.equals(i, 8))
            {
                if (Objects.equals(questions8, true))
                {
                    trueButton.setVisibility(View.GONE);
                    trueButton.setClickable(false);
                    falseButton.setVisibility(View.GONE);
                    falseButton.setClickable(false);
                    cheatButton.setVisibility(View.GONE);
                    cheatButton.setClickable(false);
                    checkButton.setVisibility(View.VISIBLE);
                    checkButton.setClickable(true);
                    internetButton.setVisibility(View.VISIBLE);
                    internetButton.setClickable(true);
                }
            }

            if (Objects.equals(i, 9))
            {
                if (Objects.equals(questions9, true))
                {
                    trueButton.setVisibility(View.GONE);
                    trueButton.setClickable(false);
                    falseButton.setVisibility(View.GONE);
                    falseButton.setClickable(false);
                    cheatButton.setVisibility(View.GONE);
                    cheatButton.setClickable(false);
                    checkButton.setVisibility(View.VISIBLE);
                    checkButton.setClickable(true);
                    internetButton.setVisibility(View.VISIBLE);
                    internetButton.setClickable(true);
                }
            }



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
            cheatButton.setVisibility(View.GONE);
            cheatButton.setClickable(false);
            checkButton.setVisibility(View.VISIBLE);
            checkButton.setClickable(true);
            internetButton.setVisibility(View.VISIBLE);
            internetButton.setClickable(true);
        }

        else
        {
            trueButton.setVisibility(View.VISIBLE);
            trueButton.setClickable(true);
            falseButton.setVisibility(View.VISIBLE);
            falseButton.setClickable(true);
            cheatButton.setVisibility(View.VISIBLE);
            cheatButton.setClickable(true);
            checkButton.setVisibility(View.GONE);
            checkButton.setClickable(false);
            internetButton.setVisibility(View.GONE);
            internetButton.setClickable(false);
        }

        if (Objects.equals(i, 0))
        {
            if (Objects.equals(questions0, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 1))
        {
            if (Objects.equals(questions1, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 2))
        {
            if (Objects.equals(questions2, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 3))
        {
            if (Objects.equals(questions3, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 4))
        {
            if (Objects.equals(questions4, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 5))
        {
            if (Objects.equals(questions5, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 6))
        {
            if (Objects.equals(questions6, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 7))
        {
            if (Objects.equals(questions7, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 8))
        {
            if (Objects.equals(questions8, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 9))
        {
            if (Objects.equals(questions9, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        textView.setText(questions[i].getTextId());
        correctText.setVisibility(View.GONE);


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
            cheatButton.setVisibility(View.GONE);
            cheatButton.setClickable(false);
            checkButton.setVisibility(View.VISIBLE);
            checkButton.setClickable(true);
            internetButton.setVisibility(View.VISIBLE);
            internetButton.setClickable(true);
        }

        else
        {
            trueButton.setVisibility(View.VISIBLE);
            trueButton.setClickable(true);
            falseButton.setVisibility(View.VISIBLE);
            falseButton.setClickable(true);
            cheatButton.setVisibility(View.VISIBLE);
            cheatButton.setClickable(true);
            checkButton.setVisibility(View.GONE);
            checkButton.setClickable(false);
            internetButton.setVisibility(View.GONE);
            internetButton.setClickable(false);
        }
        if (Objects.equals(i, 0))
        {
            if (Objects.equals(questions0, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 1))
        {
            if (Objects.equals(questions1, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 2))
        {
            if (Objects.equals(questions2, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 3))
        {
            if (Objects.equals(questions3, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 4))
        {
            if (Objects.equals(questions4, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 5))
        {
            if (Objects.equals(questions5, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 6))
        {
            if (Objects.equals(questions6, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 7))
        {
            if (Objects.equals(questions7, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 8))
        {
            if (Objects.equals(questions8, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }

        if (Objects.equals(i, 9))
        {
            if (Objects.equals(questions9, true))
            {
                trueButton.setVisibility(View.GONE);
                trueButton.setClickable(false);
                falseButton.setVisibility(View.GONE);
                falseButton.setClickable(false);
                cheatButton.setVisibility(View.GONE);
                cheatButton.setClickable(false);
                checkButton.setVisibility(View.VISIBLE);
                checkButton.setClickable(true);
                internetButton.setVisibility(View.VISIBLE);
                internetButton.setClickable(true);
            }
        }


        textView.setText(questions[i].getTextId());
        correctText.setVisibility(View.GONE);


    }

    public void anstrue(View view)
    {

        if(Objects.equals(true,questions[i].isAnswer()))
        {
            points += 1;
            correctText.setTextColor(Color.parseColor("#899989"));
            correctText.setBackgroundColor(Color.parseColor("#E5FFE5"));
            correctText.setText("Correct");

        }
        else
        {
            incorrect +=1;
            correctText.setTextColor(Color.parseColor("#997070"));
            correctText.setBackgroundColor(Color.parseColor("#FFBBBB"));
            correctText.setText("Incorrect");
        }
        correctText.setVisibility(View.VISIBLE);

        answered += 1;
        trueButton.setVisibility(View.GONE);
        falseButton.setVisibility(View.GONE);
        cheatButton.setVisibility(View.GONE);
        checkButton.setVisibility(View.VISIBLE);
        checkButton.setClickable(true);
        internetButton.setVisibility(View.VISIBLE);
        internetButton.setClickable(true);
        questions[i].setChecked(true);

        if (Objects.equals(answered, 10))
        {
            setContentView(R.layout.activity_resume);
            textViewresume = findViewById(R.id.question_resume);
            resume = "Resume: \n Correct: " + Integer.toString(points) + "\n Incorrect: " + Integer.toString(incorrect) +  "\n Cheated (-5% for each): " + Integer.toString(cheated) + "\n Score: " + Integer.toString((points*100-cheated*50)/points_possible) + "%";
            textViewresume.setText(resume);
        }

    }

    public void ansfalse(View view)
    {
        if(Objects.equals(false,questions[i].isAnswer()))
        {
            points += 1;
            correctText.setTextColor(Color.parseColor("#899989"));
            correctText.setBackgroundColor(Color.parseColor("#E5FFE5"));
            correctText.setText("Correct");
        }
        else
        {
            incorrect +=1;
            correctText.setTextColor(Color.parseColor("#997070"));
            correctText.setBackgroundColor(Color.parseColor("#FFBBBB"));
            correctText.setText("Incorrect");
        }

        answered += 1;
        correctText.setVisibility(View.VISIBLE);
        trueButton.setVisibility(View.GONE);
        falseButton.setVisibility(View.GONE);
        cheatButton.setVisibility(View.GONE);
        checkButton.setVisibility(View.VISIBLE);
        checkButton.setClickable(true);
        questions[i].setChecked(true);
        internetButton.setVisibility(View.VISIBLE);
        internetButton.setClickable(true);

        if (Objects.equals(answered, 10))
        {
            setContentView(R.layout.activity_resume);
            textViewresume = findViewById(R.id.question_resume);
            resume = "Resume: \n Correct: " + Integer.toString(points) + "\n Incorrect: " + Integer.toString(incorrect) +  "\n Cheated (-5% for each): " + Integer.toString(cheated) + "\n Score: " + Integer.toString((points*100-cheated*50)/points_possible) + "%";
            textViewresume.setText(resume);
        }
    }

    public void cheat(View view)
    {
        Intent intent = new Intent(this, CheatActivity.class);
        intent.putExtra("key",questions[i].isAnswer());
        startActivity(intent);
        cheatButton.setVisibility(View.GONE);
        cheatButton.setClickable(false);
        cheated += 1;
    }

    public void check(View view)
    {
        Intent intent = new Intent(this, CheatActivity.class);
        intent.putExtra("key", questions[i].isAnswer());
        startActivity(intent);
    }



        public void retry(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        for (k=0; k<10; k++)
        {
            questions[k].setChecked(false);
        }
    }

    public void internet(View view)
    {
        String string;
        string= (String) textView.getText();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + string));
        startActivity(intent);
    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("i", i);
        outState.putInt("points", points);
        outState.putInt("incorrect", incorrect);
        outState.putInt("answered", answered);
        outState.putInt("cheated", cheated);
        outState.putBoolean("questions0", questions[0].isChecked());
        outState.putBoolean("questions1", questions[1].isChecked());
        outState.putBoolean("questions2", questions[2].isChecked());
        outState.putBoolean("questions3", questions[3].isChecked());
        outState.putBoolean("questions4", questions[4].isChecked());
        outState.putBoolean("questions5", questions[5].isChecked());
        outState.putBoolean("questions6", questions[6].isChecked());
        outState.putBoolean("questions7", questions[7].isChecked());
        outState.putBoolean("questions8", questions[8].isChecked());
        outState.putBoolean("questions9", questions[9].isChecked());


    }

}