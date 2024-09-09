package org.example;

import org.example.entity.*;

import java.util.Date;
import static org.example.entity.Expenses.deleteExpense;
import static org.example.entity.RecurringExpenses.cancelRecurringExpense;
import static org.example.entity.RecurringExpenses.enableRecurringExpense;


public class Main {
    public static void main(String[] args) throws Exception {
        // Não tem implementação da classe Investiment porque não
        // tem herança e evita código poluído para a entrega dessa atividade.

        User user1 = User.createUser(1L, "Jack", "Test", 30, new Date(), "jack.test@example.com", "mypassword", User.Role.ADMIN);

        user1.displayUserDetails();

        User updatedUser = User.updateUser(1L, "Jack", "Test", 31, new Date(), "jacktest@example.com");

        updatedUser.displayUserDetails();
        User user2 = User.createUser(2L, "Jeff", "Silva", 21, new Date(), "jeffteste@example.com", "testepass", User.Role.USER );

        User retrievedUser = User.getById(1L);
        System.out.println("========== Retrieved User =============== ");
        retrievedUser.displayUserDetails();

        System.out.println("================= all users ====================== ");
        for(User user : User.userStore.values()){
            user.displayUserDetails();
        }


        boolean isDeleted = User.deleteUser(1L);
        System.out.println("User deleted: " + isDeleted);

        User deletedUser = User.getById(1L);
        if (deletedUser == null) {
            System.out.println("User not found.");
        }

        Expenses firstExpense = Expenses.createExpense(101L, "Groceries", new Date(), 50.0, true, 2L);
        Expenses secondExpense = Expenses.createExpense(202L, "Wallmart", new Date(), 510.0, true, 2L);

        RecurringExpenses recurringExpense = RecurringExpenses.createRecurringExpense(
                101L, "Gym Membership", new Date(), 30.0, true, 1L,
                RecurringExpenses.Frequency.MONTHLY, null,  true, "Health and Fitness"
        );

        RecurringExpenses recurringExpense2 = RecurringExpenses.createRecurringExpense(
                202L, "Restaurant membership", new Date(), 40.0, true, 1L,
                RecurringExpenses.Frequency.WEEKLY, null,  true, "Dinner at restaurant"
        );

        System.out.println("========= All Recurring Expenses before cancellation =========");
        for (RecurringExpenses recExp : RecurringExpenses.recExpStore.values()) {
            recExp.displayExpenseDetails();
        }

        cancelRecurringExpense(202L);

        System.out.println("\n========= All Recurring Expenses not canceled after cancellation =========");
        for (RecurringExpenses recExp : RecurringExpenses.recExpStore.values()) {
            if(!recExp.getCanceled())
                recExp.displayExpenseDetails();
        }

        enableRecurringExpense(202L);

        System.out.println("\n========= All Recurring Expenses after re-enabling =========");
        for (RecurringExpenses recExp : RecurringExpenses.recExpStore.values()) {
            recExp.displayExpenseDetails();
        }

        System.out.println("========== all Expense: ==========");
        for(Expenses exp : Expenses.expensesStore.values()){
            exp.displayExpenseDetails();
        }

        System.out.println("========== Updating one-time expense ==========");
        Expenses updatedExpense = Expenses.updateExpense(101L, "Updated Groceries", new Date(), 60.0, false, 2L);
        if (updatedExpense != null) {
            System.out.println("Updated Expense:");
            updatedExpense.displayExpenseDetails();
        } else {
            System.out.println("Expense not found for updating.");
        }

        System.out.println("========== Trying to update non-existent expense ==========");
        Expenses nonExistentExpense = Expenses.updateExpense(999L, "Non-existent", new Date(), 100.0, false, 2L);
        if (nonExistentExpense == null) {
            System.out.println("Non-existent expense not found, cannot update.");
        }

        deleteExpense(101L);

        System.out.println("========== all Expense: ==========");
        for(Expenses exp : Expenses.expensesStore.values()){
            exp.displayExpenseDetails();
        }
    }
}