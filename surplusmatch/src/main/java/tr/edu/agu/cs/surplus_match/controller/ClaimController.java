package tr.edu.agu.cs.surplus_match.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.agu.cs.surplus_match.dto.CreateClaimRequest;
import tr.edu.agu.cs.surplus_match.model.Claim;
import tr.edu.agu.cs.surplus_match.service.ClaimService;

import java.util.List;

/**
 * Handles claim-related endpoints.
 */
@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    /**
     * Creates a new claim for a product.
     *
     * @param request the claim creation request body
     * @return the created claim
     */
    @PostMapping
    public ResponseEntity<Claim> createClaim( @Valid @RequestBody CreateClaimRequest request) {
        Claim savedClaim = claimService.createClaim(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClaim);
    }

    /**
     * Returns all claims created by a specific claimant.
     *
     * @param claimantId the claimant user id
     * @return list of claims
     */
    @GetMapping("/claimant/{claimantId}")
    public ResponseEntity<List<Claim>> getClaimsByClaimant(@PathVariable Long claimantId) {
        List<Claim> claims = claimService.getClaimsByClaimant(claimantId);
        return ResponseEntity.ok(claims);
    }

    /**
     * Returns all claims created for a specific product.
     *
     * @param productId the product id
     * @return list of claims
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Claim>> getClaimsByProduct(@PathVariable Long productId) {
        List<Claim> claims = claimService.getClaimsByProduct(productId);
        return ResponseEntity.ok(claims);
    }
}