package com.myfinances.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TransactionDataRepository {

    private TransactionDataDao mDao;
    private LiveData<List<TransactionData>> listLiveData;


    TransactionDataRepository(Application application) {
        RoomDatabase db = RoomDatabase.getDatabase(application);
        mDao = db.transactionDataDao();
        listLiveData = mDao.getCartList();
    }

    LiveData<List<TransactionData>> getListLiveData() {
        return listLiveData;
    }


    void insert(final TransactionData transactionData) {
        RoomDatabase.databaseWriteExecutor.execute(() ->{
            mDao.insert(transactionData);
        });
    }

    void deleteAll() {
        RoomDatabase.databaseWriteExecutor.execute(() ->{
            mDao.deleteAll();
        });
    }


    void deleteBysID(final String sID) {
        RoomDatabase.databaseWriteExecutor.execute(() ->{
            mDao.deleteBysID(sID);
        });
    }


}
