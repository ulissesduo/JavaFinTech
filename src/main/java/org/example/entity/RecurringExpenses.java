package org.example.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RecurringExpenses extends Expenses {
    private Date endDate;
    private Date nextPaymentDate;
    private Boolean autoPay;
    private String recurringNote;
    private Boolean IsCanceled;

    public enum Frequency{
        WEEKLY, MONTHLY, ANNUALLY
    }
    private Frequency frequency;

    public Boolean getCanceled() {
        return IsCanceled;
    }

    public void setCanceled(Boolean canceled) {
        IsCanceled = canceled;
    }

    public static final Map<Long, RecurringExpenses> recExpStore = new HashMap<>();

    public RecurringExpenses(Long id, String description, Date payment_date, Double values, Boolean statusPayment, Long userId,
                             Frequency frequency, Date endDate, Boolean autoPay, String recurringNote) {
        super(id, description, payment_date, values, statusPayment, userId);
        this.frequency = frequency;
        this.endDate = endDate;
        this.autoPay = autoPay;
        this.recurringNote = recurringNote;
        this.nextPaymentDate = calculateNextPaymentDate(payment_date);
        this.IsCanceled= false;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setNextPaymentDate(Date nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    public Boolean getAutoPay() {
        return autoPay;
    }

    public void setAutoPay(Boolean autoPay) {
        this.autoPay = autoPay;
    }

    public String getRecurringNote() {
        return recurringNote;
    }

    public void setRecurringNote(String recurringNote) {
        this.recurringNote = recurringNote;
    }

    public static RecurringExpenses createRecurringExpense(Long id, String description, Date payment_date, Double values, Boolean statusPayment, Long userId,
                                                           Frequency frequency, Date endDate, Boolean autoPay, String recurringNote) {
        RecurringExpenses newExpense = new RecurringExpenses(id, description, payment_date, values, statusPayment, userId,
                frequency, endDate,  autoPay, recurringNote);
        recExpStore.put(id, newExpense);
        return newExpense;
    }

    public static boolean deleteRecExp(Long id) {
        if (recExpStore.containsKey(id)) {
            recExpStore.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public static void cancelRecurringExpense(Long id) {
        RecurringExpenses recExp = recExpStore.get(id);

        if (recExp != null) {
            recExp.setEndDate(new Date());
            recExp.setAutoPay(false);
            recExp.setNextPaymentDate(null);
            recExp.setCanceled(true);
            System.out.println("Recurring expense with ID " + id + " cancelled.");
        } else {
            System.out.println("Recurring expense with ID " + id + " not found.");
        }
    }

    public static void enableRecurringExpense(Long id){
        RecurringExpenses recExp = recExpStore.get(id);
        if (recExp != null && recExp.getCanceled()) {
            recExp.setEndDate(null);
            recExp.setAutoPay(true);
            Date currentDate = new Date();
            recExp.setNextPaymentDate(recExp.calculateNextPaymentDate(currentDate));
            recExp.setCanceled(false);

            System.out.println("Recurring expense with ID " + id + " enabled.");
        } else {
            System.out.println("Recurring expense with ID " + id + " not found.");
        }
    }

    public Date calculateNextPaymentDate(Date paymentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paymentDate);

        switch (frequency){
            case WEEKLY:
                calendar.add(Calendar.WEEK_OF_YEAR, 1);
                break;
            case MONTHLY:
                calendar.add(Calendar.MONTH, 1);
                break;
            case ANNUALLY:
                calendar.add(Calendar.YEAR, 1);
                break;

            default:
                throw new IllegalArgumentException("Invalid frequency: " + frequency);
        }
        return calendar.getTime();
    }

    @Override
    public void displayExpenseDetails() {
        super.displayExpenseDetails();
        System.out.println("Frequency: " + frequency);
        System.out.println("End Date: " + endDate);
        System.out.println("Next Payment Date: " + nextPaymentDate);
        System.out.println("Auto Pay: " + autoPay);
        System.out.println("Recurring Note: " + recurringNote);
        System.out.println("Canceled: " + IsCanceled);
        System.out.println("id: " + getId());
        System.out.println(" ======================================= ");
    }
}
