import java.util.Comparator;

/**
 * Used to compare objects of type InventoryItem Name:Khair Ahmed Student
 * Number:040946481 Course: CST8130 - Data Structures
 * 
 * @author Khair Ahmed
 *
 */
public class InventoryItemComparator implements Comparator<InventoryItem> {
	// Compares its two arguments for order. Returns a negative integer, zero, or a
	// positive integer as the first argument is less than, equal to, or greater
	// than the
	// second.
	@Override
	public int compare(InventoryItem o1, InventoryItem o2) {
		// Compare by item code.
		return o1.getItemCode() - o2.getItemCode();
	}

}
