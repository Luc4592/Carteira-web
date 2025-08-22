package com.example.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Investment {
    @Id
    private Long id;
    private String name;
    private Double amount;
    private String type;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}