package com.example.weatherforecastservice.controller;

import com.example.weatherforecastservice.model.SourceQueryOption;
import com.example.weatherforecastservice.repository.SourceQueryOptionRepository;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
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

    @PostMapping("/addsourcequeryoption")
    public SourceQueryOption addSourceQueryOption(@RequestBody String  sqoString)
    {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(sqoString);
        SourceQueryOption sqo = gson.fromJson(sqoString,SourceQueryOption.class);

        return sourceQueryOptionRepository.save(sqo);
    }

    @GetMapping("/listsourcequeryoption")
    public List<SourceQueryOption> listSourceQueryOption()
    {
        return sourceQueryOptionRepository.findAll();
    }

    @GetMapping("/deletesourcequeryoption")
    public void deleteSourceQueryOption(Long id){sourceQueryOptionRepository.deleteById(id);}

}
