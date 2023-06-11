package com.myfinances.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TransactionDataTable")
public class TransactionData {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public String sID;

    @ColumnInfo(name = "account_type")
    public String sAccountType;

    @ColumnInfo(name = "account_number")
    public String sAccountNumber;

    @ColumnInfo(name = "initial_balance")
    public String sInitialBalance;

    @ColumnInfo(name = "current_balance")
    public String sCurrentBalance;

    @ColumnInfo(name = "interest_rate")
    public String sInterestRate;

    @ColumnInfo(name = "payment_amount")
    public String sPaymentAmount;


    public TransactionData(@NonNull String sID ,String sAccountType,String sAccountNumber, String sInitialBalance,
            String sCurrentBalance, String sInterestRate, String sPaymentAmount) {
        this.sID = sID;
        this.sAccountType = sAccountType;
        this.sAccountNumber = sAccountNumber;
        this.sInitialBalance = sInitialBalance;
        this.sCurrentBalance = sCurrentBalance;
        this.sInterestRate = sInterestRate;
        this.sPaymentAmount = sPaymentAmount;
    }


    @NonNull
    public String getsID() {
        return sID;
    }

    public String getsAccountType() {
        return sAccountType;
    }

    public String getsAccountNumber() {
        return sAccountNumber;
    }

    public String getsInitialBalance() {
        return sInitialBalance;
    }

    public String getsCurrentBalance() {
        return sCurrentBalance;
    }

    public String getsInterestRate() {
        return sInterestRate;
    }

    public String getsPaymentAmount() {
        return sPaymentAmount;
    }
}
