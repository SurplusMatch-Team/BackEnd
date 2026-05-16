package tr.edu.agu.cs.surplus_match.model;

/**
 * Enum for Claim status
 * PENDING: Claim is awaiting approval
 * APPROVED: Claim has been approved
 * REJECTED: Claim has been rejected
 * WITHDRAWN: Claim was withdrawn by the NGO
 */
public enum ClaimStatus {
    PENDING,
    APPROVED,
    REJECTED,
    WITHDRAWN
}
