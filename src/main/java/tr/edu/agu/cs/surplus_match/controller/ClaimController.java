package tr.edu.agu.cs.surplus_match.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.agu.cs.surplus_match.model.Claim;
import tr.edu.agu.cs.surplus_match.service.ClaimService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/claims")
@CrossOrigin(origins = "http://localhost:5173")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    // POST /api/claims — create a new claim
    @PostMapping
    public ResponseEntity<?> createClaim(@RequestBody Map<String, Object> request) {
        try {
            Long claimantId = ((Number) request.get("claimantId")).longValue();
            Long productId = ((Number) request.get("productId")).longValue();
            Integer requestedQuantity = request.get("requestedQuantity") != null
                    ? ((Number) request.get("requestedQuantity")).intValue()
                    : null;

            Claim newClaim;
            if (requestedQuantity != null) {
                newClaim = claimService.createClaim(claimantId, productId, requestedQuantity);
            } else {
                newClaim = claimService.createClaim(claimantId, productId);
            }
            return ResponseEntity.ok(newClaim);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PATCH /api/claims/{claimId} — edit a pending claim
    @PatchMapping("/{claimId}")
    public ResponseEntity<?> editClaim(@PathVariable Long claimId, @RequestBody Map<String, Object> request) {
        try {
            Integer newQuantity = ((Number) request.get("requestedQuantity")).intValue();
            Claim updated = claimService.editClaim(claimId, newQuantity);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PATCH /api/claims/{claimId}/withdraw — withdraw a pending claim
    @PatchMapping("/{claimId}/withdraw")
    public ResponseEntity<?> withdrawClaim(@PathVariable Long claimId) {
        try {
            Claim withdrawn = claimService.withdrawClaim(claimId);
            return ResponseEntity.ok(withdrawn);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PATCH /api/claims/{claimId}/approve — market approves a claim
    @PatchMapping("/{claimId}/approve")
    public ResponseEntity<?> approveClaim(@PathVariable Long claimId) {
        try {
            Claim approved = claimService.approveClaim(claimId);
            return ResponseEntity.ok(approved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PATCH /api/claims/{claimId}/reject — market rejects a claim
    @PatchMapping("/{claimId}/reject")
    public ResponseEntity<?> rejectClaim(@PathVariable Long claimId) {
        try {
            Claim rejected = claimService.rejectClaim(claimId);
            return ResponseEntity.ok(rejected);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /api/claims/owner/{ownerId} — claims for market's products
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Claim>> getClaimsByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(claimService.getClaimsByProductOwner(ownerId));
    }

    // GET /api/claims/claimant/{claimantId} — NGO's claims
    @GetMapping("/claimant/{claimantId}")
    public ResponseEntity<List<Claim>> getClaimsByClaimant(@PathVariable Long claimantId) {
        return ResponseEntity.ok(claimService.getClaimsByClaimant(claimantId));
    }
}
