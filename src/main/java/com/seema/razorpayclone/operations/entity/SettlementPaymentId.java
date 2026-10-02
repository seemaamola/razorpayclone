package com.seema.razorpayclone.operations.entity;

import jakarta.persistence.Embeddable;

import java.util.UUID;

// Composite key for SettlementPayment.
@Embeddable
public class SettlementPaymentId {

    // ID of the settlement.
    private UUID settlementId;

    // ID of the payment from the Payment domain.
    private UUID paymentId;
}
