package com.seema.razorpayclone.common.enums;

public enum PaymentStatus {
    CREATED,       //Payment object is created; processing has not started yet.
    AUTHORIZING,  //Payment is currently being processed/authorized by the payment processor.
    AUTHORIZED,  // Processor approved the payment.
    CAPTURING,  // Payment is being captured.
    CAPTURED,  // Money has been successfully captured.
    FAILED,   //Payment processing failed.
    CANCELLED,  // Payment was cancelled.
    REFUNDED,  // Full amount was returned to the customer.
    PARTIALLY_REFUNDED,  //Only part of the payment was returned.
    SETTLED,            // Payment has been settled with the merchant.
    AUTH_EXPIRED       //Authorization expired before the payment was captured.

    // Intermediatory States tells us that the payment actually went through a processing step.
    // It also helps with retries, failures, timeouts, and debugging, because we know exactly where the payment stopped.
}