package com.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Scanner;

@Entity
@Table(name = "option")
public class Option {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @OrderBy("questionRank")
    @JsonBackReference
    private Question question;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long optionId;

    @Column(name = "option_title")
    private String optionTitle;

    @Column(name = "option_rank")
    private int optionRank;

    @Column(name = "other_value")
    private boolean otherValue;

    public Option() {}

    public Option(String optionTitle, Scanner kb, Question question, boolean otherValue) {
        this.optionTitle = optionTitle;
        System.out.print("option_rank:");
        kb = new Scanner(System.in);
        this.optionRank = Integer.parseInt(kb.nextLine());
        this.question = question;
        this.otherValue = otherValue;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getOptionTitle() {
        return optionTitle;
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
    }

    public int getOptionRank() {
        return optionRank;
    }

    public void setOptionRank(Scanner kb) {
        System.out.print("option_rank:");
        kb = new Scanner(System.in);
        this.optionRank = Integer.parseInt(kb.nextLine());
    }

    public boolean isOtherValue() {
        return otherValue;
    }

    public void setOtherValue(boolean otherValue) {
        this.otherValue = otherValue;
    }
}
