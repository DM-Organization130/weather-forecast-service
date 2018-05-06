package com.example.weatherforecastservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SourceQueryOptions")
public class SourceQueryOption {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "QueryDescription")
    private String QueryDescription;

    @ManyToOne
    @JoinColumn(name = "SourceServiceId", nullable = false)
    private SourceService sourceService;


    @ManyToOne
    @JoinColumn(name = "QueryOptionId" , nullable = false)
    private QueryOption queryOption;

    @JsonIgnoreProperties
    @Column(name = "JsonFormat")
    private String JsonFormat;

    @Column(name = "ResponseRegex")
    private String ResponseRegex ;






}
