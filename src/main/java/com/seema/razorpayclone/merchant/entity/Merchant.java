package com.seema.razorpayclone.merchant.entity;

import com.seema.razorpayclone.common.enums.BusinessType;
import com.seema.razorpayclone.common.enums.MerchantStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "merchant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,length = 200)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 20)
    private String contactNumber;

    @Column(length = 100)
    private String businessName;

    @Column(length = 50)
    private BusinessType businessType;
    @Column(length = 200)
    private String websiteUrl;

    @Column(length = 200,nullable = false)
    @Builder.Default
    private MerchantStatus status = MerchantStatus.PENDING_KYC;

    @Column(length = 20)
    private String gstId;
    @Column(length = 20)
    private String panId;

    @Column(length = 200)
    private String settlementBankAccount;
    @Column(length = 20)
    private String settlementBankIFSC;
    @Column(length = 200)
    private String settlementBankAccountHolderName;


}
