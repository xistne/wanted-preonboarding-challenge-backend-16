package com.wanted.preonboarding.ticket.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private UUID performanceId;
    @Column(nullable = false)
    private int round;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, name = "is_subscribe", columnDefinition = "varchar default 'enable'")
    private String isSubscribe;
}
