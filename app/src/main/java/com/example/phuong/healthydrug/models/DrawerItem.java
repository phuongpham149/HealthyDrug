package com.example.phuong.healthydrug.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by phuong on 28/12/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Data
public class DrawerItem {
    private String name;
    private int idImage;
}
