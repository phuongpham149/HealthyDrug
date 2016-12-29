package com.example.phuong.healthydrug.models;

import com.orm.SugarRecord;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by phuong on 28/12/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Data
public class Hospital extends SugarRecord {
    private String name;
    private String address;
    private String image;
    private String website;
    private Provices provices;
    private String phone;

    public Hospital() {
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                ", website='" + website + '\'' +
                ", provices=" + provices +
                ", phone='" + phone + '\'' +
                '}';
    }


}
