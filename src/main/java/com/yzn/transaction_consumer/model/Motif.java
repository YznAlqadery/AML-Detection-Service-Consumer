package com.yzn.transaction_consumer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "motifs")
public class Motif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description", nullable = false,columnDefinition = "MEDIUMTEXT")
    private String description;

    @Column(name = "cypher_query", columnDefinition = "MEDIUMTEXT ")
    private String cypherQuery;

    @Column(name = "is_active")
    private boolean isActive;

    public Motif() {
    }

    public Motif(String name,String description, String cypherQuery, boolean isActive) {
        this.name = name;
        this.description = description;
        this.cypherQuery = cypherQuery;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCypherQuery() {
        return cypherQuery;
    }

    public void setCypherQuery(String cypherQuery) {
        this.cypherQuery = cypherQuery;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Motif{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cypherQuery='" + cypherQuery + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
