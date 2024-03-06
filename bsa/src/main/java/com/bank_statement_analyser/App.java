package com.bank_statement_analyser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    private static final String RESOURCES = "C:\\Users\\n" + //
            "vcmm\\Documents\\java projects\\bank_statement_analyser\\resources\\statement_summary.csv";

    public static void main( String[] args ) throws IOException {
        final Path path = Paths.get(RESOURCES);
        final List<String> lines = Files.readAllLines(path);
        BankStatementCSVParser csvParser = new BankStatementCSVParser("yyyy-MM-dd", ",");
        List<BankTransaction> transactions = csvParser.parseLinesFromCSV(lines);
        System.out.println(transactions);
    }
    
    public static double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
        double total = 0d;
        for (final BankTransaction bankTransaction: bankTransactions) {
            total += bankTransaction.getAmount();
        }
        return total;
    }

    public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
        final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                bankTransactionsInMonth.add(bankTransaction);
            }
        }
        return bankTransactionsInMonth;
    }
}
