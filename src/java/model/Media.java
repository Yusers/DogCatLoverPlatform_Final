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
public class Media {

    private int id;
    private String url;
    private String file_name;
    private Date created_at;
    private Date updated_at;

    public Media() {
    }
    
    public Media(int id, String url, String file_name) {
        this.id = id;
        this.url = url;
        this.file_name = file_name;
    }

    public Media(String url, String file_name) {
        this.url = url;
        this.file_name = file_name;
    }
    
    public Media(String url, String file_name, Date created_at) {
        this.url = url;
        this.file_name = file_name;
        this.created_at = created_at;
    }

    public Media(int id, String url, String file_name, Date created_at) {
        this.id = id;
        this.url = url;
        this.file_name = file_name;
        this.created_at = created_at;
    }

    public Media(int id, String url, String file_name, Date created_at, Date updated_at) {
        this.id = id;
        this.url = url;
        this.file_name = file_name;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
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
        return "Media{" + "id=" + id + ", url=" + url + ", file_name=" + file_name + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }

}
