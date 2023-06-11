package com.contractor.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText etLaborCost,etMaterialCost;
    Button btnCalculate;
    TextView tvSubTotal,tvTax,tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLaborCost = findViewById(R.id.etLaborCost);
        etMaterialCost = findViewById(R.id.etMaterialCost);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvSubTotal = findViewById(R.id.tvSubTotal);
        tvTax = findViewById(R.id.tvTax);
        tvTotal = findViewById(R.id.tvTotal);

       btnCalculate.setOnClickListener(view -> {

            if(etLaborCost.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "Enter Labor Cost", Toast.LENGTH_SHORT).show();
            }else if (etMaterialCost.getText().toString().isEmpty()){
                Toast.makeText(MainActivity.this, "Enter Material Cost", Toast.LENGTH_SHORT).show();
            }else {
                Double laborCost = Utils.stringToDecimal(etLaborCost.getText().toString()) ;
                Double materialCost = Utils.stringToDecimal(etMaterialCost.getText().toString());

                // Add Labor Cost and Material Cost
                Double subTotal = laborCost + materialCost;

                // Calculate Tax Charges 5%
                Double taxAmount = (subTotal * 5)/ 100;

                // Total Amount
                Double total = subTotal + taxAmount;


                tvSubTotal.setText(Utils.showNumber(subTotal));
                tvTax.setText(Utils.showNumber(taxAmount));
                tvTotal.setText(Utils.showNumber(total));

            }
        });


    }
}