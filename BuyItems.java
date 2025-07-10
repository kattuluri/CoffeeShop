import java.util.Scanner;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 5, 5));
        frame.add(panel);

        JLabel welcomeLabel = new JLabel("Welcome to the Coffee and Cookie Shop!");
        panel.add(welcomeLabel);
        JLabel priceLabel = new JLabel("We have coffee for $2.50 each and cookies for $1.50 each.");
        panel.add(priceLabel);

        BuyItems buyItems = new BuyItems();
        buyItems.initializeStock();

        JLabel coffeeLabel = new JLabel("How many coffees would you like to buy?");
        panel.add(coffeeLabel);
        coffeeField = new JTextField(5);
        panel.add(coffeeField);
        int count = Integer.parseInt(coffeeField.getText());
        buyItems.addCoffeeToCart(count);
        
        JLabel cookieLabel = new JLabel("How many cookies would you like to buy?");
        panel.add(cookieLabel);
        cookieField = new JTextField(5);
        count = Integer.parseInt(cookieField.getText());
        buyItems.addCookiesToCart(count);

        buyItems.buyItems();
    }
    public void initializeStock() {
        if (coffeeStock == 0) {
            coffeeStock = 100;
        }
        if (cookieStock == 0) {
            cookieStock = 100;
        }
    }
    public void addCoffeeToCart(int count) {
        if (coffeeStock > 0) {
            coffee += count;
            System.out.println("Coffee added to cart. Current coffee count: " + coffee);
        } else {
            System.out.println("No coffee left in stock.");
        }
    }
    public void addCookiesToCart(int count) {
        if (cookieStock > 0) {
            cookies += count;
            System.out.println("Cookies added to cart. Current cookie count: " + cookies);
        } else {
            System.out.println("No cookies left in stock.");
        }
    }
    public void buyItems() {
        if (coffee > 0 || cookies > 0) {
            if (coffee > 0 && cookies == 0) {
                System.out.println("You have " + coffee + " coffee(s) in your cart for a total of " + 
                                   (coffee * 2.5) + " dollars.");
            }
            else if (cookies > 0 && coffee == 0) {
                System.out.println("You have " + cookies + " cookie(s) in your cart for a total of " +
                                   (cookies * 1.5) + " dollars.");
            } 
            else {
                System.out.println("You have " + coffee + " coffee(s) and " + cookies + " cookie(s) in your cart for a total of " +
                                   ((coffee * 2.5) + (cookies * 1.5)) + " dollars.");
            }
        } else {
            System.out.println("No items in cart.");
            return;
        }
        System.out.println("Would you like to buy these items? (yes/no)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            if (coffee > coffeeStock) {
                System.out.println("Not enough coffee in stock.");
                coffee = coffeeStock; 
            }
            if (cookies > cookieStock) {
                System.out.println("Not enough cookies in stock.");
                cookies = cookieStock;
            }
            double totalCost = (coffee * 2.5) + (cookies * 1.5);
            profit += totalCost;
            coffeeStock -= coffee;
            cookieStock -= cookies;
            System.out.println("Thank you for your purchase! Total cost: " + totalCost + " dollars.");
            coffee = 0; 
            cookies = 0; 
            return;
        } else {
            System.out.println("Purchase cancelled.");
            userInput();
        }
    }
}