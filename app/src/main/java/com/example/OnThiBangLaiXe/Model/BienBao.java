package com.example.OnThiBangLaiXe.Model;

import java.io.Serializable;

public class BienBao implements Serializable {
    private int img;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public BienBao(int img, String iD, String title, String content) {
        this.img = img;
        this.iD = iD;
        this.title = title;
        Content = content;
    }

    private String iD;
    private String title;
    private String Content;

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public BienBao() {
    }

    public BienBao(String iD, String title, String content) {
        this.iD = iD;
        this.title = title;
        Content = content;
    }
}
