package com.demo.controllers;

import com.demo.exceptions.ResourceNotFoundException;
import com.demo.models.Option;
import com.demo.repositories.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@RestController
@RequestMapping("api")
public class OptionController {

    @Autowired
    private OptionRepository optionRepository;

    @GetMapping("option")
    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    @GetMapping("option/{optionId}")
    public ResponseEntity<Option> getOptionById(@PathVariable(value = "optionId") Long optionId) throws ResourceNotFoundException {

        Option option = optionRepository.findById(optionId).orElseThrow(() -> new ResourceNotFoundException("Option not found for this id : " + optionId));

        return ResponseEntity.ok().body(option);
    }

    @PostMapping("option")
    public Option createOption(@Valid @RequestBody Option option) {
        return optionRepository.save(option);
    }

    @PutMapping("option/{optionId}")
    public ResponseEntity<Option> updateOption(@PathVariable(value = "optionId") Long optionId, @Valid @RequestBody Option optionDetails) throws ResourceNotFoundException {

        Option option = optionRepository.findById(optionId).orElseThrow(() -> new ResourceNotFoundException("Option not found for this id : " + optionId));

        option.setOptionTitle(optionDetails.getOptionTitle());
        option.setQuestion(optionDetails.getQuestion());
        option.setOptionRank(new Scanner(System.in));

        final Option updatedOption = optionRepository.save(option);

        return ResponseEntity.ok(updatedOption);
    }

    @DeleteMapping("option/{optionId}")
    public Map<String, Boolean> deleteOption(@PathVariable(value = "optionId") Long optionId) throws ResourceNotFoundException {

        Option option = optionRepository.findById(optionId).orElseThrow(() -> new ResourceNotFoundException("Option not found for this id : " + optionId));
        optionRepository.delete(option);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
