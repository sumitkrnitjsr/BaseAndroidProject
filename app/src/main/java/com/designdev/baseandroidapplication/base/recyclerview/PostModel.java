package com.designdev.baseandroidapplication.base.recyclerview;

public class PostModel {

    public String text;

    public PostModel(String t) {
            text = t;
    }
    @Override
    public String toString(){
        return text;
    }
}
