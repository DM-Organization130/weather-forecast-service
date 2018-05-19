package com.example.weatherforecastservice.controller;

import com.example.weatherforecastservice.model.SourceService;
import com.example.weatherforecastservice.repository.SourceServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SourceServiceController {
    @Autowired
    private SourceServiceRepository sourceServiceRepository;

    @GetMapping("/addsourceservice")
    public SourceService addCityService(String apiKey, Long dailyLimit, String url, String  description)
    {
        SourceService sourceService = new SourceService();
        sourceService.setDescription(description);
        sourceService.setServiceURL(url);
        sourceService.setDailyLimit(dailyLimit);
        sourceService.setAPIKey(apiKey);

        sourceServiceRepository.save(sourceService);

        return sourceService;
    }

    @GetMapping("/deletesourceservice")
    public void deleteSourceService(Long id)
    {
        sourceServiceRepository.deleteById(id);
    }

    @GetMapping("/listsourceservices")
    public List<SourceService> listSourceServices ()
    {
        return sourceServiceRepository.findAll();
    }

}
