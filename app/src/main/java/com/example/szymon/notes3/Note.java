package com.example.szymon.notes3;

import java.io.Serializable;

public class Note implements Serializable {
    private String text;
    private String title;
    public Note(String title,String text){
        this.text=text;
        this.title=title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
