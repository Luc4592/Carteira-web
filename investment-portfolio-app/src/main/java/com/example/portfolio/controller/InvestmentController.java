package com.example.portfolio.controller;

import com.example.portfolio.model.Investment;
import com.example.portfolio.repository.InvestmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    private final InvestmentRepository repository;

    public InvestmentController(InvestmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Investment> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investment> getInvestmentById(@PathVariable Long id) {
        return repository.findById(id)
                .map(investment -> ResponseEntity.ok().body(investment))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Investment createInvestment(@RequestBody Investment investment) {
        return repository.save(investment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Investment> updateInvestment(@PathVariable Long id, @RequestBody Investment investmentDetails) {
        return repository.findById(id)
                .map(investment -> {
                    investment.setName(investmentDetails.getName());
                    investment.setAmount(investmentDetails.getAmount());
                    investment.setType(investmentDetails.getType());
                    Investment updatedInvestment = repository.save(investment);
                    return ResponseEntity.ok().body(updatedInvestment);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvestment(@PathVariable Long id) {
        return repository.findById(id)
                .map(investment -> {
                    repository.delete(investment);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }
}