package com.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "answer_option")
public class AnswerOption {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    @JsonBackReference
    private Answer answer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answerOption_id")
    private Long answerOptionId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "survey_id")
    @JsonIgnore
    private Survey survey;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "option_id")
    @JsonIgnore
    private Option option;

    @Column(name = "option_value")
    private String optionValue;

    public AnswerOption() {}

    public AnswerOption(Answer answer, Survey survey, Question question, Option option, String optionValue) {
        this.answer = answer;
        this.survey = survey;
        this.question = question;
        this.option = option;
        this.optionValue = optionValue;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Long getAnswerOptionId() {
        return answerOptionId;
    }

    public void setAnswerOptionId(Long answerOptionId) {
        this.answerOptionId = answerOptionId;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }
}
