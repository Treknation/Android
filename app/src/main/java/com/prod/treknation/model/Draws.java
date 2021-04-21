package com.prod.treknation.model;

import java.util.Date;

public  class Draws {

    Long crsScoreOfTheLowestRank;
    Date dateTime;
    Long noOfTheInvitationsIssued;
    String category;


    public Long getCrsScoreOfTheLowestRank() {
        return crsScoreOfTheLowestRank;
    }

    public void setCrsScoreOfTheLowestRank(Long crsScoreOfTheLowestRank) {
        this.crsScoreOfTheLowestRank = crsScoreOfTheLowestRank;
    }

    public Long getNoOfTheInvitationsIssued() {
        return noOfTheInvitationsIssued;
    }

    public void setNoOfTheInvitationsIssued(Long noOfTheInvitationsIssued) {
        this.noOfTheInvitationsIssued = noOfTheInvitationsIssued;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Draws{" +
                "crsScoreOfTheLowestRank='" + crsScoreOfTheLowestRank + '\'' +
                ", dateTime=" + dateTime +
                ", noOfTheInvitationsIssued='" + noOfTheInvitationsIssued + '\'' +
                '}';
    }
}
