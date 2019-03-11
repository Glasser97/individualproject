package com.coderglasser.individualproject;

import java.util.Date;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

//date.getTime()返回的值当作是id
@Entity
public class Data {
    @Id
    private long id;
    private int imgId;
    private String content;
    private String mount;
    private Date date;

    public Data(){}

    @Generated(hash = 1619741195)
    public Data(long id, int imgId, String content, String mount, Date date) {
        this.id = id;
        this.imgId = imgId;
        this.content = content;
        this.mount = mount;
        this.date = date;
    }

    public long getId(){ return id; }

    public int getImgId(){ return imgId; }

    public String getContent(){
        return content;
    }

    public String getMount(){
        return mount;
    }

    public Date getDate(){
        return date;
    }

    public void setId(long id){ this.id = id;}

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
