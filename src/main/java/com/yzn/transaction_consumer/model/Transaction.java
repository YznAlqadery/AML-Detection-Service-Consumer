package com.yzn.transaction_consumer.model;

import com.yzn.transaction_consumer.model.enums.LaunderingType;
import com.yzn.transaction_consumer.model.enums.PaymentType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Transaction {

    private Integer id;

    private LocalTime time;

    private LocalDate date;

    private Long senderAccount;

    private Long receiverAccount;

    private Double amount;

    private String paymentCurrency;

    private String receivedCurrency;

    private String senderBankLocation;

    private String receiverBankLocation;

    private PaymentType paymentType;

    private Integer isLaundering;

    private LaunderingType launderingType;

    private LocalDateTime createdAt;

    public Transaction() {
    }

    public Transaction(LocalTime time, LocalDate date, Long senderAccount, Long receiverAccount,
                       Double amount, String paymentCurrency, String receivedCurrency,
                       String senderBankLocation, String receiverBankLocation,
                       PaymentType paymentType, Integer isLaundering, LaunderingType launderingType) {
        this.time = time;
        this.date = date;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        this.paymentCurrency = paymentCurrency;
        this.receivedCurrency = receivedCurrency;
        this.senderBankLocation = senderBankLocation;
        this.receiverBankLocation = receiverBankLocation;
        this.paymentType = paymentType;
        this.isLaundering = isLaundering;
        this.launderingType = launderingType;
    }

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Long senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Long getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Long receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public String getReceivedCurrency() {
        return receivedCurrency;
    }

    public void setReceivedCurrency(String receivedCurrency) {
        this.receivedCurrency = receivedCurrency;
    }

    public String getSenderBankLocation() {
        return senderBankLocation;
    }

    public void setSenderBankLocation(String senderBankLocation) {
        this.senderBankLocation = senderBankLocation;
    }

    public String getReceiverBankLocation() {
        return receiverBankLocation;
    }

    public void setReceiverBankLocation(String receiverBankLocation) {
        this.receiverBankLocation = receiverBankLocation;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getIsLaundering() {
        return isLaundering;
    }

    public void setIsLaundering(Integer isLaundering) {
        this.isLaundering = isLaundering;
    }

    public LaunderingType getLaunderingType() {
        return launderingType;
    }

    public void setLaunderingType(LaunderingType launderingType) {
        this.launderingType = launderingType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", time=" + time +
                ", date=" + date +
                ", senderAccount=" + senderAccount +
                ", receiverAccount=" + receiverAccount +
                ", amount=" + amount +
                ", paymentCurrency='" + paymentCurrency + '\'' +
                ", receivedCurrency='" + receivedCurrency + '\'' +
                ", senderBankLocation='" + senderBankLocation + '\'' +
                ", receiverBankLocation='" + receiverBankLocation + '\'' +
                ", paymentType=" + paymentType +
                ", isLaundering=" + isLaundering +
                ", launderingType=" + launderingType +
                ", createdAt=" + createdAt +
                '}';
    }
}
