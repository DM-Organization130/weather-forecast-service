package com.example.weatherforecastservice.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "WeatherDetails")
@EntityListeners(AuditingEntityListener.class)
public class WeatherDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @Column(name = "Description", nullable = false)
    private String Description;

    @Column(name = "MinTemperature", nullable = false)
    private Long MinTemperature;

    @Column(name = "MaxTemperature", nullable = false)
    private Long MaxTemperature;

    @Column(name = "Pressure", nullable = false)
    private Long Pressure;

    @Column(name = "Humidity", nullable = false)
    private Long Humidity;

    @Column(name = "Wind", nullable = false)
    private Long Wind;


}
