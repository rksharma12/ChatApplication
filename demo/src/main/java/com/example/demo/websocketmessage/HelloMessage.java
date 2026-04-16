package com.example.demo.websocketmessage;

public class HelloMessage {

    private String message;  // lowercase!
    private String sender;
    private String reciever;
    public HelloMessage() {
    }

    public HelloMessage(String message,String reciever,String sender) {
        this.message = message;
        this.sender=sender;
        this.reciever=reciever;
    }

    public String getMessage() {
        return message;
    }

    public String getReciever() {
        return reciever;
    }

    public String getSender() {
        return sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}