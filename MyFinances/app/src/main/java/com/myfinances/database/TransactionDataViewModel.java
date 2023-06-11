package com.myfinances.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TransactionDataViewModel extends AndroidViewModel {
    private TransactionDataRepository mRepository;
    private LiveData<List<TransactionData>> listLiveData;


    public TransactionDataViewModel(Application application) {
        super(application);
        mRepository = new TransactionDataRepository(application);
        listLiveData = mRepository.getListLiveData();
    }

    public LiveData<List<TransactionData>> getListLiveData() {
        return listLiveData;
    }


    public void insert(TransactionData cart) {
        mRepository.insert(cart);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteBySkuID(final String sID) {
        mRepository.deleteBysID(sID);
    }



}
