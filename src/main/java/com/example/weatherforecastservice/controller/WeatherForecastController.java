package com.example.weatherforecastservice.controller;

import com.example.weatherforecastservice.model.*;
import com.example.weatherforecastservice.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
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
import java.util.HashMap;
import java.util.List;

@RestController
public class WeatherForecastController {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    SourceServiceRepository sourceServiceRepository;

    @Autowired
    SourceQueryOptionRepository sourceQueryOptionRepository;

    @GetMapping("/forecastbycity")
    public  List<WeatherHeadline> GetForecasts(String city, String country)
    {
        List<WeatherHeadline> forecast = new ArrayList<>();
        City c = cityRepository.matchCity(city, country);

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

        return null;
    }
}
