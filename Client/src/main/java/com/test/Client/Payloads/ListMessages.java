package com.test.Client.Payloads;

import java.util.ArrayList;


public class ListMessages {

    ArrayList<Messages> messageList;

    public ListMessages(ArrayList<Messages> messageList) {
        this.messageList = messageList;
    }

    public ListMessages() {
    }

    public ArrayList<Messages> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<Messages> messageList) {
        this.messageList = messageList;
    }
}
