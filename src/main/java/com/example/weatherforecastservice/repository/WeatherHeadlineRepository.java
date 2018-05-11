package com.example.weatherforecastservice.repository;

import com.example.weatherforecastservice.model.WeatherHeadline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeatherHeadlineRepository extends JpaRepository<WeatherHeadline, Long> {

    @Query ("SELECT w FROM WeatherHeadline w WHERE w.city.Id = ?1 AND w.QueryType = ?2  AND w.QueryDate >= ?3")
    List<WeatherHeadline> getWeatherHeadline (Long city, Byte queryType, Date date);




}
