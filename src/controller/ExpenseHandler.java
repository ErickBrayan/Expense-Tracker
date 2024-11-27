package controller;

import model.Expense;
import service.ExpensiveService;

import java.util.List;

public class ExpenseHandler {

    private final ExpensiveService service;

    public ExpenseHandler(ExpensiveService service) {
        this.service = service;
    }

    public String addExpense(Expense expense) {
        return service.addExpense(expense);
    }

    public String deleteExpense(int id) {
        return service.deleteExpense(id);
    }

    public String updateExpense(int id, String description, double amount) {
        return service.updateExpense(id, description, amount);
    }

    public String updateDescription(int id, String description) { return service.updateDescription(id, description); }

    public String updateAmount(int id, double amount) { return service.updateAmount(id, amount); }

    public List<Expense> getAllExpenses() {
        return service.getAllExpense();
    }

    public double getSummary() {
        return service.getSummary();
    }

    public String getSummaryByMonth(int month) {
        return service.getSummaryByMonth(month);
    }

    public String setMonthlyBudget(double budget) {
        return service.setMonthlyBudget(budget);
    }
}
