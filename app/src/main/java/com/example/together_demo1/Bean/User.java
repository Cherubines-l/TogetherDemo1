package com.example.together_demo1.Bean;

import android.content.Intent;

import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport {
    private String name;
    private String carid;
    private Integer banlance;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public Integer getBanlance() {
        return banlance;
    }

    public void setBanlance(Integer banlance) {
        this.banlance = banlance;
    }

    public User(String name, String carid, Integer banlance) {
        this.name = name;
        this.carid = carid;
        this.banlance = banlance;
    }
}
