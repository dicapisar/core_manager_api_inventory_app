package com.dicapisar.coreManagerAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_id")
    private Long id;

    @Column(name = "con_active", nullable = false)
    private boolean isActive;

    @Column(name = "con_name", nullable = false)
    private String name;

    @Column(name = "con_email", nullable = false)
    private String email;

    @Column(name = "con_phone_number", nullable = false)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "con_provider_id")
    private Provider provider;

    @Column(name = "con_creation_date")
    private LocalDateTime creationDate;

    @Column(name = "con_update_date")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "con_creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "con_updater_id")
    private User updater;

}
