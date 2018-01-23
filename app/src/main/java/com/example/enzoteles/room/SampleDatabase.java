package com.example.enzoteles.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by enzoteles on 22/01/18.
 */
@Database(entities = {University.class}, version = 1)
public abstract class SampleDatabase extends RoomDatabase {

    private static SampleDatabase INSTANCE;

    public static SampleDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), SampleDatabase.class, "borrow_db")
                            .build();
        }
        return INSTANCE;
    }

    public abstract DaoAccess daoAccess();
}

