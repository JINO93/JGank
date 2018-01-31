package com.jino.jgank.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by JINO on 2018/1/30.
 */
@Entity
public class ArticleItem implements Serializable{

    static final long serialVersionUID = 0l;
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_LIKE = 0x1;
    public static final int TYPE_HISTORY = 0x2;

//    @Id(autoincrement = true)
//    private long id;
    private String publishTime;
    private int type;
    @Unique
    @Id
    private String url;
    private String img;
    private String author;
    private String desc;
    private String category;

    @Generated(hash = 1755646041)
    public ArticleItem(String publishTime, int type, String url, String img,
            String author, String desc, String category) {
        this.publishTime = publishTime;
        this.type = type;
        this.url = url;
        this.img = img;
        this.author = author;
        this.desc = desc;
        this.category = category;
    }

    @Generated(hash = 1068397934)
    public ArticleItem() {
    }


    public String getPublishTime() {
        return this.publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ArticleItem{" +
                ", publishTime='" + publishTime + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", img='" + img + '\'' +
                ", author='" + author + '\'' +
                ", desc='" + desc + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
