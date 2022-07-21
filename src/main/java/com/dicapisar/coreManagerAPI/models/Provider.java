package com.dicapisar.coreManagerAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "providers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private Long id;

    @Column(name = "pro_active", nullable = false)
    private boolean isActive;

    @Column(name = "pro_name", nullable = false)
    private String name;

    @Column(name = "pro_address", nullable = false)
    private String address;

    @Column(name = "pro_document_number", nullable = false)
    private String documentNumber;

    @Column(name = "pro_email", nullable = false)
    private String email;

    @Column(name = "pro_phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "pro_creation_date")
    private LocalDateTime creationDate;

    @Column(name = "pro_update_date")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_creator_id")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_updater_id")
    private User updater;

}
