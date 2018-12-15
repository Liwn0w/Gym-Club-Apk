package edu.bjtu.example.gym_club;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class NewsItem extends BmobObject {

    String content;
    BmobFile picture;
    String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setPicture(BmobFile picture) {
        this.picture = picture;
    }

    public BmobFile getPicture() {
        return picture;
    }

    public String getIconUrl() {
        return picture.getUrl();
    }
}
