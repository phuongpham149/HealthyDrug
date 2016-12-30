package com.example.phuong.healthydrug.models;

import com.orm.SugarRecord;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by phuong on 28/12/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Data
public class Provices extends SugarRecord {
    private String name;
    private String image;

    public Provices() {
    }
}
