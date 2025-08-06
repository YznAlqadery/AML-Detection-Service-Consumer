package com.yzn.transaction_consumer.config;


import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemgraphConfig {

    @Bean
    public Driver memgraphDriver(){
        return  GraphDatabase.driver("bolt://localhost:7687", AuthTokens.none());


    }
}
