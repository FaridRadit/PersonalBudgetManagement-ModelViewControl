package budgetmanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BudgetModel {
    private List<Transaction> transactions;

    public BudgetModel() {
        transactions = new ArrayList<>();
        loadTransactionsFromDatabase();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveTransactionToDatabase(transaction);
    }

    public void updateTransaction(int index, Transaction updatedTransaction) {
        transactions.set(index, updatedTransaction);
        updateTransactionInDatabase(updatedTransaction);
    }

    public void deleteTransaction(int index) {
        Transaction deletedTransaction = transactions.remove(index);
        deleteTransactionFromDatabase(deletedTransaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public double getTotalIncome() {
        return transactions.stream()
                .filter(t -> t.getType().equals("Income"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpense() {
        return transactions.stream()
                .filter(t -> t.getType().equals("Expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    private void loadTransactionsFromDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM transactions")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                String category = rs.getString("category");
                String date = rs.getString("date");
                transactions.add(new Transaction(id, type, amount, category, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveTransactionToDatabase(Transaction transaction) {
        String query = "INSERT INTO transactions (type, amount, category, date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, transaction.getType());
            pstmt.setDouble(2, transaction.getAmount());
            pstmt.setString(3, transaction.getCategory());
            pstmt.setString(4, transaction.getDate());
            pstmt.executeUpdate();

            // Retrieve the generated ID and set it in the transaction object
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    transaction.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTransactionInDatabase(Transaction updatedTransaction) {
        String query = "UPDATE transactions SET type=?, amount=?, category=?, date=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, updatedTransaction.getType());
            pstmt.setDouble(2, updatedTransaction.getAmount());
            pstmt.setString(3, updatedTransaction.getCategory());
            pstmt.setString(4, updatedTransaction.getDate());
            pstmt.setInt(5, updatedTransaction.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

       private void deleteTransactionFromDatabase(Transaction transaction) {
        String query = "DELETE FROM transactions WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, transaction.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
