package com.example.weatherforecastservice.controller;

import com.example.weatherforecastservice.model.ServiceUser;
import com.example.weatherforecastservice.model.SourceService;
import com.example.weatherforecastservice.repository.ServiceUserRepository;
import com.example.weatherforecastservice.repository.SourceServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SourceServiceController {
    @Autowired
    private SourceServiceRepository sourceServiceRepository;
    @Autowired
    private ServiceUserRepository serviceUserRepository;

    @PostMapping("/addsourceservice")
    public SourceService addCityService(String userKey, @RequestBody SourceService sourceService)
    {
        if (serviceUserRepository.ısThereServiceUser(userKey, (byte) 1, ServiceUser.adminKey))
        {
            return null;
        }
        return sourceServiceRepository.save(sourceService);
    }

    @GetMapping("/deletesourceservice")
    public void deleteSourceService(String userKey, Long id)
    {
        if (serviceUserRepository.ısThereServiceUser(userKey, (byte) 1, ServiceUser.adminKey))
        {
            return;
        }
        sourceServiceRepository.deleteById(id);
    }

    @GetMapping("/listsourceservices")
    public List<SourceService> listSourceServices (String userKey)
    {
        if (serviceUserRepository.ısThereServiceUser(userKey, (byte) 1, ServiceUser.adminKey))
        {
            return null;
        }
        return sourceServiceRepository.findAll();
    }

}
