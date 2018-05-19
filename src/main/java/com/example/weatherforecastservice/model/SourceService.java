package com.example.weatherforecastservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SourceServices")
public class SourceService {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Description" , length = 200, nullable = false)
    private String Description;

    @Column(name = "ServiceURL", nullable = false)
    private String ServiceURL;

    @Column(name = "DailyLimit", nullable = false)
    private Long DailyLimit;

    @Column(name = "APIKey" , unique = true, nullable = false)
    private  String APIKey;

}
