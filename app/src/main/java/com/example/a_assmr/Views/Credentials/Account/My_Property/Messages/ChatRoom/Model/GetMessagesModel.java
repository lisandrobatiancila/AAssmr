package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model;

public class GetMessagesModel {
    private int messageID, userID;
    private String message_sender, message_reciever, message_type, is_read, message;

    public GetMessagesModel(int messageID, int userID, String message_sender, String message_reciever, String message_type, String is_read, String message) {
        this.messageID = messageID;
        this.userID = userID;
        this.message_sender = message_sender;
        this.message_reciever = message_reciever;
        this.message_type = message_type;
        this.is_read = is_read;
        this.message = message;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getMessage_sender() {
        return message_sender;
    }

    public void setMessage_sender(String message_sender) {
        this.message_sender = message_sender;
    }

    public String getMessage_reciever() {
        return message_reciever;
    }

    public void setMessage_reciever(String message_reciever) {
        this.message_reciever = message_reciever;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getIs_read() {
        return is_read;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
