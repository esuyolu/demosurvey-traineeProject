package com.demo.models;

public enum Status {
    ACTIVE(0), PASSIVE(1), DELETED(2);

    private int status;

    Status(int status)
    {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
