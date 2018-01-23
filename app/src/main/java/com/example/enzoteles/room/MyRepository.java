package com.example.enzoteles.room;

import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by enzoteles on 22/01/18.
 */

public class MyRepository {

    private SampleDatabase sampleDatabase;
    private LiveData<List<University>> listUniversity;

    public MyRepository() {
        sampleDatabase = SampleDatabase.getDatabase(MainActivity.context);
        listUniversity = sampleDatabase.daoAccess().fetchAllData();
    }

    public LiveData<List<University>> getListUniversity() {
        return listUniversity;
    }

    public void insertUniversity(University university){
        sampleDatabase.daoAccess().insertOnlySingleRecord(university);
    }
}
