package com.example.weatherforecastservice.controller;

import com.example.weatherforecastservice.model.Assessment;
import com.example.weatherforecastservice.model.ServiceUser;
import com.example.weatherforecastservice.repository.AssessmentRepository;
import com.example.weatherforecastservice.repository.ServiceUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AssessmentController {
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private ServiceUserRepository serviceUserRepository;


    @PostMapping("/postassessment")
    public void postAssessment(String userKey, @RequestBody Assessment assessment)
    {
        if (serviceUserRepository.ısThereServiceUser(userKey, (byte) 0, ServiceUser.adminKey))
        {
            return;
        }
        assessmentRepository.save(assessment);

    }
}
