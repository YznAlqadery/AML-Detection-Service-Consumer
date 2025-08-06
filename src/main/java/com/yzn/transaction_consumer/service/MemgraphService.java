package com.yzn.transaction_consumer.service;


import com.yzn.transaction_consumer.model.Transaction;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;
import org.springframework.stereotype.Service;

@Service
public class MemgraphService {

    private final Driver driver;


    public MemgraphService(Driver driver) {
        this.driver = driver;
    }

    public void saveTransaction(Transaction transaction){
        try (Session session = driver.session()) {
            session.run(
                    "CREATE (t:Transaction { " +
                            "id: $id, " +
                            "time: $time, " +
                            "senderAccount: $senderAccount, " +
                            "receiverAccount: $receiverAccount, " +
                            "amount: $amount, " +
                            "paymentCurrency: $paymentCurrency, " +
                            "receivedCurrency: $receivedCurrency, " +
                            "senderBankLocation: $senderBankLocation, " +
                            "receiverBankLocation: $receiverBankLocation, " +
                            "paymentType: $paymentType, " +
                            "isLaundering: $isLaundering, " +
                            "launderingType: $launderingType, " +
                            "createdAt: $createdAt " +
                            "})",
                    Values.parameters(
                            "id", transaction.getId(),
                            "time", transaction.getTime() != null ? transaction.getTime().toString() : null,
                            "senderAccount", transaction.getSenderAccount(),
                            "receiverAccount", transaction.getReceiverAccount(),
                            "amount", transaction.getAmount(),
                            "paymentCurrency", transaction.getPaymentCurrency(),
                            "receivedCurrency", transaction.getReceivedCurrency(),
                            "senderBankLocation", transaction.getSenderBankLocation(),
                            "receiverBankLocation", transaction.getReceiverBankLocation(),
                            "paymentType", transaction.getPaymentType() != null ? transaction.getPaymentType().toString() : null,
                            "isLaundering", transaction.getIsLaundering(),
                            "launderingType", transaction.getLaunderingType() != null ? transaction.getLaunderingType().toString() : null,
                            "createdAt", transaction.getCreatedAt() != null ? transaction.getCreatedAt().toString() : null
                    )
            );
        }
    }
}
