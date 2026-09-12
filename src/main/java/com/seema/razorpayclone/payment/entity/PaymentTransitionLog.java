package com.seema.razorpayclone.payment.entity;

import com.seema.razorpayclone.common.enums.PaymentActor;
import com.seema.razorpayclone.common.enums.PaymentEvent;
import com.seema.razorpayclone.common.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_transition_log")
public class PaymentTransitionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="payment_id",nullable = false)
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private PaymentStatus fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "event",nullable = false,length = 30)
    private PaymentEvent event;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_status", nullable = false,length = 30)
    private PaymentStatus toStatus;

    @Enumerated(EnumType.STRING)
    @Column(name="actor",length = 100)
    private PaymentActor actor;  // who made this action happen -- mostly system could be admin sometimes

    @Column(name = "occured_at", nullable = false)
    private LocalDateTime occuredAt;


}
