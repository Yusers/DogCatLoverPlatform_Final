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
public class Chat {
    private String sender_id;
    private String receiver_id;
    private int id;
    private String topic;
    private Date created_at;
    private Date updated_at;

    public Chat() {
    }

    public Chat(String sender_id, String receiver_id, int id, String topic, Date created_at) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.id = id;
        this.topic = topic;
        this.created_at = created_at;
    }
    
    public Chat(String sender_id, String receiver_id, int id, String topic, Date created_at, Date updated_at) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.id = id;
        this.topic = topic;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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
        return "Chat{" + "sender_id=" + sender_id + ", receiver_id=" + receiver_id + ", id=" + id + ", topic=" + topic + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }
    
}
