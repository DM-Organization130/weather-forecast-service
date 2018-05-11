package com.example.weatherforecastservice.repository;

import com.example.weatherforecastservice.model.QueryOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QueryOptionRepository extends JpaRepository<QueryOption, Long> {

}
