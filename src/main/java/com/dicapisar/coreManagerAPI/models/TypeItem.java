package com.dicapisar.coreManagerAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "types_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "typ_ite_id")
    private Long id;

    @Column(name = "typ_ite_active", nullable = false)
    private boolean isActive;

    @Column(name = "typ_ite_name", nullable = false)
    private String name;

    @Column(name = "typ_ite_perishable", nullable = false)
    private boolean isPerishable;

    @Column(name = "typ_ite_creation_date")
    private LocalDateTime creationDate;

    @Column(name = "typ_ite_update_date")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typ_ite_creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typ_ite_updater_id")
    private User updater;

}
