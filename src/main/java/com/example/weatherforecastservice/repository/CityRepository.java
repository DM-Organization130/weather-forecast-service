package com.example.weatherforecastservice.repository;

import com.example.weatherforecastservice.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City, Long> {


    @Query ("SELECT c FROM City c WHERE c.Name = ?1 AND c.Country = ?2")
    City matchCity(String cityName, String countryNme);

}
