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

    @Column(name = "Name", nullable = false)
    private String Name;

    @Column(name = "AdminName", nullable = false)
    private String AdminName;

    @Column(name = "Country", nullable = false)
    private String Country;

    @Column(name = "FeatureClass", nullable = false)
    private String FeatureClass;

    @Column(name = "Latitude", nullable = false)
    private String Latitude;

    @Column(name = "Longitude", nullable = false)
    private String Longitude;

}
