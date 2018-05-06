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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Cities")
public class City {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Name")
    private String Name;

    @Column(name = "adminName")
    private String AdminName;

    @Column(name = "Country")
    private String Country;

    @Column(name = "FeatureClass")
    private String FeatureClass;

    @Column(name = "Latitude")
    private String Latitude;

    @Column(name = "Longitude")
    private String Longitude;

}
