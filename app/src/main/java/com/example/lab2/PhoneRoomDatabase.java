package com.example.lab2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Phone.class}, version = 1, exportSchema = false)
public abstract class PhoneRoomDatabase extends RoomDatabase {

    public abstract PhoneDao phoneDao();

    private static volatile PhoneRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PhoneRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PhoneRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PhoneRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                PhoneDao dao = INSTANCE.phoneDao();
                dao.deleteAll();

                Phone phone1 = new Phone("Samsung", "Galaxy S21", "Android 11", "https://www.samsung.com/global/galaxy/galaxy-s21-5g/");
                Phone phone2 = new Phone("OnePlus", "9 Pro", "Android 11", "https://www.oneplus.com/9-pro");
                Phone phone3 = new Phone("Apple", "iPhone 12", "iOS 14", "https://www.apple.com/iphone-12/");

                dao.insert(phone1);
                dao.insert(phone2);
                dao.insert(phone3);
            });
        }
    };
}