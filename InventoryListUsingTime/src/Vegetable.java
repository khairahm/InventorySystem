
import java.util.Scanner;

/**
 * Claas vegetable that contains all information on the vegeatibles Name:Khair
 * Ahmed Student Number:040946481 Course: CST8130 - Data Structures
 * 
 * @author Khair Ahmed
 *
 */
public class Vegetable extends FoodItem {
	/**
	 * Name of the farm the vegetable came from
	 */
	private String farmName;

	/**
	 * Default Constructor
	 */
	public Vegetable() {
		super();
		farmName = "";
	}

	/**
	 * add farm supplier information
	 * 
	 * @param scanner user input
	 */

	@Override
	public boolean addItem(Scanner scanner) {
		if (super.addItem(scanner)) {

			System.out.print("Enter the name of the farm supplier: ");
			farmName = scanner.next();
		}
		return true;
	}

	/**
	 * String to string print vegeable information
	 */

	@Override
	public String toString() {
		return super.toString() + " farm supplier: " + farmName;
	}
}
