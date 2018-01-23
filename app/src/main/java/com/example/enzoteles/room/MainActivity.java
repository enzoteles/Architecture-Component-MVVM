package com.example.enzoteles.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    SampleDatabase sampleDatabase;
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    UniversityViewModel viewModel;
    public static Application application;

    public static Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getBaseContext();

        setContentView(R.layout.main);
        //init viewmodel
        viewModel = ViewModelProviders.of(this).get(UniversityViewModel.class);


        University university = new University();
        university.setName("sim");
        College c = new College();
        c.setId(1);
        c.setName("simcollege");
        university.setCollege(c);

        //insert university
        viewModel.insertUniversity(university);


        //list university
        viewModel.getmAllUniversity().observe(this, new Observer<List<University>>() {
            @Override
            public void onChanged(@Nullable List<University> universities) {
                for (University u: universities){
                    Log.i("LISTA>>>", "Nome "+ u.getName());
                }
            }
        });



    }

}
