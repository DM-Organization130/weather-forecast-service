package com.example.weatherforecastservice.controller;

import com.example.weatherforecastservice.model.QueryOption;
import com.example.weatherforecastservice.repository.QueryOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class QueryOptionController {
    @Autowired
    private QueryOptionRepository queryOptionRepository;
    //repositoryi doldur boşal faksiyonlarını yaz test et ikisini de

    @GetMapping("/initqueryoption")
    public List<QueryOption> initQueryOption()
    {
        List<String> queryDescriptions = Arrays.asList("Measure", "Day", "ThreeDay", "Weekly", "Monthly", "CityQuery");
        if (queryOptionRepository.count() == 0) {
            for (String s : queryDescriptions) {
                QueryOption qo = new QueryOption();
                qo.setQueryDescription(s);
                queryOptionRepository.save(qo);
            }
        }
        return queryOptionRepository.findAll();
    }

    @GetMapping("/clearqueryoptions")
    public void clearQueryOptions()
    {
        queryOptionRepository.deleteAll();
    }
}
