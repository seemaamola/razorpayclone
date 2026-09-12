package com.seema.razorpayclone.common.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Money {

    private int amountUnits;
    private String currency;

    // All arg Constructor
    private Money(int amountUnits,String currency)
    {
        this.amountUnits = amountUnits;
        this.currency = currency;
    }

    // no arg constructor to create the Money object itself when it
    // reads an Order or another entity from the database
    protected Money() {
    }

    //creates a Money object with whatever currency you provide
    public Money of(int amountUnits, String currency)
    {
        return new Money(amountUnits,currency);
    }
    // creates a Money object with currency as "INR".
    public Money inr(int amountUnits)
    {
        return new Money(amountUnits,"INR");
    }

    //add money
    public Money add(Money other)
    {
        if (!this.currency.equals(other.currency))
        {
            throw new IllegalArgumentException("Cannot add money with different currencies");
        }
        return new Money(this.amountUnits+other.amountUnits,this.currency);
    }

    //subtract money
    public Money subtract(Money other)
    {
        if (!this.currency.equals(other.currency))
        {
            throw new IllegalArgumentException("Cannot subtract Money with different currencies");
        }
        return new Money(this.amountUnits-other.amountUnits,this.currency);
    }

}
