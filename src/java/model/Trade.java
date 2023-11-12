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
public class Trade {
    private int id;
    private String author_id;
    private String title;
    private String content;
    private String status;
    private String rejected_reason;
    private int cate_id;
    private String image;
    private Date created_at;
    private Date updated_at;

    public Trade() {
    }

    public Trade(String author_id, String title, String content, int cate_id) {
        this.author_id = author_id;
        this.title = title;
        this.content = content;
        this.cate_id = cate_id;
    }
    
    public Trade(int id, String author_id, String title, String content, String status, int cate_id) {
        this.id = id;
        this.author_id = author_id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.cate_id = cate_id;
    }
    
    public Trade(int id, String author_id, String title, String content, String status, int cate_id, String rejected_reason) {
        this.id = id;
        this.author_id = author_id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.cate_id = cate_id;
        this.rejected_reason = rejected_reason;
    }
    
    public Trade(int id, String author_id, String title, String content, String status, int cate_id, String image, Date created_at, Date updated_at) {
        this.id = id;
        this.author_id = author_id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.cate_id = cate_id;
        this.image = image;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getRejected_reason() {
        return rejected_reason;
    }

    public void setRejected_reason(String rejected_reason) {
        this.rejected_reason = rejected_reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        return "Trade{" + "id=" + id + ", author_id=" + author_id + ", title=" + title + ", content=" + content + ", status=" + status + ", cate_id=" + cate_id + ", image=" + image + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }
    
}
