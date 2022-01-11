
import java.util.Scanner;

/**
 * 
 * Class of Fruit that contains information of all fruit objects Name:Khair
 * Ahmed Student Number:040946481 Course: CST8130 - Data Structures
 * 
 * @author Khair Ahmed
 *
 */
public class Fruit extends FoodItem {
	/**
	 * Name of the orchard the fruit is from
	 */
	private String orchardName;

	/**
	 * Default Constructor
	 */
	public Fruit() {
		super();
		orchardName = "";
	}
	/*
	 * add the ocharhad supplier information
	 */

	@Override
	public boolean addItem(Scanner scanner) {
		if (super.addItem(scanner)) {
			System.out.print("Enter the name of the orchard supplier: ");
			orchardName = scanner.next();
		}
		return true;
	}

	/*
	 * print fruit information
	 */

	@Override
	public String toString() {
		return super.toString() + " orchard supplier: " + orchardName;
	}
}
