package com.example.weatherforecastservice.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

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

    @Column(name = "Description")
    @NotBlank
    private String Description;

    @Column(name = "Temperature")
    @NotBlank
    private Long Temperature;

    @Column(name = "Pressure")
    private Long Pressure;

    @Column(name = "Rainy")
    private Long Rainy;

    @Column(name = "Moisture")
    private Long Moisture;

    @Column(name = "Wind")
    private Long Wind;

}
