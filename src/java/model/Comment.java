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
public class Comment {
    private int id;
    private String author_id;
    private int post_id;
    private String content;
    private int parent_id;
    private Date created_at;
    private Date updated_at;

    public Comment() {
    }

    public Comment(String author_id, int post_id, String content, int parent_id) {
        this.author_id = author_id;
        this.post_id = post_id;
        this.content = content;
        this.parent_id = parent_id;
    }
    
    public Comment(int id, String author_id, int post_id, String content, int parent_id, Date created_at, Date updated_at) {
        this.id = id;
        this.author_id = author_id;
        this.post_id = post_id;
        this.content = content;
        this.parent_id = parent_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
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

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
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
        return "Comment{" + "id=" + id + ", author_id=" + author_id + ", post_id=" + post_id + ", content=" + content + ", parent_id=" + parent_id + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }
    
}
