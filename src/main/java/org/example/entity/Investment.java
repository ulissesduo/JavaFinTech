package org.example.entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Investment {
    private Long id;
    private InvestmentType investmentType;
    private Double amount;
    private LocalDate dateInvestment;
    private Long userId;

    private static final Map<Long, Investment> investmentStore = new HashMap<>();

    public Investment(Long id, Double amount, LocalDate dateInvestment, Long userId, InvestmentType investmentType) {
        this.id = id;
        this.amount = amount;
        this.dateInvestment = dateInvestment;
        this.userId = userId;
        this.investmentType = investmentType;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDateInvestment() {
        return dateInvestment;
    }

    public void setDateInvestment(LocalDate dateInvestment) {
        this.dateInvestment = dateInvestment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public InvestmentType getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(InvestmentType investmentType) {
        this.investmentType = investmentType;
    }


    public static Investment createInvestment(Long id, Double amount, LocalDate dateInvestment, Long userId, InvestmentType investmentType) {
        Investment investment = new Investment(id, amount, dateInvestment, userId, investmentType);
        investmentStore.put(id, investment);
        return investment;
    }

    public static Investment getById(Long id) {
        return investmentStore.get(id);
    }


    public static Investment updateInvestment(Long id, Double amount, LocalDate dateInvestment, Long userId, InvestmentType investmentType) {
        Investment investment = investmentStore.get(id);
        if (investment != null) {
            investment.setAmount(amount);
            investment.setDateInvestment(dateInvestment);
            investment.setUserId(userId);
            investment.setInvestmentType(investmentType);
        }
        return investment;
    }


    public static boolean deleteInvestment(Long id) {
        if (investmentStore.containsKey(id)) {
            investmentStore.remove(id);
            return true;
        }
        return false;
    }


    public void displayInvestmentDetails() {
        System.out.println("Investment ID: " + id);
        System.out.println("Amount: " + amount);
        System.out.println("Date of Investment: " + dateInvestment);
        System.out.println("User ID: " + userId);
        System.out.println("Investment Type: " + (investmentType != null ? investmentType.getTypeName() : "None"));
    }
}
