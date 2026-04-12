package tr.edu.agu.cs.surplus_match.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.agu.cs.surplus_match.model.Claim;
import tr.edu.agu.cs.surplus_match.service.ClaimService;

import java.util.Map;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = "*")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping
    public ResponseEntity<Claim> createClaim(@RequestBody Map<String, Long> request) {
        Long claimantId = request.get("claimantId");
        Long productId = request.get("productId");
        
        Claim newClaim = claimService.createClaim(claimantId, productId);
        return ResponseEntity.ok(newClaim);
    }
}