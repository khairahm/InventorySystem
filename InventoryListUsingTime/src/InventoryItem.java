import java.time.LocalDate;

import java.util.Scanner;

/**
 * Inventory item class that hold all informatin on a item and it quantity
 * Name:Khair Ahmed Student Number:040946481 Course: CST8130 - Data Structures
 * 
 * @author Khair Ahmed
 */
public class InventoryItem {
	/**
	 * stock of the food item
	 */
	private int itemQuantityInStock;
	/**
	 * holds all information on food
	 */
	private FoodItem item;
	/**
	 * linked list that holds all expiry date
	 */
	private LinkedList expires = new LinkedList();

	/**
	 * 
	 * @param foodType string what decides the foodtype
	 */
	public InventoryItem(String foodType) {
		switch (foodType) {
		case "f":
			item = new Fruit();
			break;
		case "v":
			item = new Vegetable();
			break;
		case "p":
			item = new Preserve();
			break;
		case "t":
			item = new FoodItem();
			break;

		}
	}

	/**
	 * adds the quantiy information and an expiry date
	 * 
	 * @param scanner
	 * @return trueif the object is added else return false
	 */

	public boolean addItem(Scanner scanner) {
		boolean valid = false;
		LocalDate tempDate = null;
		String expiry = null;
		item.addItem(scanner);

		valid = false;
		while (!valid) {
			System.out.print("Enter the quantity for the item: ");
			if (scanner.hasNextInt()) {
				itemQuantityInStock = scanner.nextInt();
				if (itemQuantityInStock < 0) {
					valid = false;
					System.out.println("Invalid input");
					itemQuantityInStock = 0;
				} else
					valid = true;
			} else {
				System.out.println("Invalid input");
				scanner.next();
				valid = false;
			}
		}

		valid = false;
		while (!valid) {
			try {
				System.out.print("Enter the expiry date of the item (yyyy-mm-dd or none): ");
				expiry = scanner.next();
				if (expiry.equalsIgnoreCase("none")) {
					tempDate = LocalDate.MAX;
					valid = true;
				} else {

					tempDate = LocalDate.parse(expiry);
					valid = true;

				}
			} catch (Exception e) {
				System.out.println("Could not create date from input, please use format yyyy-mm-dd");
				System.out.println(e.getMessage());
			}
		}

		expires.insert(tempDate, itemQuantityInStock);
		return true;
	}

	/**
	 * gets the item code
	 * 
	 * @return item.getItemCode
	 */
	public int getItemCode() {
		return item.getItemCode();
	}

	/**
	 * input the code
	 * 
	 * @param scanner user input
	 * @return true if the item code was saved to food item
	 */

	public boolean inputCode(Scanner scanner) {
		return item.inputCode(scanner);
	}

	/**
	 * print a;; expiry dates
	 */
	public void printExpirySummary() {
		if (expires == null) {
			System.out.println("There is no inventory");
		} else {
			expires.display();
		}
	}

	/**
	 * remove all expired dated
	 * 
	 * @param today
	 */

	public void removeExpiredItems(LocalDate today) {
		itemQuantityInStock = expires.deleteNode(today, itemQuantityInStock);

	}

	/**
	 * Updates the quantity field by amount (note amount could be positive or
	 * negative)
	 *
	 * @param amount - what to update by, either can be positive or negative
	 * @return Method returns <code>true</code> if successful, otherwise returns
	 *         <code>false</code>
	 */
	public boolean updateQuantity(Scanner scanner, int amount) {
		LocalDate tempDate = null;
		String expiry = null;
		// If you are removing stock, then check that we have enough stock
		if (amount < 0 && itemQuantityInStock < (amount * -1)) {
			return false;

		}
		if (amount > 0) {
			boolean valid = false;
			while (!valid) {
				try {
					System.out.print("Enter the expiry date of the item (yyyy-mm-dd or none): ");
					expiry = scanner.next();
					if (expiry.equalsIgnoreCase("none")) {
						tempDate = LocalDate.MAX;
						valid = true;
					} else {

						tempDate = LocalDate.parse(expiry);
						valid = true;

					}
				} catch (Exception e) {
					System.out.println("Could not create date from input, please use format yyyy-mm-dd");
					System.out.println(e.getMessage());
				}
			}
			expires.insert(tempDate, amount);
		}

		if (amount < 0) {
			expires.sellItems(amount * -1);

		}
		itemQuantityInStock += amount;

		return true;
	}

	/**
	 * print string information
	 */

	public String toString() {
		return item.toString() + " qty: " + itemQuantityInStock;

	}

	/**
	 * checks if the stock of the itemis 0, used for deleted the inventory item
	 * array list
	 * 
	 * @return true if no stock else false
	 */

	public boolean checkNull() {
		if (itemQuantityInStock == 0) {
			return true;
		}

		return false;
	}

}
