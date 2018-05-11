package com.example.weatherforecastservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "WeatherHeadlines")
@EntityListeners(AuditingEntityListener.class)


public class WeatherHeadline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "SourceServiceId", nullable = false)
    private SourceService sourceSevrice;

    @NotBlank
    @OneToMany
    @JoinColumn(name = "WeatherHeadlineId", nullable = false)
    private List<WeatherDetail> weatherDetails;

    @Column(name = "QueryType")
    @NotBlank
    private byte QueryType;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "CityId", nullable = false)
    private City city;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "QueryDate")
    private Date QueryDate;



}
