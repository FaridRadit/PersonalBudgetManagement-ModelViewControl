package budgetmanager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class BudgetView extends JFrame {
    private JComboBox<String> typeField;
    private JTextField amountField;
    private JTextField categoryField;
    private JTextField dateField;
    private JTextField idField;
    private JButton addButton;
    private JButton resetAutoIncrementButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewAllButton;
    private JButton viewBalanceButton;
    private JTable table;
    private DefaultTableModel tableModel;

    public BudgetView() {
        setTitle("Budget Manager");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 1));  // Changed to a single column layout

        formPanel.add(new JLabel("Type (Income/Expense):"));
        typeField = new JComboBox<>(new String[]{"Income", "Expense"});
        formPanel.add(typeField);

        formPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        formPanel.add(amountField);

        formPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        formPanel.add(categoryField);

        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        formPanel.add(dateField);

        formPanel.add(new JLabel("Transaction ID (for update/delete):"));
        idField = new JTextField();
        formPanel.add(idField);

        addButton = new JButton("Add Transaction");
        formPanel.add(addButton);

        updateButton = new JButton("Update Transaction");
        formPanel.add(updateButton);

        deleteButton = new JButton("Delete Transaction");
        formPanel.add(deleteButton);

        viewAllButton = new JButton("View All Transactions");
        formPanel.add(viewAllButton);

        viewBalanceButton = new JButton("View Balance");
        formPanel.add(viewBalanceButton);
        resetAutoIncrementButton = new JButton("Reset Auto Increment");
        formPanel.add(resetAutoIncrementButton);
        add(formPanel, BorderLayout.WEST);  // Placing form panel on the left
        

        // Table panel
        tableModel = new DefaultTableModel(new String[]{"ID", "Type", "Amount", "Category", "Date"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    public Transaction getTransactionDetails() {
        String type = (String) typeField.getSelectedItem();
        double amount = Double.parseDouble(amountField.getText());
        String category = categoryField.getText();
        String date = dateField.getText();
        return new Transaction(type, amount, category, date);
    }

    public int getTransactionId() {
        return Integer.parseInt(idField.getText());
    }

    public void setTransactionDetails(Transaction transaction) {
        typeField.setSelectedItem(transaction.getType());
        amountField.setText(String.valueOf(transaction.getAmount()));
        categoryField.setText(transaction.getCategory());
        dateField.setText(transaction.getDate());
        idField.setText(String.valueOf(transaction.getId()));
    }

    public void displayTransactions(List<Transaction> transactions) {
        tableModel.setRowCount(0);
        for (Transaction transaction : transactions) {
            tableModel.addRow(new Object[]{transaction.getId(), transaction.getType(), transaction.getAmount(), transaction.getCategory(), transaction.getDate()});
        }
    }

    public void displayBalance(double balance) {
        JOptionPane.showMessageDialog(this, "Balance: " + balance);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void addAddTransactionListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addUpdateTransactionListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addDeleteTransactionListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addViewAllTransactionsListener(ActionListener listener) {
        viewAllButton.addActionListener(listener);
    }

    public void addViewBalanceListener(ActionListener listener) {
        viewBalanceButton.addActionListener(listener);
    }
        public void addResetAutoIncrementListener(ActionListener listener) {
        resetAutoIncrementButton.addActionListener(listener); // Tambahkan listener untuk tombol reset auto increment
    }
}
