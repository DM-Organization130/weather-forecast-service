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

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ServiceUserId" ,updatable=false, insertable = false)
    private ServiceUser user;

    @Column(name = "ServiceUserId")
    private Long ServiceUserId;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WeatherDetailId", updatable=false, insertable = false)
    private WeatherDetail detail;

    @Column(name = "WeatherDetailId")
    private Long WeatherDetailId;

    @NotBlank
    @Column(name = "QueryDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date QueryDate;

    @NotBlank
    @Column(name = "Point")
    private int Point;




}
