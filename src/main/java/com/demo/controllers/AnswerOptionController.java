package com.demo.controllers;

import com.demo.exceptions.ResourceNotFoundException;
import com.demo.models.AnswerOption;
import com.demo.repositories.AnswerOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class AnswerOptionController {

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    @GetMapping("answerOption")
    List<AnswerOption> getAllAnswerOptions() {
        return answerOptionRepository.findAll();
    }

    @GetMapping("answerOption/{answerOptionId}")
    public ResponseEntity<AnswerOption> getAnswerOptionById(@PathVariable(value = "answerOptionId") Long answerOptionId) throws ResourceNotFoundException {

        AnswerOption answerOption = answerOptionRepository.findById(answerOptionId).orElseThrow(() -> new org.springframework.data.rest.webmvc.ResourceNotFoundException("AnswerOption not found for this id :" + answerOptionId));

        return ResponseEntity.ok().body(answerOption);
    }

    @PostMapping("answerOption")
    public AnswerOption createAnswerOption(@Valid @RequestBody AnswerOption answerOption) {
        return answerOptionRepository.save(answerOption);
    }

    @PutMapping("answerOption/answerOptionId")
    public ResponseEntity<AnswerOption> updateAnswerOption(@PathVariable(value = "answerOptionId") Long answerOptionId, @Valid @RequestBody AnswerOption answerOptionDetails) throws ResourceNotFoundException {

        AnswerOption answerOption = answerOptionRepository.findById(answerOptionId).orElseThrow(() -> new ResourceNotFoundException("AnswerOption not found for this id: " + answerOptionId));

        answerOption.setAnswer(answerOptionDetails.getAnswer());
        answerOption.setQuestion(answerOptionDetails.getQuestion());
        answerOption.setSurvey(answerOptionDetails.getSurvey());
        answerOption.setOption(answerOptionDetails.getOption());
        answerOption.setOptionValue(answerOptionDetails.getOptionValue());

        final AnswerOption updatedAnswerOption = answerOptionRepository.save(answerOption);

        return ResponseEntity.ok(updatedAnswerOption);
    }

    @DeleteMapping("answerOption/answerOptionId")
    public Map<String, Boolean> deleteAnswerOption(@PathVariable(value = "answerOptionId") Long answerOptionId) throws ResourceNotFoundException {

        AnswerOption answerOption = answerOptionRepository.findById(answerOptionId).orElseThrow(() -> new ResourceNotFoundException("AnswerOption not found for this id: " + answerOptionId));
        answerOptionRepository.delete(answerOption);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
