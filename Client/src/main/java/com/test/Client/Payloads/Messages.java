package com.test.Client.Payloads;

import java.time.Instant;

public class Messages {

    String id;
    Instant timestamp;
    String message;

    public Messages(String id, Instant timestamp, String message) {
        this.id = id;
        this.timestamp = timestamp;
        this.message = message;
    }

    public Messages() {
    }

    public Messages(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
