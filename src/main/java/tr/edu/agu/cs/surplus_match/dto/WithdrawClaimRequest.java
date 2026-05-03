package tr.edu.agu.cs.surplus_match.dto;

import jakarta.validation.constraints.NotNull;

public class WithdrawClaimRequest {

    @NotNull
    private Long claimantId;

    public Long getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(Long claimantId) {
        this.claimantId = claimantId;
    }
}
