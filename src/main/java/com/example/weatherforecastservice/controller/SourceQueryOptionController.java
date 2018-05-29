package com.example.weatherforecastservice.controller;

import com.example.weatherforecastservice.model.ServiceUser;
import com.example.weatherforecastservice.model.SourceQueryOption;
import com.example.weatherforecastservice.repository.ServiceUserRepository;
import com.example.weatherforecastservice.repository.SourceQueryOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SourceQueryOptionController {
    @Autowired
    private SourceQueryOptionRepository sourceQueryOptionRepository;
    @Autowired
    private ServiceUserRepository serviceUserRepository;


    @PostMapping("/addsourcequeryoption")
    public SourceQueryOption addSourceQueryOption(String userKey, @RequestBody SourceQueryOption sourceQueryOption)
    {
        if(serviceUserRepository.ısThereServiceUser(userKey, (byte) 1, ServiceUser.adminKey))
        {
            return null;
        }
        return sourceQueryOptionRepository.save(sourceQueryOption);

    }

    @GetMapping("/listsourcequeryoption")
    public List<SourceQueryOption> listSourceQueryOption(String userKey)
    {
        if (serviceUserRepository.ısThereServiceUser(userKey, (byte) 1, ServiceUser.adminKey))
        {
            return null;
        }
        return sourceQueryOptionRepository.findAll();
    }

    @GetMapping("/deletesourcequeryoption")
    public void deleteSourceQueryOption(String userKey, Long id)
    {
        if (serviceUserRepository.ısThereServiceUser(userKey, (byte) 1, ServiceUser.adminKey))
        {
            return;
        }
        sourceQueryOptionRepository.deleteById(id);
    }

}
