package com.example.weatherforecastservice.controller;

import com.example.weatherforecastservice.model.QueryOption;
import com.example.weatherforecastservice.model.ServiceUser;
import com.example.weatherforecastservice.repository.QueryOptionRepository;
import com.example.weatherforecastservice.repository.ServiceUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class QueryOptionController {
    @Autowired
    private QueryOptionRepository queryOptionRepository;
    @Autowired
    private ServiceUserRepository serviceUserRepository;

    @GetMapping("/initqueryoption")
    public List<QueryOption> initQueryOption(String userKey)
    {
        if (serviceUserRepository.ısThereServiceUser(userKey, (byte) 2, ServiceUser.adminKey))
        {
            return null;
        }
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
    public void clearQueryOptions(String userKey)
    {
        if (serviceUserRepository.ısThereServiceUser(userKey, (byte) 2, ServiceUser.adminKey))
        {
            return;
        }
        queryOptionRepository.deleteAll();
    }
}
