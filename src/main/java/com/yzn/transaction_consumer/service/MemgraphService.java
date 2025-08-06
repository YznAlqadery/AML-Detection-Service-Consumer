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

    public void saveTransaction(Transaction transaction) {
        if (transaction.getFromAccount() == null || transaction.getToAccount() == null) {
            // Optionally log the bad transaction
            System.err.println("Skipping transaction due to null account numbers: " + transaction.getId());
            return;
        }

        try (Session session = driver.session()) {
            session.run(
                    "MERGE (sender:Account {accountNumber: $fromAccount}) " +
                            "MERGE (receiver:Account {accountNumber: $toAccount}) " +
                            "CREATE (t:Transaction { " +
                            "id: $id, " +
                            "timestamp: $timestamp, " +
                            "fromBank: $fromBank, " +
                            "fromAccount: $fromAccount, " +
                            "toAccount: $toAccount, " +
                            "toBank: $toBank, " +
                            "amountReceived: $amountReceived, " +
                            "receivingCurrency: $receivingCurrency, " +
                            "amountPaid: $amountPaid, " +
                            "paymentCurrency: $paymentCurrency, " +
                            "paymentFormat: $paymentFormat, " +
                            "isLaundering: $isLaundering " +
                            "}) " +
                            "MERGE (sender)-[:SENT]->(t) " +
                            "MERGE (t)-[:RECEIVED_BY]->(receiver)",
                    Values.parameters(
                            "id", transaction.getId(),
                            "timestamp", transaction.getTimestamp() != null ? transaction.getTimestamp().toString() : null,
                            "fromBank", transaction.getFromBank(),
                            "fromAccount", transaction.getFromAccount(),
                            "toAccount", transaction.getToAccount(),
                            "toBank", transaction.getToBank(),
                            "amountReceived", transaction.getAmountReceived(),
                            "receivingCurrency", transaction.getReceivingCurrency(),
                            "amountPaid", transaction.getAmountPaid(),
                            "paymentCurrency", transaction.getPaymentCurrency(),
                            "paymentFormat", transaction.getPaymentFormat() != null ? transaction.getPaymentFormat().toString() : null,
                            "isLaundering", transaction.getLaundering()
                    )
            );
        }
    }

}
