package tr.edu.agu.cs.surplus_match.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateClaimRequest {

    @NotNull
    private Long productId;

    @NotNull
    private Long claimantId;

    @NotNull
    @Min(1)
    private Integer requestedQuantity;

    public CreateClaimRequest() {
    }

    public Long getProductId() {
        return productId;
    }

    public Long getClaimantId() {
        return claimantId;
    }

    public Integer getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setClaimantId(Long claimantId) {
        this.claimantId = claimantId;
    }

    public void setRequestedQuantity(Integer requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }
}
