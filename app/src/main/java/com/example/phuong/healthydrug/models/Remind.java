package com.example.phuong.healthydrug.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by phuong on 30/12/2016.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Data
public class Remind {
    private boolean isStatus;
    private String hour;
    private String mins;

    @Override
    public String toString() {
        return "Remind{" +
                "isStatus=" + isStatus +
                ", hour='" + hour + '\'' +
                ", mins='" + mins + '\'' +
                '}';
    }
}
