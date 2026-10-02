package com.seema.razorpayclone.operations.entity;

import jakarta.persistence.*;

/*
* Settlement - Payment Mapping
* A settlement contains multiple payments; one payment can appear in multiple settlements
* This entity acts as the mapping/join table between Settlement and Payment.
* Payment belongs to another domain, so we don't create a direct Java/JPA relationship to a Payment entity here.
* */

@Entity
@Table(name="settlement_payment")
public class SettlementPayment {

    //- Uses composite primary key via @EmbeddedId on SettlementPaymentId
    //  - SettlementPaymentId holds settlementId + paymentId
    @EmbeddedId
    private SettlementPaymentId id;


    //- @MapsId maps settlementId from composite key to Settlement field
    //  - Enables settlementPayment.settlement in Java without extra DB column
    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "settlement_id", nullable = false)
    private Settlement settlement;

    //- Payment is in a separate domain, so no Java reference to Payment entity here

}
