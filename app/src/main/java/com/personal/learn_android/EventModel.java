package com.personal.learn_android;

/**
 * Created by Bervianto Leo P on 21/02/2017.
 */

public class EventModel {
    private String event_name;
    private String tanggal_event;
    private Integer image;
    private boolean selected;

    public EventModel(String event_name, String tanggal_event, Integer image) {
        this.event_name = event_name;
        this.tanggal_event = tanggal_event;
        this.image = image;
        selected = false;
    }

    public String getEventName(){
        return event_name;
    }

    public void setEventName(String event_name){
        this.event_name = event_name;
    }

    public String getTanggalEvent(){
        return tanggal_event;
    }

    public void setTanggalEvent(String tanggal_event){
        this.tanggal_event = tanggal_event;
    }

    public Integer getImage(){
        return image;
    }

    public void setImage(Integer image){
        this.image = image;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
