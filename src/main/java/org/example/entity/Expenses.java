package org.example.entity;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Expenses {
    private Long id;
    private String description;
    private Date payment_date;
    private Double values;
    private Boolean statusPayment;
    private Long userId;

    public static final Map<Long, Expenses> expensesStore = new HashMap<>();

    public Expenses(Long id, String description, Date payment_date, Double values, Boolean statusPayment, Long userId) {
        this.id = id;
        this.description = description;
        this.payment_date = payment_date;
        this.values = values;
        this.statusPayment = statusPayment;
        this.userId = userId;
    }

    public Long getId() {
       return this.id;
    }

    public void setId(Long id) {
        this.userId = id;
    }

    public Double getvalues() {
        return this.values;
    }

    public void setvalues(Double values) {
        this.values = values;
    }

    public String getDescription() {
       return this.description;
    }

    public void setDescription(String description) {
       this.description = description;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public Double getValues() {
        return values;
    }

    public void setValues(Double values) {
        this.values = values;
    }

    public Boolean getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(Boolean statusPayment) {
        this.statusPayment = statusPayment;
    }


    public static Expenses createExpense(Long id, String description, Date payment_date, Double value, Boolean status_payment, Long userId)
    {
        Expenses newExp = new Expenses(id, description,payment_date,value,status_payment,userId);
        expensesStore.put(id, newExp);
        return newExp;
    }

    public static Expenses getById(Long id) {
        return expensesStore.get(id);
    }

    public static Expenses updateExpense(Long id, String description, Date payment_date, Double value, Boolean status_payment, Long userId) {
        Expenses exp = getById(id);
        if (exp != null) {
            exp.setValues(value);
            exp.setDescription(description);
            exp.setPayment_date(payment_date);
            exp.setStatusPayment(status_payment);
            exp.setUserId(userId);
            return exp;
        } else {
            System.out.println("Expense with ID " + id + " not found.");
            return null;
        }
    }


    public static void deleteExpense(Long id) throws Exception {
        Expenses exp = getById(id);
        if (exp != null) {
            expensesStore.remove(id);
            System.out.println("Expense with ID " + id + " deleted.");
        } else {
            throw new Exception("Expense not found");
        }
    }
    public void displayExpenseDetails() {
        System.out.println("Description: " + this.getDescription());
        System.out.println("Values: " + this.getvalues());
        System.out.println("UserId: " + userId);
        System.out.println("Status payment: " + this.getStatusPayment());
        System.out.println("Payment Date: " + this.getPayment_date());
        System.out.println("ID: " + id);
    }

}
