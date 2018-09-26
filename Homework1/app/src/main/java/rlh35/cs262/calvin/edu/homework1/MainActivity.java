package rlh35.cs262.calvin.edu.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText firstValue;
    private EditText secondValue;
    private TextView results;
    private Spinner operator;
    private int first;
    private int second;
    private double answer;
    private String resultText;
    private String op;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstValue = findViewById(R.id.firstValueInput);
        secondValue = findViewById(R.id.secondValueInput);
        results = findViewById(R.id.resultTextBox);
        operator = findViewById(R.id.spinner);
    }

    public void calculate(View view) {
        //String firstInput = firstValue.getText().toString();
        first = Integer.parseInt(firstValue.getText().toString());
        second = Integer.parseInt(secondValue.getText().toString());
        op = operator.getSelectedItem().toString();

        switch (op) {
            case "+":
                answer = first + second;
                break;
            case "-":
                answer = first - second;
                break;
            case "*":
                answer = first * second;
                break;
            case "/":
                answer = first / second;
                break;
        }

        resultText = Double.toString(answer);
        results.setText(resultText);
    }
}
