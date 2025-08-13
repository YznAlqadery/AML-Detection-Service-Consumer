package com.yzn.transaction_consumer.service;

import com.yzn.transaction_consumer.model.Motif;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {

    private final Driver driver;
    private final MotifService motifService;

    public TransactionService(Driver driver, MotifService motifService) {
        this.driver = driver;
        this.motifService = motifService;
    }

    public Map<String,Object> getFraudCycles(Integer motifId){
        Motif motif = motifService.getMotifById(motifId);

        if (!motif.isActive()) {
            throw new RuntimeException("Motif is not active");
        }

        Map<String, Map<String,Object>> nodes = new HashMap<>();
        List<Map<String,Object>> relationships = new ArrayList<>();

        try (Session session = driver.session()) {
            var result = session.run(motif.getCypherQuery());

            while (result.hasNext()) {
                var record = result.next();

                // Nodes
                List<String> nodeKeys = List.of("a1","a2","a3","t1","t2","t3");
                for (String key : nodeKeys) {
                    if (!record.containsKey(key)) continue;
                    var node = record.get(key).asNode();
                    String nodeId = String.valueOf(node.id());
                    nodes.putIfAbsent(nodeId, Map.of(
                            "id", nodeId,
                            "labels", node.labels(),
                            "properties", node.asMap()
                    ));
                }

                // Relationships
                List<String> relationKeys = List.of("r1","r2","r3","r4","r5","r6");
                for (String key : relationKeys) {
                    if (!record.containsKey(key)) continue;
                    var relation = record.get(key).asRelationship();
                    String relationId = String.valueOf(relation.id());
                    relationships.add(Map.of(
                            "id", relationId,
                            "type", relation.type(),
                            "startNode", relation.startNodeId(),
                            "endNode", relation.endNodeId(),
                            "properties", relation.asMap()
                    ));
                }
            }

            Map<String,Object> response = new HashMap<>();
            response.put("nodes", nodes.values());
            response.put("relationships", relationships);
            return response;

        } catch (Exception e) {
            throw new RuntimeException("Error querying fraud cycles for motif " + motifId, e);
        }
    }
    public List<Motif> getActiveMotifs() {
        return motifService.getAllMotifs().stream()
                .filter(Motif::isActive)
                .toList();
    }
}

//            var result = session.run("MATCH " +
//                    "(a1:Account)-[r1:SENT]->(t1:Transaction)-[r2:RECEIVED_BY]->(a2:Account), " +
//                    "(a2)-[r3:SENT]->(t2:Transaction)-[r4:RECEIVED_BY]->(a3:Account), " +
//                    "(a3)-[r5:SENT]->(t3:Transaction)-[r6:RECEIVED_BY]->(a1) " +
//                    "WHERE a1 <> a2 AND a2 <> a3 AND a1 <> a3 " +
//                    "RETURN a1, a2, a3, t1, t2, t3, r1, r2, r3, r4, r5,     r6 " +
//                    "LIMIT 5");