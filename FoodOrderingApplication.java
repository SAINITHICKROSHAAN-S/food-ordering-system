import java.awt.*;
import java.awt.event.*;

public class FoodOrderingApplication {
    public static void main(String[] args) {
        Frame frame = new Frame("Food Ordering System");

        // Create labels and text fields for menu items
        Label pizzaLabel = new Label("Pizza ($10.0)");
        TextField pizzaQty = new TextField("0", 5);
        
        Label burgerLabel = new Label("Burger ($8.0)");
        TextField burgerQty = new TextField("0", 5);
        
        Label friesLabel = new Label("Fries ($5.0)");
        TextField friesQty = new TextField("0", 5);

        Label sandwichLabel = new Label("Sandwich ($7.0)");
        TextField sandwichQty = new TextField("0", 5);

        Label drinkLabel = new Label("Drink ($2.5)");
        TextField drinkQty = new TextField("0", 5);

        // Create buttons
        Button orderButton = new Button("Place Order");
        Button clearButton = new Button("Clear");

        // Create a panel for the form
        Panel panel = new Panel(new GridLayout(6, 2));
        panel.add(pizzaLabel);
        panel.add(pizzaQty);
        panel.add(burgerLabel);
        panel.add(burgerQty);
        panel.add(friesLabel);
        panel.add(friesQty);
        panel.add(sandwichLabel);
        panel.add(sandwichQty);
        panel.add(drinkLabel);
        panel.add(drinkQty);
        panel.add(orderButton);
        panel.add(clearButton);

        // Action listener for placing order
        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int pizzaCount = Integer.parseInt(pizzaQty.getText());
                    int burgerCount = Integer.parseInt(burgerQty.getText());
                    int friesCount = Integer.parseInt(friesQty.getText());
                    int sandwichCount = Integer.parseInt(sandwichQty.getText());
                    int drinkCount = Integer.parseInt(drinkQty.getText());

                    if (pizzaCount < 0 || burgerCount < 0 || friesCount < 0 || sandwichCount < 0 || drinkCount < 0) {
                        throw new NumberFormatException("Negative value");
                    }

                    double totalAmount = (pizzaCount * 10.0) + (burgerCount * 8.0) + 
                                         (friesCount * 5.0) + (sandwichCount * 7.0) + 
                                         (drinkCount * 2.5);

                    // Prepare the order summary
                    String summary = "Order Summary:\n"
                            + "Pizza: " + pizzaCount + " x $10.0\n"
                            + "Burger: " + burgerCount + " x $8.0\n"
                            + "Fries: " + friesCount + " x $5.0\n"
                            + "Sandwich: " + sandwichCount + " x $7.0\n"
                            + "Drink: " + drinkCount + " x $2.5\n"
                            + "Total: $" + totalAmount;

                    // Display the order summary
                    displayMessage("Order Summary", summary);
                } catch (NumberFormatException ex) {
                    displayMessage("Error", "Please enter valid non-negative quantities.");
                }
            }
        });

        // Action listener for clearing inputs
        clearButton.addActionListener(e -> {
            pizzaQty.setText("0");
            burgerQty.setText("0");
            friesQty.setText("0");
            sandwichQty.setText("0");
            drinkQty.setText("0");
        });

        // Add the panel to the frame
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);

        // Add window closing event to close the application
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
    }

    // Utility method to display a message in a new frame
    private static void displayMessage(String title, String message) {
        Frame messageFrame = new Frame(title);
        messageFrame.setSize(350, 300);
        messageFrame.setLayout(new FlowLayout());

        TextArea messageArea = new TextArea(message, 10, 30, TextArea.SCROLLBARS_VERTICAL_ONLY);
        messageArea.setEditable(false);

        Button closeButton = new Button("Close");
        closeButton.addActionListener(e -> messageFrame.dispose());

        messageFrame.add(messageArea);
        messageFrame.add(closeButton);
        messageFrame.setVisible(true);
    }
}
