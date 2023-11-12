/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author overw
 */
public class Trade_Media {
    private int id;
    private int trade_id;
    private int media_id;

    public Trade_Media(int id, int trade_id, int media_id) {
        this.id = id;
        this.trade_id = trade_id;
        this.media_id = media_id;
    }

    public Trade_Media(int trade_id, int media_id) {
        this.trade_id = trade_id;
        this.media_id = media_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(int trade_id) {
        this.trade_id = trade_id;
    }

    public int getMedia_id() {
        return media_id;
    }

    public void setMedia_id(int media_id) {
        this.media_id = media_id;
    }
    
}
