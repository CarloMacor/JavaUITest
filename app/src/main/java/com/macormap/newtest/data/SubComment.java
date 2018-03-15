package com.macormap.newtest.data;

import android.app.Activity;

/**
 * Created by carlo on 11/03/2018.
 */

public class SubComment {

    private Integer author_code;
    private String  author_name;
    private Integer author_idImg;
    private String  subCommenttxt;
    private String  comment_date;


    public SubComment() {

    }

    public void setAuthor_code(Activity activity, Integer author_code) {
        this.author_code = author_code;
        setAuthor_name(DecodeAuthor.nameAuthor(author_code));
        setAuthor_idImg(DecodeAuthor.getIdResourceImgAuthor(activity ,author_code));
    }

    public Integer getAuthor_code() {
        return author_code;
    }

    public void setAuthor_code(Integer author_code) {
        this.author_code = author_code;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public Integer getAuthor_idImg() {
        return author_idImg;
    }

    public void setAuthor_idImg(Integer author_idImg) {
        this.author_idImg = author_idImg;
    }

    public String getSubCommenttxt() {
        return subCommenttxt;
    }

    public void setSubCommenttxt(String subCommenttxt) {
        this.subCommenttxt = subCommenttxt;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }
}
