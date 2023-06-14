package com.example.lab2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneRepository {
    private PhoneDao mPhoneDao;
    private LiveData<List<Phone>> mAllPhones;

    PhoneRepository(Application application) {
        PhoneRoomDatabase db = PhoneRoomDatabase.getDatabase(application);
        mPhoneDao = db.phoneDao();
        mAllPhones = mPhoneDao.getAllPhones();
    }

    LiveData<List<Phone>> getAllPhones() {
        return mAllPhones;
    }

    void insert(Phone phone) {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.insert(phone);
        });
    }

    void deleteAll() {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.deleteAll();
        });
    }

    void update(Phone phone) {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.update(phone);
        });
    }

    void delete(int id) {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> {
            mPhoneDao.delete(id);
        });
    }
}
