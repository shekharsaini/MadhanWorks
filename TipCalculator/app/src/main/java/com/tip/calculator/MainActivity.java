package com.tip.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText etBillAmount;
    Button btnCalculate15,btnCalculate18,btnCalculate20;
    TextView tvTotalTipBillAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBillAmount = findViewById(R.id.etBillAmount);
        btnCalculate15 = findViewById(R.id.btnCalculate15);
        btnCalculate18 = findViewById(R.id.btnCalculate18);
        btnCalculate20 = findViewById(R.id.btnCalculate20);
        tvTotalTipBillAmount = findViewById(R.id.tvTotalTipBillAmount);

        btnCalculate15.setOnClickListener(view -> {
            funCalculation(15);
        });

        btnCalculate18.setOnClickListener(view -> {
            funCalculation(18);
        });

        btnCalculate20.setOnClickListener(view -> {
            funCalculation(20);
        });


    }

    private void funCalculation(int percentage) {
        if(etBillAmount.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "Enter Bill Amount", Toast.LENGTH_SHORT).show();
        }else {
            Double billAmount = Utils.stringToDecimal(etBillAmount.getText().toString()) ;

            // Calculate Tip Amount
            Double tipAmount = funCalculateTipAmount(billAmount,percentage);

            // Calculate Total Bill Amount
            Double totalBillAmount = funCalculateTotalBillAmount(billAmount,percentage);

            tvTotalTipBillAmount.setText("Tip $"+ Utils.showNumber(tipAmount)  + " , "+ "Total Bill $" +Utils.showNumber(totalBillAmount));
        }
    }

    private Double funCalculateTipAmount(Double billAmount, int percentage) {
        // Calculate Tax Charges percentage
        Double taxAmount = (billAmount * percentage)/ 100;

        return taxAmount;
    }
    private Double funCalculateTotalBillAmount(Double billAmount, int percentage) {
        // Calculate Tax Charges percentage
        Double taxAmount = (billAmount * percentage)/ 100;

        // Total Bill Amount
        Double totalBillAmount = billAmount + taxAmount;

        return totalBillAmount;
    }
}