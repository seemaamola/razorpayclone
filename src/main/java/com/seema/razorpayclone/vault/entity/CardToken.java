package com.seema.razorpayclone.vault.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="card_token")
public class CardToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //reference/token that the rest of the payment system can use instead of handling the actual card number (PAN).
    //unique= true  ensures that the same token cannot be assigned to multiple records
    @Column(nullable = false, length = 50, unique = true)
    private String token;

    //A token points to the actual card stored in the Vault.
    //Many tokens can potentially reference the same VaultCard,hence @ManyToOne.
    /*LAZY:
     * The VaultCard is not loaded from the database unless we actually
     * access vaultCard. This avoids unnecessarily loading sensitive
     * card information when we only need the token.*/
    //- one card → many tokens
    //- One card generates separate tokens per merchant (Zara token ≠ H&M token) Enforces merchant data isolation
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vault_card_id",nullable = false)
    private VaultCard vaultCard;

    /* Customer who owns/uses this token.
     * We store the customer's UUID rather than a direct Customer entity
     * relationship because Customer belongs to another part/service of
     * the system.
    */
    @Column(nullable = false)
    private UUID customer;

    /*Merchant for whom this token is associated.
     * Keeping the merchant UUID allows the Vault to associate the token
     * with the merchant without creating a direct dependency on the
     * Merchant entity.
    */
    @Column(nullable = false)
    private UUID merchant;


    /*TOKEN REVOCATION
     * null  -> token is currently active
     * value -> token was revoked at this time
     * We use a timestamp instead of simply deleting the token so that
     * we retain an audit/history of when it was revoked.
     */
    private LocalDateTime revokedAt;


}
