package com.demo.controllers;

import com.demo.exceptions.ResourceNotFoundException;
import com.demo.models.Answer;
import com.demo.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("answer")
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @GetMapping("answer/{answerId}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable(value = "answerId") Long answerId) throws ResourceNotFoundException {

        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found for this id :: " + answerId));

        return ResponseEntity.ok().body(answer);
    }

    @PostMapping("answer")
    public Answer createAnswer(@Valid @RequestBody Answer answer) {
        return answerRepository.save(answer);
    }

    @PutMapping("answer/{answerId}")
    public ResponseEntity<Answer> updateQuestion(@PathVariable(value = "answerId") Long answerId, @Valid @RequestBody Answer answerDetails) throws ResourceNotFoundException {

        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found for this id : " + answerId));
        answer.setAnswerOptions(answer.getAnswerOptions());

        final Answer updatedAnswer = answerRepository.save(answer);

        return ResponseEntity.ok(updatedAnswer);
    }

    @DeleteMapping("answer/{answerId}")
    public Map<String, Boolean> deleteAnswer(@PathVariable(value = "answerId") Long answerId) throws ResourceNotFoundException {

        Answer answer = answerRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found for this id : " + answerId));
        answerRepository.delete(answer);

        Map<String, Boolean> response = new HashMap<>();

        response.put("deleted", Boolean.TRUE);

        return response;
    }

}