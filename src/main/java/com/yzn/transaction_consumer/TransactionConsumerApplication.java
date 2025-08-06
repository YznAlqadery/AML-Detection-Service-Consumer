package com.yzn.transaction_consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TransactionConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionConsumerApplication.class, args);
	}

}
