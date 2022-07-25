package com.dicapisar.coreManagerAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ite_id")
    private Long id;

    @Column(name = "ite_active", nullable = false)
    private boolean isActive;

    @Column(name = "ite_status", nullable = false)
    private String status;

    @Column(name = "ite_name", nullable = false)
    private String name;

    @Column(name = "ite_count", nullable = false)
    private int count;

    @Column(name = "ite_price", nullable = false)
    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ite_brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ite_type_item_id")
    private TypeItem typeItem;

    @Column(name = "ite_creation_date")
    private LocalDateTime creationDate;

    @Column(name = "ite_update_date")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ite_creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ite_updater_id")
    private User updater;

}
