package com.example.alherd.carapp.model;

import java.util.UUID;

public class Car {
    private UUID mId;
    private String title;
    private String mark;
    private int cost;
    private String power;
    private int doorsNumber;
    private String bodyType;
    private int seatsNumber;
    private String startRelease;
    private String endRelease;
    private String photo;

    public Car() {
        this(UUID.randomUUID());
    }

    public Car(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public int getDoorsNumber() {
        return doorsNumber;
    }

    public void setDoorsNumber(int doorsNumber) {
        this.doorsNumber = doorsNumber;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public String getStartRelease() {
        return startRelease;
    }

    public void setStartRelease(String startRelease) {
        this.startRelease = startRelease;
    }

    public String getEndRelease() {
        return endRelease;
    }

    public void setEndRelease(String endRelease) {
        this.endRelease = endRelease;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
