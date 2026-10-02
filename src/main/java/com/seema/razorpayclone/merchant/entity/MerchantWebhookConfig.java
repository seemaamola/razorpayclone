package com.seema.razorpayclone.merchant.entity;

import jakarta.persistence.*;


import java.util.UUID;

@Entity
@Table(name = "merchant_webhook_config")
public class MerchantWebhookConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id",nullable = false)
    private Merchant merchant;

    @Column(nullable = false, length = 500)
    private String targetUrl; // - merchant’s endpoint Razorpay calls on events

    @Column(length = 255)
    private String webhookSecretHash;  // - signs payload so merchant can verify origin

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(length = 255)
    private String eventTypes;

    //comma seperated list of event types to subscribe to
    //empty = subscribe to all events

}
