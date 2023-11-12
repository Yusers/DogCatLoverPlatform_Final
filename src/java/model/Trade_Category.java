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
public class Trade_Category {
    private int id;
    private String name;
    private Date created_at;
    private Date updated_at;

    public Trade_Category() {
    }

    public Trade_Category(int id, String name, Date created_at) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
    }
    
    public Trade_Category(int id, String name, Date created_at, Date updated_at) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Trade_Category{" + "id=" + id + ", name=" + name + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }
    
}
