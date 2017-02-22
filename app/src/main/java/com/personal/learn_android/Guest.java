package com.personal.learn_android;

/**
 * Created by Bervianto Leo P on 22/02/2017.
 */

public class Guest {
    private int id;
    private Integer image;
    private String name;
    private String birthdate;

    public Guest(int id,Integer image,String name, String birthdate) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate(){
        return birthdate;
    }

    public Integer getImage() {
        return image;
    }
}
