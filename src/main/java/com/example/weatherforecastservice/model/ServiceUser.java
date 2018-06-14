package com.example.weatherforecastservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter

@Component

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ServiceUsers")
public class ServiceUser {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "UserType", nullable = false)
    private byte UserType;

    @Column(name = "APIKey" , unique = true, nullable = false)
    private  String APIKey;

    @Column(name = "Description" , length = 200, nullable = false)
    private String Description;

    @Column(name = "DailyLimit", nullable = false)
    private Long DailyLimit;

    @Column(name = "LimitCounter", nullable = false)
    private Long LimitCounter;

    public static String adminKey;

    @Value("${adminKey}")
    public void setAdminKey(String ak) {
        adminKey = ak;
    }
}

