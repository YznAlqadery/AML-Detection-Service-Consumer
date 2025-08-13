package com.yzn.transaction_consumer.service;

import com.yzn.transaction_consumer.model.Motif;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.types.Type;
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

    public Map<String, Object> getFraudCycles(Integer motifId) {
        Motif motif = motifService.getMotifById(motifId);

        if (!motif.isActive()) {
            throw new RuntimeException("Motif is not active");
        }

        Map<String, Map<String, Object>> nodes = new HashMap<>();
        List<Map<String, Object>> relationships = new ArrayList<>();

        try (Session session = driver.session()) {
            var result = session.run(motif.getCypherQuery());

            while (result.hasNext()) {
                var record = result.next();

                for (String key : record.keys()) {
                    var value = record.get(key);

                    // Node
                    if (value != null && value.hasType(TypeSystemUtil.nodeType())) {
                        var node = value.asNode();
                        String nodeId = String.valueOf(node.id());
                        nodes.putIfAbsent(nodeId, Map.of(
                                "id", nodeId,
                                "labels", node.labels(),
                                "properties", node.asMap()
                        ));
                    }

                    // Relationship
                    else if (value != null && value.hasType(TypeSystemUtil.relationshipType())) {
                        var rel = value.asRelationship();
                        String relId = String.valueOf(rel.id());
                        relationships.add(Map.of(
                                "id", relId,
                                "type", rel.type(),
                                "startNode", rel.startNodeId(),
                                "endNode", rel.endNodeId(),
                                "properties", rel.asMap()
                        ));
                    }
                }
            }

            Map<String, Object> response = new HashMap<>();
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

// Utility for Neo4j Type checking
class TypeSystemUtil {
    private static final org.neo4j.driver.types.TypeSystem TYPE_SYSTEM = org.neo4j.driver.types.TypeSystem.getDefault();

    public static org.neo4j.driver.types.Type nodeType() {
        return TYPE_SYSTEM.NODE();
    }

    public static org.neo4j.driver.types.Type relationshipType() {
        return TYPE_SYSTEM.RELATIONSHIP();
    }
}
