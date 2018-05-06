package com.example.weatherforecastservice.repository;

import com.example.weatherforecastservice.model.SourceService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceServiceRepository extends JpaRepository<SourceService, Long> {


    @Query("SELECT s FROM SourceService s WHERE s.Description ='CityService'")
    SourceService FindCityService();
}
