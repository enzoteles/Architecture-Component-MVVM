package com.example.enzoteles.room;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by enzoteles on 22/01/18.
 */
@Entity
public class University extends BaseObservable{

    @PrimaryKey(autoGenerate = true)
    private int slNo;
    private String name;

    @Embedded(prefix = "clg")
    College college;

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}
