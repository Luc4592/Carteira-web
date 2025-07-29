import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    @Autowired
    private InvestmentRepository investmentRepository;

    @GetMapping
    public List<Investment> getAllInvestments() {
        return investmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investment> getInvestmentById(@PathVariable Long id) {
        return investmentRepository.findById(id)
                .map(investment -> ResponseEntity.ok().body(investment))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Investment createInvestment(@RequestBody Investment investment) {
        return investmentRepository.save(investment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Investment> updateInvestment(@PathVariable Long id, @RequestBody Investment investmentDetails) {
        return investmentRepository.findById(id)
                .map(investment -> {
                    investment.setName(investmentDetails.getName());
                    investment.setAmount(investmentDetails.getAmount());
                    investment.setType(investmentDetails.getType());
                    Investment updatedInvestment = investmentRepository.save(investment);
                    return ResponseEntity.ok().body(updatedInvestment);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
        return investmentRepository.findById(id)
                .map(investment -> {
                    investmentRepository.delete(investment);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}