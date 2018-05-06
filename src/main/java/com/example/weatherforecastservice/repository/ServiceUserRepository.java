package com.example.weatherforecastservice.repository;

import com.example.weatherforecastservice.model.ServiceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceUserRepository extends JpaRepository<ServiceUser, Long> {



}
