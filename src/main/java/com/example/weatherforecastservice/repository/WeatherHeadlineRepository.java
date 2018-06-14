package com.example.weatherforecastservice.repository;

import com.example.weatherforecastservice.model.ServiceUser;
import com.example.weatherforecastservice.model.SourceService;
import com.example.weatherforecastservice.model.WeatherHeadline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeatherHeadlineRepository extends JpaRepository<WeatherHeadline, Long> {

    @Query ("SELECT w FROM WeatherHeadline w WHERE w.city.Id = ?1 AND w.QueryType = ?2  AND w.QueryDate >= ?3")
    List<WeatherHeadline> getWeatherHeadline (Long city, Long queryType, Date date);

    @Query("SELECT MAX(wh.QueryDate) FROM WeatherHeadline wh WHERE wh.ServiceUser.Id = ?1 ")
    Date latestWeatherHeadlineOfUser (Long serviceUserId);

    @Query("SELECT CASE WHEN COUNT(ss) > MAX(ss.DailyLimit) THEN TRUE ELSE FALSE END FROM WeatherHeadline wh JOIN wh.SourceService ss WHERE ss.Id = ?3 AND wh.QueryDate BETWEEN ?1 AND ?2")
    boolean isLimitExceeded (Date begin, Date end, Long sourceServiceId);

}
