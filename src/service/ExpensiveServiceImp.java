package service;

import model.Expense;
import model.Month;
import util.FileUtil;

import java.time.LocalDate;
import java.util.List;

public class  ExpensiveServiceImp implements ExpensiveService {

    List<Expense> expenseList = FileUtil.loadData();
    public double monthlyRate = FileUtil.loadBudget();

    @Override
    public String addExpense(Expense expense) {

        double summaryMonthly = getSummary();

        if (monthlyRate == 0){
            expenseList.add(expense);
            FileUtil.saveData(expenseList,monthlyRate);
            return "Expensive added successfully (ID: " + expense.getId() + ")";
        }

        if (monthlyRate != 0 && expense.getAmount() + summaryMonthly <= monthlyRate) {
            if (isNewMonth(expense.getCreatedAt())) {
                expenseList.add(expense);
                FileUtil.saveData(expenseList,monthlyRate);
                monthlyRate = 0;
                return "Expensive added successfully (ID: " + expense.getId() + ") \nIs new month, Can you set up a new budget or omit";
            } else {
                expenseList.add(expense);
                FileUtil.saveData(expenseList,monthlyRate);
                return "Expensive added successfully (ID: " + expense.getId() + ")";
            }
        }else {
            return "Your spending is exceeding your budget";
        }
    }

    @Override
    public String deleteExpense(int id) {
        expenseList.stream()
                .filter(expense -> expense.getId() == id)
                .findFirst()
                .ifPresent(expense -> expenseList.remove(expense));
        FileUtil.saveData(expenseList,monthlyRate);
        return "Expensive deleted successfully (ID: " + id + ")";
    }

    @Override
    public String updateExpense(int id, String description, double amount) {

        expenseList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .ifPresent(e -> {
                    e.setDescription(description);
                    e.setAmount(amount);
                });

        FileUtil.saveData(expenseList,monthlyRate);
        return "Expensive updated successfully (ID: " + id + ")";
    }

    @Override
    public String updateDescription(int id, String description) {

        expenseList.stream()
                .filter(expense -> expense.getId() == id)
                .findFirst()
                .ifPresent(expense -> expense.setDescription(description));

        FileUtil.saveData(expenseList,monthlyRate);
        return "Expensive updated successfully (ID: " + id + ")";
    }

    @Override
    public String updateAmount(int id, double amount) {
        expenseList.stream()
                .filter(expense -> expense.getId() == id)
                .findFirst()
                .ifPresent(expense -> expense.setAmount(amount));

        FileUtil.saveData(expenseList,monthlyRate);
        return "Expensive updated successfully (ID: " + id + ")";
    }

    @Override
    public List<Expense> getAllExpense() {
        return expenseList;
    }

    @Override
    public double getSummary() {
        return expenseList.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    @Override
    public String getSummaryByMonth(int month) {

        double summaryByMonth = expenseList.stream()
                .filter(e -> e.getCreatedAt().getMonthValue() == month)
                .mapToDouble(Expense::getAmount)
                .sum();

        return "Total expenses for "+ Month.getMoth(month) + ": $" + summaryByMonth;
    }

    @Override
    public String setMonthlyBudget(double budget) {
        this.monthlyRate = budget;

        return " Monthly Budget set successfully";
    }

    private boolean isNewMonth(LocalDate date) {
        return date.getDayOfMonth() == 1;
    }
}
