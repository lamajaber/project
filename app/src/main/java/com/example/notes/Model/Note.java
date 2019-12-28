package com.example.notes.Model;

public class Note {

    private String id;
    private int img;
    private String date, title, dec;




    public Note(String id, int mark, String date, String title, String dec) {
        this.id = id;
        this.img = mark;
        this.date = date;
        this.title = title;
        this.dec = dec;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int mark) {
        this.img = mark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }
}
