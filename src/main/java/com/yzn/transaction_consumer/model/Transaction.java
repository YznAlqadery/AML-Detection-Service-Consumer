package com.yzn.transaction_consumer.model;

import com.yzn.transaction_consumer.model.enums.PaymentFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime timestamp;

    private String fromBank;
    private String fromAccount;

    private String toAccount;
    private String toBank;

    private Double amountReceived;
    private String receivingCurrency;

    private Double amountPaid;
    private String paymentCurrency;

    @Enumerated(EnumType.STRING)
    private PaymentFormat paymentFormat;

    private Boolean isLaundering;

    public Transaction() {
    }

    public Transaction(String fromBank, String fromAccount, String toAccount, String toBank, Double amountReceived, String receivingCurrency, Double amountPaid, String paymentCurrency, PaymentFormat paymentFormat, Boolean isLaundering) {
        this.fromBank = fromBank;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.toBank = toBank;
        this.amountReceived = amountReceived;
        this.receivingCurrency = receivingCurrency;
        this.amountPaid = amountPaid;
        this.paymentCurrency = paymentCurrency;
        this.paymentFormat = paymentFormat;
        this.isLaundering = isLaundering;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getFromBank() {
        return fromBank;
    }

    public void setFromBank(String fromBank) {
        this.fromBank = fromBank;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getToBank() {
        return toBank;
    }

    public void setToBank(String toBank) {
        this.toBank = toBank;
    }

    public Double getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(Double amountReceived) {
        this.amountReceived = amountReceived;
    }

    public String getReceivingCurrency() {
        return receivingCurrency;
    }

    public void setReceivingCurrency(String receivingCurrency) {
        this.receivingCurrency = receivingCurrency;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public PaymentFormat getPaymentFormat() {
        return paymentFormat;
    }

    public void setPaymentFormat(PaymentFormat paymentFormat) {
        this.paymentFormat = paymentFormat;
    }

    public Boolean getLaundering() {
        return isLaundering;
    }

    public void setLaundering(Boolean laundering) {
        isLaundering = laundering;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", fromBank='" + fromBank + '\'' +
                ", fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", toBank='" + toBank + '\'' +
                ", amountReceived=" + amountReceived +
                ", receivingCurrency='" + receivingCurrency + '\'' +
                ", amountPaid=" + amountPaid +
                ", paymentCurrency='" + paymentCurrency + '\'' +
                ", paymentFormat=" + paymentFormat +
                ", isLaundering=" + isLaundering +
                '}';
    }
}
