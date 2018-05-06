package com.example.weatherforecastservice.repository;

import com.example.weatherforecastservice.model.WeatherHeadline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherHeadlineRepository extends JpaRepository<WeatherHeadline, Long> {
}
