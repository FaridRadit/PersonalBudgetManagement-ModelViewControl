package budgetmanager;

import javax.swing.SwingUtilities;

public class Budgetmanager {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
         BudgetModel model = new BudgetModel();
        BudgetView view = new BudgetView();
        BudgetController controller = new BudgetController(model, view);
         view.setVisible(true);
 

            }
        });
    }
}
