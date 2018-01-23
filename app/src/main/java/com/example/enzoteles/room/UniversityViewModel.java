package com.example.enzoteles.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by enzoteles on 22/01/18.
 */

public class UniversityViewModel extends ViewModel {

    private MyRepository myRepository;
    private LiveData<List<University>> mAllUniversity;

    public UniversityViewModel() {
        myRepository = new MyRepository();
        mAllUniversity = myRepository.getListUniversity();
    }

    //método para pegar a lista do banco
    public LiveData<List<University>> getmAllUniversity() {
        return mAllUniversity;
    }

    /**
     * método para inserir uma universidade no banco
     * */
    public void insertUniversity(final University university){
        Single.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {

                myRepository.insertUniversity(university);
                return "sucess";
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe();

    }

}
