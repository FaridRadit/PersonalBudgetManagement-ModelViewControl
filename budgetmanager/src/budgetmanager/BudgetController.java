package budgetmanager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class BudgetController {
    private BudgetModel model;
    private BudgetView view;

    public BudgetController(BudgetModel model, BudgetView view) {
        this.model = model;
        this.view = view;

        view.addAddTransactionListener(new AddTransactionListener());
        view.addUpdateTransactionListener(new UpdateTransactionListener());
        view.addDeleteTransactionListener(new DeleteTransactionListener());
        view.addViewAllTransactionsListener(new ViewAllTransactionsListener());
        view.addViewBalanceListener(new ViewBalanceListener());
        view.addResetAutoIncrementListener(new ResetAutoIncrementListener());
    }

    class AddTransactionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Transaction newTransaction = view.getTransactionDetails();
            model.addTransaction(newTransaction);
            view.showMessage("Transaction added successfully.");
        }
    }

  class UpdateTransactionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int id = view.getTransactionId();
        Transaction updatedTransaction = view.getTransactionDetails();
        updatedTransaction.setId(id);
        model.updateTransaction(id, updatedTransaction);
        view.showMessage("Transaction updated successfully.");
    }
}

     class DeleteTransactionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = view.getTransactionId();
            model.deleteTransaction(id);
            view.showMessage("Transaction deleted successfully.");
        }
    }

    class ViewAllTransactionsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Transaction> transactions = model.getTransactions();
            view.displayTransactions(transactions);
        }
    }

    class ViewBalanceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double balance = model.getBalance();
            view.displayBalance(balance);
        }
    }
    class ResetAutoIncrementListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset auto increment to 1?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            model.resetAutoIncrement(); // Panggil metode resetAutoIncrement dari BudgetModel
            
        }
    }
}
}
