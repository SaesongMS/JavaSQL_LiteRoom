package com.example.lab2;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneViewModel extends AndroidViewModel {

    private PhoneRepository mRepository;

    private final LiveData<List<Phone>> mAllPhones;

    public PhoneViewModel (Application application) {
        super(application);
        mRepository = new PhoneRepository(application);
        mAllPhones = mRepository.getAllPhones();
    }

    LiveData<List<Phone>> getAllPhones() { return mAllPhones; }

    public void insert(Phone phone) { mRepository.insert(phone); }

    public void deleteAll() { mRepository.deleteAll(); }

    public void update(Phone phone) { mRepository.update(phone); }

    public void delete(int id) { mRepository.delete(id); }
}