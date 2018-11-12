package com.example.justin.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TextView answer;
    private Spinner operator;
    private TextView value1;
    private TextView value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operator =  findViewById(R.id.spinner);
        value1 =  findViewById(R.id.value);
        value2 = findViewById(R.id.value1);
        answer = findViewById(R.id.textView3);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            try {
                Integer v1 = Integer.valueOf(value1.getText().toString());
                Integer v2 = Integer.valueOf(value2.getText().toString());


                switch (operator.getSelectedItem().toString()) {
                    case "+":
                        Log.e("Value of Answer", String.valueOf(v1 + v2));
                        answer.setText(String.valueOf(v1 + v2));
                        break;
                    case "-":
                        Log.e("Value of Answer", String.valueOf(v1 - v2));
                        answer.setText(String.valueOf(v1 - v2));
                        break;
                    case "*":
                        Log.e("Value of Answer", String.valueOf(v1 * v2));
                        answer.setText(String.valueOf(v1 * v2));
                        break;
                    case "/":
                        Log.e("Value of Answer", String.valueOf(v1 / v2));
                        answer.setText(String.valueOf(v1 / v2));
                        break;
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Invalid Inputs!",
                        Toast.LENGTH_LONG).show();
            }

        });


    }
}
