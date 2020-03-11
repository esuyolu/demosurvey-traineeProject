package com.demo.models;

public enum QuestionType {
    SINGLE(0), MULTIPLE(1), TEXT(2);

    private int type;

    QuestionType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
