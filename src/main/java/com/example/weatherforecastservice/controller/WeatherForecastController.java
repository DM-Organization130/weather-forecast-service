package com.example.weatherforecastservice.controller;

import com.example.weatherforecastservice.ServiceCall;
import com.example.weatherforecastservice.model.*;
import com.example.weatherforecastservice.repository.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.svenson.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.LongFunction;

@RestController
public class WeatherForecastController {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private QueryOptionRepository queryOptionRepository;
    @Autowired
    private SourceServiceRepository sourceServiceRepository;
    @Autowired
    private SourceQueryOptionRepository sourceQueryOptionRepository;
    @Autowired
    private WeatherHeadlineRepository weatherHeadlineRepository;
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private ServiceUserRepository serviceUserRepository;

    @GetMapping("/forecastbycity")
    public  List<WeatherHeadline> GetForecasts(String apiKey, String city, String country, Long queryOptionId)
    {
        if (serviceUserRepository.ısThereServiceUser(apiKey, (byte) 0, ServiceUser.adminKey))
        {
            return null;
        }

        City c = cityRepository.matchCity(city, country);
        List<SourceQueryOption> sourceQueryOptions = sourceQueryOptionRepository.getSourceQueryOptions(queryOptionId);


        if(c == null)
        {
            SourceService cityService = sourceServiceRepository.FindCityService();
            SourceQueryOption sqo = sourceQueryOptionRepository.getCityQueryOption(cityService.getId());

            String urlString = cityService.getServiceURL();
            urlString += sqo.getRequestPath();
            urlString =  urlString
                    .replace("{city}",city)
                    .replace("{apikey}", cityService.getAPIKey());
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(urlString);

            try {
                HttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);

                Gson gson = new Gson();
                HashMap<String, String> hmap = (HashMap<String, String>) gson.fromJson(sqo.getResponseMapping(), HashMap.class);

                for (String key: hmap.keySet()
                        ) {
                    content = content.replace(hmap.get(key), key);
                }

                JsonElement jsonElement = gson.toJsonTree(JsonPath.read(content, sqo.getResponsePath()));

                c = gson.fromJson(jsonElement, City.class);

                /*

                LinkedHashMap lhm = JsonPath.read(content, sqo.getResponsePath());
                JsonElement jsonElement = gson.toJsonTree(lhm);
                c = gson.fromJson(jsonElement, City.class);

                 */


                if (cityRepository.matchCity(c.getName(), c.getCountry()) == null){
                    cityRepository.save(c);
                }
                else
                    c = cityRepository.matchCity(c.getName(), c.getCountry());
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }


        Date currentDate = new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(15));

        List<WeatherHeadline> forecast = weatherHeadlineRepository.getWeatherHeadline(c.getId(), queryOptionId , currentDate);

        sourceQueryOptions.removeIf(sqo1 ->
                forecast.stream().anyMatch(f ->

                        f.getSourceService().getId().equals(sqo1.getSourceService().getId())

                )
        );


        for (SourceQueryOption sqo1: sourceQueryOptions)
        {
            List<WeatherDetail> weatherDetails;

            Gson gson = new Gson();
            HashMap<String, String> hmap = (HashMap<String, String>) gson.fromJson(sqo1.getResponseMapping(), HashMap.class);

            String rp =  sqo1.getRequestPath();
            rp = rp.replace("{apikey}",sqo1.getSourceService().getAPIKey()).
                    replace("{lat}", c.getLatitude()).
                    replace("{lng}",c.getLongitude()).
                    replace("{city}",c.getName());

            String url = (sqo1.getSourceService().getServiceURL() + rp);

            weatherDetails = ServiceCall.RestGet(url, sqo1.getResponsePath() , hmap);

            Date date = new Date();
            WeatherHeadline wh = new WeatherHeadline();
            wh.setQueryDate(date);
            wh.setCity(c);
            wh.setQueryType(sqo1.getQueryOption().getId());
            wh.setSourceService(sqo1.getSourceService());
            wh.setWeatherDetails(weatherDetails);
            forecast.add(wh);
            weatherHeadlineRepository.save(wh);

        }

        return forecast;
    }

}
