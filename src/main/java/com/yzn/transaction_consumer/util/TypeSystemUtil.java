package com.yzn.transaction_consumer.util;

// Utility for Neo4j Type checking
public class TypeSystemUtil {
    private static final org.neo4j.driver.types.TypeSystem TYPE_SYSTEM = org.neo4j.driver.types.TypeSystem.getDefault();

    public static org.neo4j.driver.types.Type nodeType() {
        return TYPE_SYSTEM.NODE();
    }

    public static org.neo4j.driver.types.Type relationshipType() {
        return TYPE_SYSTEM.RELATIONSHIP();
    }
}