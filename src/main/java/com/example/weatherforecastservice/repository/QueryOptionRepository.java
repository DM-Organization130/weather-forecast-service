package com.example.weatherforecastservice.repository;

import com.example.weatherforecastservice.model.QueryOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryOptionRepository extends JpaRepository<QueryOption, Long> {

}
