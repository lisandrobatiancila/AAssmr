package com.example.a_assmr.Views.Credentials.Account.My_Property.FeedBacks.Model;

public class FeedBackModel {
    private int feedbackID, userID;
    private String user_full_name, user_feedback;
    private int feedback_rating;
    private String feedback_date;

    public FeedBackModel(int feedbackID, int userID, String user_full_name, String user_feedback, int feedback_rating, String feedback_date) {
        this.feedbackID = feedbackID;
        this.userID = userID;
        this.user_full_name = user_full_name;
        this.user_feedback = user_feedback;
        this.feedback_rating = feedback_rating;
        this.feedback_date = feedback_date;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUser_full_name() {
        return user_full_name;
    }

    public void setUser_full_name(String user_full_name) {
        this.user_full_name = user_full_name;
    }

    public String getUser_feedback() {
        return user_feedback;
    }

    public void setUser_feedback(String user_feedback) {
        this.user_feedback = user_feedback;
    }

    public int getFeedback_rating() {
        return feedback_rating;
    }

    public void setFeedback_rating(int feedback_rating) {
        this.feedback_rating = feedback_rating;
    }

    public String getFeedback_date() {
        return feedback_date;
    }

    public void setFeedback_date(String feedback_date) {
        this.feedback_date = feedback_date;
    }
}
