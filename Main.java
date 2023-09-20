import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Main {
    private static Map<String, Item> inventory = new HashMap<>();
    private static List<Item> shoppingCart = new ArrayList<>();
    private static double totalPrice = 0;

    public static void main(String[] args) {
        initializeInventory();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Store!");
      
        while (true) {
            System.out.println(" ");
            System.out.println("1. View Items");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    displayInventory();
                    break;
                case 2:
                    addToCart(scanner);
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    System.out.println("Thank you for shopping with us!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeInventory() {
        inventory.put("Eggs", new Item("Eggs", 10.0, 10));
        inventory.put("Broccoli", new Item("Broccoli", 15.0, 5));
        inventory.put("Kale", new Item("Kale", 5.0, 20));
    }

    private static void displayInventory() {
        System.out.println("Available Items:");
        for (Item item : inventory.values()) {
            System.out.println(item.getName() + " - $" + item.getPrice() + " (Qty: " + item.getQuantity() + ")");
        }
    }

    private static void addToCart(Scanner scanner) {
        displayInventory();
        System.out.print("Enter the name of the item to add to your cart: ");
        String itemName = scanner.nextLine();

        Item item = inventory.get(itemName);
        if (item.getQuantity() > 0) {
            shoppingCart.add(item);
            item.decreaseQuantity(1);
            totalPrice += item.getPrice();
            System.out.println(item.getName() + " has been added to your cart.");
        } else {
            System.out.println("Item not available or out of stock.");
        }
    }

    private static void viewCart() {
        System.out.println("Shopping Cart:");
        for (Item item : shoppingCart) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
        System.out.println("Total Price: $" + totalPrice);
    }

    private static void checkout() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            viewCart();
            System.out.println("Thank you for your purchase! Your order has been placed.");
            shoppingCart.clear();
            totalPrice = 0;
        }
    }
}
