package com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Model;

public class FeedBackForm {
    private int userID;
    private String feedback;
    private int rating;

    public FeedBackForm(int userID, String feedback, int rating) {
        this.userID = userID;
        this.feedback = feedback;
        this.rating = rating;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}