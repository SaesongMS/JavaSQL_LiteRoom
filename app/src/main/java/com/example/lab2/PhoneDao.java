package com.example.lab2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Phone phone);

    @Query("DELETE FROM phone_table")
    void deleteAll();

    @Query("SELECT * FROM phone_table ORDER BY id ASC")
    LiveData<List<Phone>> getAllPhones();

    @Update
    void update(Phone phone);

    @Query("DELETE FROM phone_table WHERE id = :id")
    void delete(int id);
}
