package com.dicapisar.coreManagerAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bra_id")
    private Long id;

    @Column(name = "bra_active", nullable = false)
    private boolean isActive;

    @Column(name = "bra_name", nullable = false)
    private String name;

    @Column(name = "bra_creation_date")
    private LocalDateTime creationDate;

    @Column(name = "bra_update_date")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bra_creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bra_updater_id")
    private User updater;

}
