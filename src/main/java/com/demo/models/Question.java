package com.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "question")
public class Question {

    @OneToMany(mappedBy = "question")
    @OrderBy("optionRank")
    @JsonManagedReference
    private List<Option> options;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    @JsonBackReference
    private Survey survey;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "question_body")
    private String questionBody;

    @Column(name = "question_type")
    private QuestionType questionType;

    @Column(name = "question_rank")
    private int questionRank;

    public Question() {}

    public Question(String questionBody, QuestionType questionType, Survey survey, Scanner kb) {
        this.questionBody = questionBody;
        this.questionType = questionType;
        this.survey = survey;
        System.out.print("question_rank:");
        kb = new Scanner(System.in);
        this.questionRank = Integer.parseInt(kb.nextLine());
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public int getQuestionRank() {
        return questionRank;
    }

    public void setQuestionRank(Scanner kb) {
        System.out.print("question_rank:");
        kb = new Scanner(System.in);
        this.questionRank = Integer.parseInt(kb.nextLine());
    }
}
