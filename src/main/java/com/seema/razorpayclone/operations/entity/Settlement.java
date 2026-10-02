package com.seema.razorpayclone.operations.entity;

import com.seema.razorpayclone.common.entity.Money;
import com.seema.razorpayclone.common.enums.SettlementStatus;
import jakarta.persistence.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.UUID;

// Settlement runs periodically and transfers captured payment funds to merchants.

@Entity
@Table(name = "settlement")
public class Settlement {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //Merchant receiving the settlement.
    private UUID merchantId;


    /*
    * Money is an @Embedable Object with two fields :amountUnits and currency
    * @Embedded tells JPA to store these fields directly in the settlement table rather than creating a separate Money table.
    * Because Money is embedded multiple time in this entity,
    * Hibernate would otherwise try to use the same column names (amountUnits and currency) for every Money object
    * @AttributeOverrides solves this by giving every Money instance its own database column names.*/
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amountUnits", column = @Column(name = "gross_amount_units")),
            @AttributeOverride(name = "currency", column = @Column(name = "gross_amount_currency"))
    })
    private Money grossAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amountUnits", column = @Column(name = "refund_amount_units")),
            @AttributeOverride(name = "currency", column = @Column(name = "refund_amount_currency"))
    })
    private  Money refundAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amountUnits", column = @Column(name = "gst_amount_units")),
            @AttributeOverride(name = "currency", column = @Column(name = "gst_amount_currency"))
    })
    private Money gstAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amountUnits", column = @Column(name = "fee_amount_units")),
            @AttributeOverride(name = "currency", column = @Column(name = "fee_amount_currency"))
    })
    private Money feeAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amountUnits", column = @Column(name = "net_amount_units")),
            @AttributeOverride(name = "currency", column = @Column(name = "net_amount_currency"))
    })
    private Money netAmount;

    // Store enum as text instead of ordinal number.
    // EnumType.STRING is safer because changing the order of enum
    // constants will not change the meaning of existing database rows.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SettlementStatus status;

    // Bank reference returned by the bank after settlement processing, used for settlement reconciliation.
    @Column(nullable = false, length = 50)
    private String bankReference;

    // Time when settlement was processed.
    // null -> not processed yet, value -> settlement has been processed at this time
    private LocalDateTime processAt;

}
