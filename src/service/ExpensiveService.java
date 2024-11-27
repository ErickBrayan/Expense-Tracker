package service;

import model.Expense;

import java.util.List;

public interface ExpensiveService {

    String addExpense(Expense expense);
    String deleteExpense(int id);
    String updateExpense(int id, String description, double amount);
    String updateDescription(int id, String description);
    String updateAmount(int id, double amount);
    List<Expense> getAllExpense();
    double getSummary();
    String getSummaryByMonth(int month);
    String setMonthlyBudget(double budget);
}
