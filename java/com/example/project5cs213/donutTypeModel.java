package com.example.project5cs213;

import android.widget.ImageView;

public class donutTypeModel {
    String type;
    int image;

    public donutTypeModel(String type, int image){
        this.type = type;
        this.image=  image;
    }
    public String getType(){
        return type;
    }

    public int getImage(){
        return image;
    }
}
