package pl.edu.uwr.pum.factorialapp;

import androidx.appcompat.app.AppCompatActivity;
import java.math.BigInteger;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static BigInteger factorial(int number){
        BigInteger factorial = BigInteger.ONE;
        for (int i = number; i >0; i--){
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial;
    }

    public void calc()
    {

    }
}