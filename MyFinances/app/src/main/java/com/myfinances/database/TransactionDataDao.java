package com.myfinances.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TransactionData transactionData);


    @Query("SELECT * from TransactionDataTable ORDER BY id ASC")
    LiveData<List<TransactionData>> getCartList();

    @Query("DELETE FROM TransactionDataTable")
    void deleteAll();



    @Query("DELETE FROM TransactionDataTable WHERE id = :sID")
    void deleteBysID(String sID);

}

