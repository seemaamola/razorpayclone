package com.seema.razorpayclone.operations.entity;


import com.seema.razorpayclone.common.enums.WebhookEventStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.SQLType;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name="webhook_event")
public class WebhookEvent {

    //logging webhook event and store it in db

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Column(nullable = false, length = 100)
    private String eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String,Object> payload;

    @Column(nullable = false)
    private String targetUrl;

    @Column(nullable = false)
    private String signature;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private WebhookEventStatus status;

    @Column(nullable = false)
    private Integer attempts;

    private LocalDateTime nextRetryAt;

    private LocalDateTime lastAttemptAt;

    @Column(nullable = false)
    private Integer lastResponseCode;

    @Column(length = 1000)
    private  String lastResponseBody;

    private LocalDateTime deliveredAt;


}
