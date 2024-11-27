import controller.ExpenseHandler;
import model.Expense;
import service.ExpensiveService;
import service.ExpensiveServiceImp;


public class expensetracker {
    public static void main(String[] args) {

        ExpensiveService service = new ExpensiveServiceImp();
        ExpenseHandler handler = new ExpenseHandler(service);

        if (args.length == 0) {
            System.out.println(showCommands());
        }
        String msg = "";
        switch (args[0]) {
            case "add":
                msg = handler.addExpense(new Expense(args[2],Double.parseDouble(args[4])));
                System.out.println(msg);
                break;
            case "list":
                header();
                handler.getAllExpenses().forEach(System.out::println);
                break;
            case "summary":
                if (args.length == 3){
                    msg = handler.getSummaryByMonth(Integer.parseInt(args[2]));
                    System.out.println(msg);
                }else {
                    double summary = handler.getSummary();
                    System.out.println("Total expenses: $" + summary);
                }
                break;
            case "delete":
                msg = handler.deleteExpense(Integer.parseInt(args[2]));
                System.out.println(msg);
                break;
            case "update":
                if (args.length == 5){
                    if (args[3].equals("--description")) msg = handler.updateDescription(Integer.parseInt(args[2]), args[4]);
                    if (args[3].equals("--amount")) msg = handler.updateAmount(Integer.parseInt(args[2]), Double.parseDouble(args[4]));
                } else {
                    msg = handler.updateExpense(Integer.parseInt(args[2]), args[4], Double.parseDouble(args[6]));
                }
                System.out.println(msg);
                break;
            case "budget":
                msg = handler.setMonthlyBudget(Double.parseDouble(args[2]));
                System.out.println(msg);
                break;
            case "help":
                System.out.println(showCommands());
                break;
            default:
                System.out.println("Invalid command");
                System.out.println(showCommands());
                break;
        }
    }

    private static void header() {
        System.out.println("            Expense Tracker");
        System.out.println("=====================================");
    }

    private static String showCommands() {
        return """
                My commands:
                                      Command                                                      |                Description
                    expensetracker add --description <description> --amount <amount>               : Add a new expense.
                    expensetracker list                                                            : List expenses.
                    expensetracker summary                                                         : Show summary of expenses.
                    expensetracker summary --month <1-12>                                          : Show summary of expenses by specific month.
                    expensetracker delete --id <id>                                                : Delete an expense.
                    expensetracker update --id <id> --amount <amount>                              : Update amount of expenses.
                    expensetracker update --id <id> --description <description>                    : Update description of expense.
                    expensetracker update --id <id> --description <description> --amount <amount>  : Update amount and description of expenses.
                    expensetracker budget --amount <budget>                                        : Set up monthly budget.
                """;
    }
}