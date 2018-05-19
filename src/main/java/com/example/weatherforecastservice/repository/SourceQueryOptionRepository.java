package com.example.weatherforecastservice.repository;

import com.example.weatherforecastservice.model.SourceQueryOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SourceQueryOptionRepository extends JpaRepository<SourceQueryOption, Long> {


    @Query ("SELECT c FROM SourceQueryOption c WHERE c.sourceService.Id = ?1")
    SourceQueryOption getCityQueryOption(Long serviceId);

    @Query("SELECT q FROM SourceQueryOption q WHERE q.queryOption.Id = ?1 ")
    List<SourceQueryOption> getSourceQueryOptions(Long queryOptionId);

}