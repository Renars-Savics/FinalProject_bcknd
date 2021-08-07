package com.company.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class AccountDTO {

    private long id;

    @Size(min = 21, max = 21, message = "Konta numuram jāsastāv no 21 simboliem! ") //validācija
    private String accountNumber;

    @Pattern(regexp = "^(?!IRR|NKW|irr|mkw).*$", message = "Not allowed currencies: IRR, NKW!")
    @Size (min = 3, max = 3)
    private String currency;

    private double ballance;

    private int status;

    @JsonManagedReference //Lai nebūtu circular refference
    private List<CardDTO> cardDTO;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBallance() {
        return ballance;
    }

    public void setBallance(double ballance) {
        this.ballance = ballance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CardDTO> getCardDTO() {
        return cardDTO;
    }

    public void setCardDTO(List<CardDTO> cardDTO) {
        this.cardDTO = cardDTO;
    }
}

