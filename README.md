# Expense CLI Application
This is a simple command-line interface (CLI) application for managing expenses. You can add, update, delete, list, show summary total and set monthly budget or by month directly from the terminal.

## Features

- **Add an Expense:** Add a new expense with a description and amount.
- **Update an Expense:** Update the description and amount of an existing expense.
- **Delete an Expense:** Remove a expense by its ID.
- **Set a Budget:** Set up budget each moth (optional).
- **List Expenses:** List all expenses.
- **Help:** Show all command available

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/ErickBrayan/Task-Tracker-CLI.git

2. **Change Directory:**
   ```bash
   cd Expense-Tracker/src

3. **Compile the source code:**
    ```bash
   javac expensetracker.java
4. **Run the application:**
    ```bash
   java expensetracker add --description <description> --amount <amount>
   ```

## Usage
```bash
# Adding a new Expense
java expensetracker add --description "Lunch" --amount 20
# Output: Expense added successfully (ID: 1)

# Updating a description Expense
java expensetracker update --id 1 --description "Buy groceries and cook dinner"
# Output: Expense updated successfully (ID: 1)

# Updating a amount Expense
java expensetracker update --id 1 --amount 250.10
# Output: Expense updated successfully (ID: 1)

# Updating a description and amount Expense
java expensetracker update --id 1 --description "Buy groceries and cook dinner" --amount 450.50
# Output: Expense updated successfully (ID: 1)

# Deleting a Expense
java expensetracker delete --id 1
# Output: Expense deleted successfully (ID: 1)

# Total Summary
java expensetracker summary
# Output: Total expenses: $785.30

# Total Summary by Mont
java expensetracker summary --month 8
# Output: Total expenses for August: $20

# Listing all Expenses
java expensetracker list
# Output: List of all expense

# Show command available
java expensetracker help
```

https://roadmap.sh/projects/expense-tracker