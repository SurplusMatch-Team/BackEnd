package tr.edu.agu.cs.surplus_match.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PatchClaimRequest {

    @NotNull
    private Long claimantId;

    @NotNull
    @Min(1)
    private Integer requestedQuantity;

    public Long getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(Long claimantId) {
        this.claimantId = claimantId;
    }

    public Integer getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(Integer requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }
}
