package com.yzn.transaction_consumer.kafka;


import com.yzn.transaction_consumer.model.Transaction;
import com.yzn.transaction_consumer.service.MemgraphService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class TransactionConsumer {

    private final MemgraphService memgraphService;

    public TransactionConsumer(MemgraphService memgraphService) {
        this.memgraphService = memgraphService;
    }



    @KafkaListener(topics = "transaction-topic", groupId = "transaction-group")
    public void consumeTransaction(Transaction transaction) {
            System.out.println("Consumed: " + transaction);
            memgraphService.saveTransaction(transaction);

    }

}
