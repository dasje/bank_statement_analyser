package com.bank_statement_analyser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser {
    private String delimiter;
    private final DateTimeFormatter DATE_PATTERN;
    
    public BankStatementCSVParser(
        String dateFormat,
        String delimiter
    ) {
        this.delimiter = delimiter;
        this.DATE_PATTERN = DateTimeFormatter.ofPattern(dateFormat);
    }

    private BankTransaction parseFromCSV(final String line) {
        final String[] columns = line.split(this.delimiter);

        final LocalDate date = LocalDate.parse(columns[0], this.DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    public List<BankTransaction> parseLinesFromCSV( final List<String> lines) {
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for(final String line : lines) {
            bankTransactions.add(parseFromCSV(line));
        }
        return bankTransactions;
    }
}
