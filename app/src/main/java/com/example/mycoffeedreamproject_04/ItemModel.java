package com.example.mycoffeedreamproject_04;

import java.io.Serializable;

public class ItemModel implements Serializable {

    private int image;
    private String name;
    private int description;

    public ItemModel(int image, String name, int description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }


}
