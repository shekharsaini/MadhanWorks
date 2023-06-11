package com.myfinances;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.myfinances.database.TransactionData;
import com.myfinances.database.TransactionDataViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;

    RadioButton rbCDs,rbLoans,rbChecking;

    EditText etAccountNumber,etInitialBalance,etCurrentBalance,etInterestRate,etPaymentAmount;

    LinearLayout llInitialBalance,llCurrentBalance,llInterestRate,llPaymentAmount;

    Button btnClear, btnSave;

    String sAccountType= "";

    private TransactionDataViewModel transactionDataViewModel;

    RecyclerView rvTransactions;
    TransactionAdapter transactionAdapter;
    private TransactionAdapter.OnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transactionDataViewModel = new ViewModelProvider(this).get(TransactionDataViewModel.class);

        initID();
        listners();

        dataBind();
    }

    private void dataBind() {

        listener = new TransactionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TransactionData transactionData, int pos) {
                transactionDataViewModel.deleteBySkuID(transactionData.sID);
            }
        };
        transactionAdapter = new TransactionAdapter(MainActivity.this,listener);
        rvTransactions.setAdapter(transactionAdapter);
        transactionDataViewModel.getListLiveData().observe(MainActivity.this, transactionData -> {
            if(transactionData.size() > 0){
                transactionAdapter.setData(transactionData);
                rvTransactions.setVisibility(View.VISIBLE);
            }else {
                rvTransactions.setVisibility(View.GONE);
            }
        });
    }

    private void initID() {
        radioGroup = findViewById(R.id.radioGroup);
        rbCDs = findViewById(R.id.rbCDs);
        rbLoans = findViewById(R.id.rbLoans);
        rbChecking = findViewById(R.id.rbChecking);

        etAccountNumber = findViewById(R.id.etAccountNumber);
        etInitialBalance = findViewById(R.id.etInitialBalance);
        etCurrentBalance = findViewById(R.id.etCurrentBalance);
        etInterestRate = findViewById(R.id.etInterestRate);
        etPaymentAmount = findViewById(R.id.etPaymentAmount);

        llInitialBalance = findViewById(R.id.llInitialBalance);
        llCurrentBalance = findViewById(R.id.llCurrentBalance);
        llInterestRate = findViewById(R.id.llInterestRate);
        llPaymentAmount = findViewById(R.id.llPaymentAmount);
        btnClear = findViewById(R.id.btnClear);
        btnSave = findViewById(R.id.btnSave);
        rvTransactions = findViewById(R.id.rvTransactions);

    }


    private void listners() {

        sAccountType = "CDS";
        llPaymentAmount.setVisibility(View.GONE);
        llInterestRate.setVisibility(View.VISIBLE);
        llInitialBalance.setVisibility(View.VISIBLE);


        rbCDs.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                sAccountType = "CDs";
                llPaymentAmount.setVisibility(View.GONE);
                llInterestRate.setVisibility(View.VISIBLE);
                llInitialBalance.setVisibility(View.VISIBLE);
            }
        });
        rbLoans.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                sAccountType = "Loans";
                llPaymentAmount.setVisibility(View.VISIBLE);
                llInitialBalance.setVisibility(View.VISIBLE);
                llInterestRate.setVisibility(View.GONE);
            }
        });

        rbChecking.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                sAccountType = "Checking";
                llInitialBalance.setVisibility(View.GONE);
                llPaymentAmount.setVisibility(View.GONE);
                llInterestRate.setVisibility(View.GONE);
            }
        });

        btnClear.setOnClickListener(v->{
            clearData();
        });

        btnSave.setOnClickListener(v->{
                if(sAccountType.equalsIgnoreCase("CDs")){

                    if(etAccountNumber.getText().toString().isEmpty()){
                        Toast.makeText(this, "Enter Account Number", Toast.LENGTH_SHORT).show();
                    }else if(etInitialBalance.getText().toString().isEmpty()){
                        Toast.makeText(this, "Enter Initial Balance", Toast.LENGTH_SHORT).show();
                    }else if(etCurrentBalance.getText().toString().isEmpty()){
                        Toast.makeText(this, "Enter Current Balance", Toast.LENGTH_SHORT).show();
                    }else if(etInterestRate.getText().toString().isEmpty()){
                        Toast.makeText(this, "Enter Interest Rate", Toast.LENGTH_SHORT).show();
                    }else {
                        TransactionData data = new TransactionData(Utils.getCurrentDateTime(),
                                sAccountType,etAccountNumber.getText().toString(),etInitialBalance.getText().toString(),etCurrentBalance.getText().toString(),etInterestRate.getText().toString(),
                                "");
                        transactionDataViewModel.insert(data);
                        clearData();
                        Toast.makeText(this, "Data has been saved successfully", Toast.LENGTH_SHORT).show();
                    }
                }

                if(sAccountType.equalsIgnoreCase("Loans")){
                    if(etAccountNumber.getText().toString().isEmpty()){
                        Toast.makeText(this, "Enter Account Number", Toast.LENGTH_SHORT).show();
                    }else if(etInitialBalance.getText().toString().isEmpty()){
                        Toast.makeText(this, "Enter Initial Balance", Toast.LENGTH_SHORT).show();
                    }else if(etCurrentBalance.getText().toString().isEmpty()){
                        Toast.makeText(this, "Enter Current Balance", Toast.LENGTH_SHORT).show();
                    }else if(etPaymentAmount.getText().toString().isEmpty()){
                        Toast.makeText(this, "Enter Payment Amount", Toast.LENGTH_SHORT).show();
                    }else {
                        TransactionData data = new TransactionData(Utils.getCurrentDateTime(),
                                sAccountType,etAccountNumber.getText().toString(),etInitialBalance.getText().toString(),etCurrentBalance.getText().toString(),"",
                                etPaymentAmount.getText().toString());
                        transactionDataViewModel.insert(data);
                        clearData();
                        Toast.makeText(this, "Data has been saved successfully", Toast.LENGTH_SHORT).show();
                    }

                }

                if(sAccountType.equalsIgnoreCase("Checking")){
                    if(etAccountNumber.getText().toString().isEmpty()){
                        Toast.makeText(this, "Enter Account Number", Toast.LENGTH_SHORT).show();
                    }else if(etCurrentBalance.getText().toString().isEmpty()){
                        Toast.makeText(this, "Enter Current Balance", Toast.LENGTH_SHORT).show();
                    }else {
                        TransactionData data = new TransactionData(Utils.getCurrentDateTime(),
                                sAccountType,etAccountNumber.getText().toString(),"",etCurrentBalance.getText().toString(),"","");
                        transactionDataViewModel.insert(data);
                        clearData();
                        Toast.makeText(this, "Data has been saved successfully", Toast.LENGTH_SHORT).show();
                    }
                }


        });
    }

    private void clearData() {
        etAccountNumber.setText("");
        etInitialBalance.setText("");
        etCurrentBalance.setText("");
        etInterestRate.setText("");
        etPaymentAmount.setText("");
    }
}