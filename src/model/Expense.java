package model;

import java.time.LocalDate;

public class Expense {

    private static int lastId = 0;
    private int id;
    private String description;
    private double amount;
    private LocalDate createdAt;

    public Expense(int id, String description, double amount, LocalDate createdAt) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public Expense(String description, double amount) {
        this.id = ++lastId;
        this.description = description;
        this.amount = amount;
        this.createdAt = LocalDate.now();
    }

    public int getId() {
        return id;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return  "ID:          " + id + "\n" +
                "Description: " + description + "\n" +
                "Amount:      " + amount + "\n" +
                "Date:        " + createdAt + "\n" +
                "-----------------------------------";
    }

    public String toCsvString() {
        return "id=" + id + ", description=" + description + ", amount=" + amount + ", createdAt=" + createdAt + "\n";
    }

    public static Expense fromCsvString(String csvString) {

        int id = Integer.parseInt(csvString.split(",")[0].split("=")[1]);
        String description = csvString.split(",")[1].split("=")[1];
        double amount = Double.parseDouble(csvString.split(",")[2].split("=")[1]);
        LocalDate date = LocalDate.parse(csvString.split(",")[3].split("=")[1]);

        if (id > lastId) {
            lastId = id;
        }

        return new Expense(id,description,amount,date);
    }
}
