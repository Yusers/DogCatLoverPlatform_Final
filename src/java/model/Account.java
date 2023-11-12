/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Kuuga
 */
public class Account {
    private String user_id;
    private String fullname;
    private String email;
    private String password;
    private String phone_number;
    private String description;
    private String role;
    private String status;
    private Date created_at;
    private Date updated_at;

    public Account() {
    }

    public Account(String user_id, String fullname, String email, String phone_number) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.email = email;
        this.phone_number = phone_number;
    }
    
    public Account(String user_id, String fullname, String email, String password, String phone_number) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }
    
    public Account(String user_id, String fullname, String email, String password, String phone_number, String description, String role, String status, Date created_at, Date updated_at) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.description = description;
        this.role = role;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Account(String user_id, String fullname, String email, String phone_number, String description, String role, String status, Date created_at) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.email = email;
        this.phone_number = phone_number;
        this.description = description;
        this.role = role;
        this.status = status;
        this.created_at = created_at;
    }
      
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Account{" + "user_id=" + user_id + ", fullname=" + fullname + ", email=" + email + ", password=" + password + ", phone_number=" + phone_number + ", description=" + description + ", role=" + role + ", status=" + status + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }
    
}
