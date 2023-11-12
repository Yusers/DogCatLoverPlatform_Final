/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author overw
 */
public class Post {
    private int id;
    private String title;
    private int cate_id;
    private String author_id;
    private String image;
    private String content;
    private String status;
    private String rejected_reason;
    private Date created_at;
    private Date updated_at;

    public Post() {
    }

    public Post(String title, int cate_id, String author_id, String content, String status, String image) {
        this.title = title;
        this.cate_id = cate_id;
        this.author_id = author_id;
        this.content = content;
        this.status = status;
        this.image = image;
    }
    
    public Post(String title, int cate_id, String author_id, String content) {
        this.title = title;
        this.cate_id = cate_id;
        this.author_id = author_id;
        this.content = content;
    }

    public Post(int id, String title, int cate_id, String author_id, String content, String status, String rejected_reason, Date created_at, Date updated_at, String image) {
        this.id = id;
        this.title = title;
        this.cate_id = cate_id;
        this.author_id = author_id;
        this.content = content;
        this.status = status;
        this.rejected_reason = rejected_reason;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.image = image;
    }
    
    public Post(int id, String title, int cate_id, String author_id, String content, String status, String rejected_reason, Date created_at) {
        this.id = id;
        this.title = title;
        this.cate_id = cate_id;
        this.author_id = author_id;
        this.content = content;
        this.status = status;
        this.rejected_reason = rejected_reason;
        this.created_at = created_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejected_reason() {
        return rejected_reason;
    }

    public void setRejected_reason(String rejected_reason) {
        this.rejected_reason = rejected_reason;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title=" + title + ", cate_id=" + cate_id + ", author_id=" + author_id + ", content=" + content + ", status=" + status + ", rejected_reason=" + rejected_reason + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }
    
}
