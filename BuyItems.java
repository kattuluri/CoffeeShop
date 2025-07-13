import java.util.Scanner;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class BuyItems {
    private static int coffeeStock = 100;
    private static int cookieStock = 100;
    private static double profit;
    private Scanner scanner = new Scanner(System.in);
    private int coffee = 0;
    private int cookies = 0;
    private JFrame frame;
    private JTextField coffeeField;
    private JTextField cookieField;
    public static void main(String[] args) {
        BuyItems buyItems = new BuyItems();
        buyItems.userInput();
    }
    public void userInput() {
        frame = new JFrame("Coffee and Cookie Shop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 5, 5));

        JLabel welcomeLabel = new JLabel("Welcome to the Coffee and Cookie Shop!");
        JLabel priceLabel = new JLabel("We have coffee for $2.50 each and cookies for $1.50 each.");

        JLabel coffeeLabel = new JLabel("How many coffees would you like to buy?");
        coffeeField = new JTextField(5);
        
        JLabel cookieLabel = new JLabel("How many cookies would you like to buy?");
        cookieField = new JTextField(5);

        JButton submitButton = new JButton("Add to Cart");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int coffeeCount = Integer.parseInt(coffeeField.getText());
                    int cookieCount = Integer.parseInt(cookieField.getText());
                    addItemsToCart(coffeeCount, cookieCount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers for coffee and cookies.");
                }
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); 
            }
        });
        panel.add(welcomeLabel);
        panel.add(priceLabel);
        panel.add(coffeeLabel);
        panel.add(coffeeField);
        panel.add(cookieLabel);
        panel.add(cookieField);
        frame.add(panel);
        frame.add(submitButton);
        frame.add(cancelButton);
        frame.setVisible(true);
    }
    public void addItemsToCart(int coffeeCount, int cookieCount) {
        if (coffeeCount < 0 || cookieCount < 0) {
            JOptionPane.showMessageDialog(frame, "Please enter non-negative numbers.");
            return;
        }
        if (coffeeCount > coffeeStock) {
            JOptionPane.showMessageDialog(frame, "Not enough coffee in stock. Available: " + coffeeStock);
            coffeeCount = coffeeStock;
        }
        if (cookieCount > cookieStock) {
            JOptionPane.showMessageDialog(frame, "Not enough cookies in stock. Available: " + cookieStock);
            cookieCount = cookieStock;
        }

        coffee += coffeeCount;
        cookies += cookieCount;

        String message;
        double totalCost = (coffee * 2.5) + (cookies * 1.5);
        if (coffee > 0 && cookies == 0) {
            message = "You have " + coffee + " coffee(s) in your cart for a total of $" + totalCost;
        } else if (cookies > 0 && coffee == 0) {
            message = "You have " + cookies + " cookie(s) in your cart for a total of $" + totalCost;
        } else if (coffee > 0 && cookies > 0) {
            message = "You have " + coffee + " coffee(s) and " + cookies + " cookie(s) in your cart for a total of $" + totalCost;
        } else {
            message = "No items in cart.";
            JOptionPane.showMessageDialog(frame, message);
            return;
        }
        int response = JOptionPane.showConfirmDialog(frame, message + "\nWould you like to buy these items?", 
                                                    "Confirm Purchase", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            coffeeStock -= coffee;
            cookieStock -= cookies;
            profit += totalCost;
            JOptionPane.showMessageDialog(frame, "Thank you for your purchase! Total cost: $" + totalCost);
            coffee = 0;
            cookies = 0;
            coffeeField.setText("");
            cookieField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Purchase cancelled.");
            coffee = 0;
            cookies = 0;
            coffeeField.setText("");
            cookieField.setText("");
        }
        initializeStock();
        
    }
    public void initializeStock() {
        if (coffeeStock == 0) {
            coffeeStock = 100;
        }
        if (cookieStock == 0) {
            cookieStock = 100;
        }
    }
}