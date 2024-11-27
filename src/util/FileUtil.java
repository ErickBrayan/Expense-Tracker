package util;

import model.Expense;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static final String FILE_NAME = "expense.txt";
    private static final Path FILE_PATH = Paths.get(FILE_NAME);

    public static void saveData(List<Expense> expenses, double budget) {

        StringBuilder st = new StringBuilder();

        for (Expense expense : expenses) {
            st.append(expense.toCsvString());
        }
        st.append("****");
        st.append("\n");
        st.append(budget);

        try {
            if (Files.notExists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            }
            Files.writeString(FILE_PATH, st.toString(), StandardOpenOption.TRUNCATE_EXISTING);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public static List<Expense> loadData() {
        List<Expense> expenses = new ArrayList<>();


        if (Files.notExists(FILE_PATH)){
            return new ArrayList<>();
        }

        try{

            String res = Files.readString(FILE_PATH);

            String[] form = res.replace(" ","").strip().split("\n");

            for (String s : form) {
                if (s.startsWith("*")) break;
                expenses.add(Expense.fromCsvString(s));
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        return expenses;
    }

    public static double loadBudget() {
        double budget = 0;
        try {
            if (Files.notExists(FILE_PATH)){
                return budget;
            }

            String res = Files.readString(FILE_PATH);
            String[] form = res.replace(" ","").strip().split("\n");
            budget = Double.parseDouble(form[form.length - 1]);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return budget;
    }

}
