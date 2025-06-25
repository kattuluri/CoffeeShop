import java.util.Scanner;
public class BuyItems {
    private static int coffeeStock;
    private static int cookieStock;
    private static double profit;
    private Scanner scanner = new Scanner(System.in);
    private int coffee;
    private int cookies;
    public static void main(String[] args) {
        System.out.println("Welcome to the Coffee and Cookie Shop!");
        System.out.println("We have coffee for $2.50 each and cookies for $1.50 each.");
        userInput();
    }
    public static void userInput() {
        BuyItems buyItems = new BuyItems();
        buyItems.initializeStock();
        System.out.println("How many coffees would you like to buy?");
        int count = Integer.parseInt(buyItems.scanner.nextLine());
        buyItems.addCoffeeToCart(count);
        System.out.println("How many cookies would you like to buy?");
        count = Integer.parseInt(buyItems.scanner.nextLine());
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