package com.example.weatherforecastservice.repository;

import com.example.weatherforecastservice.model.WeatherDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDetailRepository extends JpaRepository<WeatherDetail, Long> {
}
