import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class encapsulates the behaviour of an inventory Name:Khair Ahmed
 * Student Number:040946481 Course: CST8130 - Data Structures
 * 
 * @author Khair Ahmed
 *
 */
public class Inventory {
	/**
	 * List of InventoryItem that represents our inventory
	 */
	private ArrayList<InventoryItem> inventory;

	/**
	 * Default Constructor
	 */
	public Inventory() {
		inventory = new ArrayList<InventoryItem>(20);
	}

	/**
	 * Reads from the Scanner object passed in and fills the data member fields of
	 * the class with valid data.
	 * 
	 * @param scanner - Scanner to use for input
	 * 
	 * @return <code>true</code> if all data members were successfully populated,
	 *         <code>false</code> otherwise
	 */
	public boolean addItem(Scanner scanner) {

		InventoryItem item = null;
		boolean valid = false;
		while (!valid) {

			System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
			if (scanner.hasNext(Pattern.compile("[fFvVpP]"))) {
				String choice = scanner.next();
				switch (choice.toLowerCase()) {
				case "f":
					item = new InventoryItem("f");
					break;
				case "v":
					item = new InventoryItem("v");

					break;
				case "p":
					item = new InventoryItem("p");

					break;
				default: // Should not get here.
					break;
				}
				valid = true;
			} else {
				System.out.println("Invalid entry");
				scanner.next();
				valid = false;
			}
		}

		if (item.inputCode(scanner)) {
			if (alreadyExists(item) < 0) {
				if (item.addItem(scanner)) {
					insertItem(item);
					return true;
				}
				return false;
			} else {
				System.out.println("Item code already exists");
				return false;
			}
		}
		return true;
	}

	/**
	 * Search for a finventory item and see if it is already stored in the inventory
	 * 
	 * @param item - FoodItem to look for
	 * @return - The index of item if it is found, -1 otherwise
	 */
	public int alreadyExists(InventoryItem item) {
		return binarySearch(item.getItemCode(), 0, inventory.size() - 1);
	}

	/**
	 * Search for the code using a binary search algorithm
	 * 
	 * @param itemCode - Code to search for
	 * @param start    - Index to start at
	 * @param end      - Index to end at
	 * @return - Index of the found item or -1 if it is not found
	 */
	private int binarySearch(int itemCode, int start, int end) {
		int middle = (start + end) / 2;
		if (start > end)
			return -1;
		if (inventory.isEmpty())
			return -1;
		if (inventory.get(middle).getItemCode() == itemCode)
			return middle;
		if (inventory.get(middle).getItemCode() > itemCode)
			return binarySearch(itemCode, start, middle - 1);
		if (inventory.get(middle).getItemCode() < itemCode)
			return binarySearch(itemCode, middle + 1, end);
		return -1;
	}

	/**
	 * Inserts an item into the inventory and maintains sort order
	 * 
	 * @param item - Item to add to the inventory
	 */
	private void insertItem(InventoryItem item) {
		// Used to compare FoodItems
		InventoryItemComparator comp = new InventoryItemComparator();
		for (int i = 0; i < inventory.size(); i++) {
			// If the item is greater than the one in inventory, insert, insert here and
			// push everything else out
			if (comp.compare(inventory.get(i), item) >= 0) {
				inventory.add(i, item);
				return;
			}
		}
		inventory.add(item);
	}

	/**
	 * Asks a user for an item code to search for and then displays that item if
	 * found
	 * 
	 * @param scanner - Scanner to use for input
	 */
	public void searchForItem(Scanner scanner) {
		FoodItem itemToSearchFor = new FoodItem();
		itemToSearchFor.inputCode(scanner);
		int index = binarySearch(itemToSearchFor.getItemCode(), 0, inventory.size() - 1);
		if (index == -1)
			System.out.println("Code not found in inventory...");
		else
			System.out.println(inventory.get(index).toString());
	}
	/*
	 * Print Inventory
	 */

	@Override
	public String toString() {
		String returnString = "Inventory:\n";
		ListIterator<InventoryItem> items = inventory.listIterator();
		while (items.hasNext())
			returnString += items.next().toString() + "\n";
		return returnString;
	}

	/**
	 * Update the quantity stored in the food item
	 * 
	 * @param scanner   - Input device to use
	 * @param buyOrSell - If we are to add to quantity (<code>true</code>) or remove
	 *                  (<code>false</code>)
	 * @return <code>true</code> if update was successfully, <code>false</code>
	 *         otherwise
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		// If there are no items then we can't update, return
		if (inventory.isEmpty())
			return false;

		InventoryItem temp = new InventoryItem("t");
		temp.inputCode(scanner);
		int index = alreadyExists(temp);
		if (index != -1) {
			String buySell = buyOrSell ? "buy" : "sell";
			System.out.print("Enter valid quantity to " + buySell + ": ");
			if (scanner.hasNextInt()) {
				int amount = scanner.nextInt();
				if (amount > 0) {
					if (buyOrSell) {
						return inventory.get(index).updateQuantity(scanner, amount);
					} else {
						if (inventory.get(index).updateQuantity(scanner, amount * -1)) {
							if (inventory.get(index).checkNull()) {
								inventory.remove(index);
								return true;
							}
							return true;
						}

					}
				} else {
					System.out.println("Invalid quantity...");

				}

			} else {
				System.out.println("Invalid quantity...");
			}
		}

		return false;
	}

	/**
	 * print expiry dates of the inventory item
	 * 
	 * @param scanner user input
	 * @return <code>true</code> if all expiry dates are printed <code>false</code>
	 *         otherwise
	 */
	public boolean printExpiry(Scanner scanner) {
		FoodItem itemToSearchFor = new FoodItem();
		itemToSearchFor.inputCode(scanner);
		int index = binarySearch(itemToSearchFor.getItemCode(), 0, inventory.size() - 1);
		if (index == -1)
			System.out.println("Code not found in inventory...");
		else
			System.out.println(inventory.get(index).toString());
		inventory.get(index).printExpirySummary();
		return true;
	}

	/**
	 * remove all expired food item
	 * 
	 * @param today date where anything is past that date will be deleted
	 */
	public void removeExpiry(LocalDate today) {
		for (InventoryItem in : inventory) {
			in.removeExpiredItems(today);

		}
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).checkNull()) {
				inventory.remove(i);
			}
		}

	}
}
