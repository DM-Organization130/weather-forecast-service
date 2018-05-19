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
@EntityListeners(AuditingEntityListener.class)

@Table(name = "Assessment")
public class Assessment {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ServiceUserId" ,updatable=false, insertable = false, nullable = false)
    private ServiceUser user;

    @Column(name = "ServiceUserId", nullable = false)
    private Long ServiceUserId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "WeatherDetailId", updatable=false, insertable = false, nullable = false)
    private WeatherDetail detail;

    @Column(name = "WeatherDetailId", nullable = false)
    private Long WeatherDetailId;

    @Column(name = "QueryDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date QueryDate;

    @Column(name = "Point", nullable = false)
    private int Point;




}
