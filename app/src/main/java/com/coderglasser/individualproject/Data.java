package com.coderglasser.individualproject;

import java.util.Date;

public class Data {
    private int imgId;
    private String content;
    private String mount;
    private Date date;

    public Data(){}

    public Data(int imgId,String content,String mount){
        this.imgId = imgId;
        this.content = content;
        this.mount = mount;
    }

    public int getImgId(){
        return imgId;
    }

    public String getContent(){
        return content;
    }

    public String getMount(){
        return mount;
    }

    public Date getDate(){
        return date;
    }

    public void setImgId(int imgId){
        this.imgId = imgId;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setMount(String mount){
        this.mount = mount;
    }

    public void setDate(Date date){
        this.date = date;
    }

}
