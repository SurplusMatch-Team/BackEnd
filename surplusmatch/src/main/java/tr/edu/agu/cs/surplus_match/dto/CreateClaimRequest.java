package tr.edu.agu.cs.surplus_match.dto;

import jakarta.validation.constraints.NotNull;

/**
 * Carries the data required to create a claim for a product.
 */
public class CreateClaimRequest {

    @NotNull(message = "Claimant id is required.")
    private Long claimantId;

    @NotNull(message = "Product id is required.")
    private Long productId;

    public CreateClaimRequest() {
    }

    public Long getClaimantId() {
        return claimantId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setClaimantId(Long claimantId) {
        this.claimantId = claimantId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}