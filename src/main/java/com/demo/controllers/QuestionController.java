package com.demo.controllers;

import com.demo.exceptions.ResourceNotFoundException;
import com.demo.models.Question;
import com.demo.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("api")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("question")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("question/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable(value = "questionId") Long questionId) throws ResourceNotFoundException {

        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found for this id : " + questionId));

        return ResponseEntity.ok().body(question);
    }

    @PostMapping("question")
    public Question createQuestion(@Valid @RequestBody Question question) {
        return questionRepository.save(question);
    }

    @PutMapping("question/{questionId}")
    public ResponseEntity<Question> updateQuestion(@PathVariable(value = "questionId") Long questionId, @Valid @RequestBody Question questionDetails) throws ResourceNotFoundException {

        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found for this id : " + questionId));

        question.setOptions(questionDetails.getOptions());
        question.setQuestionBody(questionDetails.getQuestionBody());
        question.setQuestionType(questionDetails.getQuestionType());
        question.setSurvey(questionDetails.getSurvey());
        question.setQuestionRank(new Scanner(System.in));

        final Question updatedQuestion = questionRepository.save(question);

        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("question/{questionId}")
    public Map<String, Boolean> deleteQuestion(@PathVariable(value = "questionId") Long questionId) throws ResourceNotFoundException {

        Question question = questionRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found for this id : " + questionId));
        questionRepository.delete(question);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
