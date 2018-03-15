package com.macormap.newtest.data;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlo on 11/03/2018.
 */

public class Obj_post {

    private Integer author_code;
    private Integer post_idImg;
    private String  author_name;
    private Integer author_idImg;
    private String mainComment;
    private List<SubComment> listComments;

    private Integer num_watch;
    private Integer num_subcomment;
    private Integer num_love;

    public Obj_post() {
        listComments = new ArrayList<SubComment>();
    }


    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public Integer getAuthor_code() {
        return author_code;
    }

    public void setAuthor_code(Activity activity, Integer author_code) {
        this.author_code = author_code;
        setAuthor_name(DecodeAuthor.nameAuthor(author_code));
        setAuthor_idImg(DecodeAuthor.getIdResourceImgAuthor(activity ,author_code));
    }

    public String getMainComment() {
        return mainComment;
    }

    public void setMainComment(String mainComment) {
        this.mainComment = mainComment;
    }

    public Integer getNum_watch() {
        return num_watch;
    }

    public void setNum_watch(Integer num_watch) {
        this.num_watch = num_watch;
    }

    public Integer getNum_subcomment() {
        return num_subcomment;
    }

    public void setNum_subcomment(Integer num_subcomment) {
        this.num_subcomment = num_subcomment;
    }

    public Integer getNum_love() {
        return num_love;
    }

    public void setNum_love(Integer num_love) {
        this.num_love = num_love;
    }

    public Integer getAuthor_idImg() {
        return author_idImg;
    }

    public void setAuthor_idImg(Integer author_idImg) {
        this.author_idImg = author_idImg;
    }

    public Integer getPost_idImg() {
        return post_idImg;
    }

    public void setPost_idImg(Integer post_idImg) {
        this.post_idImg = post_idImg;
    }

    public List<SubComment> getListComments() {
        return listComments;
    }

    public void addItemListComments(SubComment  comment) {
        listComments.add(comment);
    }

    public void setListComments(List<SubComment> listComments) {
        this.listComments = listComments;
    }
}
