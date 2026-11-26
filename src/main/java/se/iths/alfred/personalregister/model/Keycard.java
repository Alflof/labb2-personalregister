package se.iths.alfred.personalregister.model;

import java.time.LocalDate;

public class Keycard {

    private Integer keycardId;
    private Integer cardNumber;
    private LocalDate issueDate;
    private LocalDate expiryDate;

    public Keycard(Integer keycardId, Integer cardNumber, LocalDate issueDate, LocalDate expiryDate) {
        this.keycardId = keycardId;
        this.cardNumber = cardNumber;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
    }

    public Integer getKeycardId() {
        return keycardId;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "Keycard{" +
                "keycardId=" + keycardId +
                ", cardNumber=" + cardNumber +
                ", issueDate=" + issueDate +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
