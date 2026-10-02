package com.seema.razorpayclone.vault.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity  // @Entity tells JPA that this class is mapped to a database table.
@Table(name ="vault_card")
public class VaultCard {

    //- Vault is a separate service to limit PCI DSS compliance scope
    //  - Auditors only inspect Vault; rest of system uses tokens, never raw card data

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //We use UUID so the database ID isn't a predictable sequential value.
    private UUID id;

    //Last 4 digits are safe to display to the customer.
    @Column(length = 4, nullable = false)
    private String lastFour;

    // Bank Identification Number - first 6 digits of the card.
    // Used for identifying card network/type, not for displaying the full card.
    @Column(nullable = false,length = 6)
    private String bin; // first 6 digit

    //The raw PAN should not be stored directly in the database.
    //Instead, it is encrypted before persistence.
    @Column(nullable = false)
    private byte[] encryptedPan;

    //DEK = Data Encryption Key
    // The DEK is used to encrypt/decrypt the PAN.- Random string generated per card
    // The DEK itself should be encrypted/wrapped using a separate key-encryption key (KEK) or managed by a key-management system.
    //- Master key lives in environment variables only, never in DB
    //- Decryption: master key → decrypt DEK → use DEK → decrypt PAN
    @Column(nullable = false)
    private byte[] encryptedDek; // secret to encrypt the pan generated using randomizer

    @Column(nullable = false)
    private String brand; // Network/Brand of the card - VISA , RUPAY

    @Column(nullable = false)
    private String expiryMonth;

    @Column(nullable = false)
    private String expiryYear;

    @Column(nullable = false)
    private String cardHolderName;

    private LocalDateTime deletedAt;

}
