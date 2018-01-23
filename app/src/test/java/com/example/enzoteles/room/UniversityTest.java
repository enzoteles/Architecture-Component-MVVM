package com.example.enzoteles.room;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.Nullable;
import android.test.RenamingDelegatingContext;
import android.test.mock.MockContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(RobolectricTestRunner.class)
public class UniversityTest {

    SampleDatabase db;
    DaoAccess dao;
    Context context;
    MainActivity activity;

    @Before
    public void setUp(){

        activity = Robolectric.setupActivity(MainActivity.class);
        db = Room.databaseBuilder(activity.getApplicationContext(), SampleDatabase.class, "borrow_db")
                .build();
        dao = db.daoAccess();

    }

    @Test
    public void insertUniversaty(){

        final University u = new University();
        u.setName("TestUnivesity");
        College college = new College();
        college.setId(1);
        college.setName("CollegeTest");
        u.setCollege(college);
        Single.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                dao.insertOnlySingleRecord(u);
                return "sucess";
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe();

    }

    @Test
    public void getListUniversityAll(){
        dao.fetchAllData().observe(activity, new Observer<List<University>>() {
            @Override
            public void onChanged(@Nullable List<University> universities) {
                assertNotNull(universities);
            }
        });
    }
}