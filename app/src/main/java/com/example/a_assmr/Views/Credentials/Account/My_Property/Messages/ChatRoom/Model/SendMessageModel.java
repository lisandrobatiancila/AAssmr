package com.example.a_assmr.Views.Credentials.Account.My_Property.Messages.ChatRoom.Model;

public class SendMessageModel {
    private int userID;
    private String message_sender, message_receiver;
    private String message, messageType;

    public SendMessageModel(int userID, String message_sender, String message_receiver, String message, String messageType) {
        this.userID = userID;
        this.message_sender = message_sender;
        this.message_receiver = message_receiver;
        this.message = message;
        this.messageType = messageType;
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

    public String getMessage_receiver() {
        return message_receiver;
    }

    public void setMessage_receiver(String message_receiver) {
        this.message_receiver = message_receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
