package com.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "survey")
public class Survey {

    @OneToMany(mappedBy = "survey")
    @OrderBy("questionId ASC")
    @JsonManagedReference
    List<Question> questions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyId;

    @Column(name = "survey_title")
    private String surveyTitle;

    @Column(name = "survey_description")
    private String surveyDescription;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "survey_status")
    private Status surveyStatus;

    public Survey() {}

    public Survey(String surveyTitle, String surveyDescription, LocalDate effectiveDate, Status surveyStatus) {
        this.surveyTitle = surveyTitle;
        this.surveyDescription = surveyDescription;
        this.effectiveDate = effectiveDate;
        this.surveyStatus = surveyStatus;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public String getSurveyDescription() {
        return surveyDescription;
    }

    public void setSurveyDescription(String surveyDescription) {
        this.surveyDescription = surveyDescription;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Status getSurveyStatus() {
        return surveyStatus;
    }

    public void setSurveyStatus(Status surveyStatus) {
        this.surveyStatus = surveyStatus;
    }

    public Question getQuestionByNumber(int number) {
        return isValidQuestionNumber(number) ? questions.get(number - 1) : null;
    }

    public boolean isValidQuestionNumber(int number) {
        return number > 0 && number <= questions.size();
    }

    public boolean isLastQuestion(Question currentQuestion) {
        int nextNumber = getNextQuestionNumber(currentQuestion);
        return !isValidQuestionNumber(nextNumber);
    }

    public int getNextQuestionNumber(Question currentQuestion) {
        return questions.indexOf(currentQuestion) + 2;
    }
}
