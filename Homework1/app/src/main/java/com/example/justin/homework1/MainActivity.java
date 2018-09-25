package com.example.justin.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView answer;
    Spinner operator;
    TextView value1;
    TextView value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operator = (Spinner) findViewById(R.id.spinner);
        value1 = (TextView) findViewById(R.id.value);
        value2 = (TextView)findViewById(R.id.value1);
        answer = (TextView) findViewById(R.id.textView3);

        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Integer v1 = Integer.valueOf(value1.getText().toString());
                    Integer v2 = Integer.valueOf(value2.getText().toString());


                    if (operator.getSelectedItem().toString().equals("+")) {
                        Log.e("Value of Answer", String.valueOf(v1 + v2));
                        answer.setText(String.valueOf(v1 + v2));
                    } else if (operator.getSelectedItem().toString().equals("-")) {
                        Log.e("Value of Answer", String.valueOf(v1 - v2));
                        answer.setText(String.valueOf(v1 - v2));
                    } else if (operator.getSelectedItem().toString().equals("*")) {
                        Log.e("Value of Answer", String.valueOf(v1 * v2));
                        answer.setText(String.valueOf(v1 * v2));
                    } else if (operator.getSelectedItem().toString().equals("/")) {
                        Log.e("Value of Answer", String.valueOf(v1 / v2));
                        answer.setText(String.valueOf(v1 / v2));
                    }
                } catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Invalid Inputs!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
