package com.example.weatherforecastservice.repository;

import com.example.weatherforecastservice.model.ServiceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceUserRepository extends JpaRepository<ServiceUser, Long> {

    @Query("SELECT CASE WHEN COUNT(su) > 0 THEN true ELSE false END FROM ServiceUser su WHERE (su.APIKey = ?1 AND su.UserType >= ?2) OR ?1 = ?3")
    boolean ısThereServiceUser (String apiKey, byte userType, String adminKey);

    @Query("SELECT su FROM ServiceUser su WHERE su.APIKey = ?1")
    ServiceUser findServiceUser (String apikey);
}
