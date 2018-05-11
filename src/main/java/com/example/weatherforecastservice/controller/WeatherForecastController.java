package com.example.weatherforecastservice.controller;

import com.example.weatherforecastservice.model.*;
import com.example.weatherforecastservice.repository.*;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @GetMapping("/forecastbycity")
    public  List<WeatherHeadline> GetForecasts(String city, String country, Byte queryOptionId)
    {
        List<WeatherHeadline> forecast = new ArrayList<>();
        City c = cityRepository.matchCity(city, country);
        List<SourceQueryOption> sourceQueryOptions = sourceQueryOptionRepository.getSourceQueryOptions(queryOptionId);


        Date currentDate = new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(15));

        weatherHeadlineRepository.getWeatherHeadline(c.getId(), sourceQueryOptions.get(1).getQueryOption().getId(), currentDate);

        sourceQueryOptions.removeIf(sqo1 ->
                forecast.stream().anyMatch(f ->

                        f.getSourceSevrice().getId().equals(sqo1.getSourceService().getId())
                )
        );



        for (SourceQueryOption sqo1: sourceQueryOptions)
        {
            Date date = new Date();
            List<WeatherDetail> weatherDetails = QueryServices(sqo1, c);
            WeatherHeadline wh = new WeatherHeadline();
            wh.setQueryDate(date);
            wh.setCity(c);
            wh.setQueryType(sqo1.getQueryOption().getId());
            wh.setSourceSevrice(sqo1.getSourceService());
            wh.setWeatherDetails(weatherDetails);
            forecast.add(wh);
            weatherHeadlineRepository.save(wh);

        }


        if(c==null)
        {
            SourceService cityService = sourceServiceRepository.FindCityService();
            SourceQueryOption sqo = sourceQueryOptionRepository.getCityQueryOption(cityService.getId());

            String urlString = cityService.getServiceURL();
            urlString += sqo.getJsonFormat();
            urlString =  urlString
                    .replace("{city}",city)
                    .replace("{apikey}", cityService.getAPIKey());
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("http://" + urlString);
            try {
                HttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                JSONParser parser = new JSONParser();
                HashMap hm = parser.parse(HashMap.class, content);
                ArrayList list = (ArrayList) hm.get("geonames");
                HashMap<String, String> map = (HashMap) list.get(0);
                c = new City();
                c.setName(map.get("name"));
                c.setAdminName(map.get("adminName1"));
                c.setCountry(map.get("countryName"));
                c.setFeatureClass(map.get("fcodeName"));
                c.setLatitude(map.get("lat"));
                c.setLongitude(map.get("lng"));


                if (cityRepository.matchCity(c.getName(), c.getCountry()) == null){
                    cityRepository.save(c);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }


        }

        return forecast;
    }

    @PostMapping("/postassessment")
     public void postAssessment(Assessment assessment)
     {
         assessmentRepository.save(assessment);

     }

    private List<WeatherDetail> QueryServices(SourceQueryOption sqo, City city){
        return null;
    }

}
