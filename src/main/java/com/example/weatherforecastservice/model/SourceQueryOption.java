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

    @Column(name = "QueryDescription", nullable = false)
    private String QueryDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SourceServiceId", updatable=false, insertable = false, nullable = false)
    private SourceService sourceService;

    @Column(name = "SourceServiceId", nullable = false)
    private Long SourceServiceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "QueryOptionId" , updatable=false, insertable = false, nullable = false)
    private QueryOption queryOption;

    @Column(name = "QueryOptionId", nullable = false)
    private Long QueryOptionId;

    @JsonIgnoreProperties
    @Column(name = "RequestPath", nullable = false)
    private String RequestPath;

    @Column(name = "ResponsePath", nullable = false)
    private String ResponsePath ;

    @Column(name = "ResponseMapping", nullable = false)
    private String ResponseMapping ;

}
