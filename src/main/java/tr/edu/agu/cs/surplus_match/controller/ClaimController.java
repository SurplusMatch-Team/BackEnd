package tr.edu.agu.cs.surplus_match.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.agu.cs.surplus_match.dto.CreateClaimRequest;
import tr.edu.agu.cs.surplus_match.model.Claim;
import tr.edu.agu.cs.surplus_match.service.ClaimService;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @PostMapping
    public ResponseEntity<Claim> createClaim(@Valid @RequestBody CreateClaimRequest request) {
        return ResponseEntity.ok(claimService.createClaim(request));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Claim>> getClaimsByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(claimService.getClaimsByProduct(productId));
    }

    @GetMapping("/claimant/{claimantId}")
    public ResponseEntity<List<Claim>> getClaimsByClaimant(@PathVariable Long claimantId) {
        return ResponseEntity.ok(claimService.getClaimsByClaimant(claimantId));
    }

    @PatchMapping("/{claimId}/approve")
    public ResponseEntity<Claim> approveClaim(@PathVariable Long claimId) {
        return ResponseEntity.ok(claimService.approveClaim(claimId));
    }

    @PatchMapping("/{claimId}/reject")
    public ResponseEntity<Claim> rejectClaim(@PathVariable Long claimId) {
        return ResponseEntity.ok(claimService.rejectClaim(claimId));
    }
}
