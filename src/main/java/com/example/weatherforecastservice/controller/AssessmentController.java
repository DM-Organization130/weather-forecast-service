package com.example.weatherforecastservice.controller;

import com.example.weatherforecastservice.model.Assessment;
import com.example.weatherforecastservice.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AssessmentController {
    @Autowired
    private AssessmentRepository assessmentRepository;


    @PostMapping("/postassessment")
    public void postAssessment(Assessment assessment)
    {
        assessmentRepository.save(assessment);

    }
}
