package com.demo.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "answer")
public class Answer {

    @OneToMany(mappedBy = "answer")
    @JsonManagedReference
    private List<AnswerOption> answerOptions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;

    public Answer() {}

    public Answer(List<AnswerOption> answerOptions, Long answerId) {
        this.answerId = answerId;
        this.answerOptions = answerOptions;
    }

    public List<AnswerOption> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<AnswerOption> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

}
