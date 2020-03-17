package com.demo.controllers;

import com.demo.exceptions.ResourceNotFoundException;
import com.demo.models.*;
import com.demo.repositories.QuestionRepository;
import com.demo.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("api")
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @GetMapping("survey")
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @GetMapping("survey/{surveyId}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable(value = "surveyId") Long surveyId) throws ResourceNotFoundException {

        Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new ResourceNotFoundException("Survey not found for this id : " + surveyId));

        return ResponseEntity.ok().body(survey);
    }

    @PostMapping("survey")
    public Survey createSurvey(@Valid @RequestBody Survey survey) throws Exception{
        if (survey == null)
            throw new Exception("Parameter Missing");

       Survey s = surveyRepository.save(survey);

       if (survey.getQuestions() != null)
           questionRepository.saveAll(survey.getQuestions());
        return s;
    }

    @PutMapping("survey/{surveyId}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable(value = "surveyId") Long surveyId, @Valid @RequestBody Survey surveyDetails) throws ResourceNotFoundException {

        Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new ResourceNotFoundException("Survey not found for this id : " + surveyId));

        if (survey.getEffectiveDate().isAfter(java.time.LocalDate.now())) {

            survey.setQuestions(surveyDetails.getQuestions());
            survey.setSurveyTitle(surveyDetails.getSurveyTitle());
            survey.setSurveyDescription(surveyDetails.getSurveyDescription());
            survey.setEffectiveDate(surveyDetails.getEffectiveDate());
            survey.setSurveyStatus(Status.ACTIVE);

            final Survey updatedSurvey = surveyRepository.save(survey);

            return ResponseEntity.ok(updatedSurvey);
        }

        else
            survey.setSurveyStatus(Status.PASSIVE);

        return ResponseEntity.ok(survey);
    }

    @DeleteMapping("survey/{surveyId}")
    public Map<String, Boolean> deleteSurvey(@PathVariable(value = "surveyId") Long surveyId) throws ResourceNotFoundException {

        Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new ResourceNotFoundException("Survey not found for this id : " + surveyId));

        survey.setSurveyStatus(Status.DELETED);

        surveyRepository.delete(survey);

        Map<String, Boolean> response = new HashMap<>();

        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
