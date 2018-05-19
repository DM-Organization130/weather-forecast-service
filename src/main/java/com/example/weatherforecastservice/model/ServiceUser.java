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
@Table(name = "ServiceUsers")
public class ServiceUser {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Description" , length = 200, nullable = false)
    private String Description;

    @Column(name = "DailyLimit", nullable = false)
    private Long DailyLimit;

}
