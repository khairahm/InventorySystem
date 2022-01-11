import java.time.LocalDate;

/**
 * Generic Linked list Name:Khair Ahmed Student Number:040946481 Course: CST8130
 * - Data Structures
 * 
 * @author khair ahmed
 *
 */

public class LinkedList {

	Node head;

	/**
	 * insert a node into the linked list
	 * 
	 * @param date   expiry date
	 * @param amount quantity
	 */

	public void insert(LocalDate date, int amount) {
		Node node = new Node();
		node.expiry = date;
		node.quantity = amount;
		node.next = null;
		if (head == null) {
			head = node;
		} else {
			Node n = head;
			while (n.next != null) {
				n = n.next;
			}

			n.next = node;
		}
	}

	/**
	 * Display all value in the linked list
	 */

	public void display() {
		Node node = head;
		while (node.next != null) {
			if (node.expiry.isEqual(LocalDate.MAX))
				System.out.println("No Expiry: " + node.quantity);
			else
				System.out.println(node.expiry + ": " + node.quantity);

			node = node.next;
		}
		if (node.expiry.isEqual(LocalDate.MAX))
			System.out.println("No Expiry: " + node.quantity);
		else
			System.out.println(node.expiry + ": " + node.quantity);
		System.out.println();
	}

	/**
	 * delete the node is it is expired
	 * 
	 * @param today
	 * @param qty
	 * @return the stock that left
	 */
	/*
	 * referenced from:
	 * https://stackoverflow.com/questions/45253705/delete-all-occurrences-of-an-
	 * element-in-a-linkedlist
	 */
	public int deleteNode(LocalDate today, int qty) {
		int amount = qty;
		Node tempNode = new Node();
		tempNode.next = head;
		Node currentNode = tempNode;
		while (currentNode.next != null) {
			if (currentNode.next.expiry.isBefore(today)) {
				amount -= currentNode.next.quantity;
				currentNode.next = currentNode.next.next;
			} else {
				currentNode = currentNode.next;
			}
		}

		head = tempNode.next;
		return amount;
	}

	/**
	 * sell items will remove from the node the quantity
	 * 
	 * @param qty
	 */
	public void sellItems(int qty) {

		Node tempNode = new Node();
		tempNode = head;

		while (tempNode != null) {
			if (qty > tempNode.quantity) {
				qty -= tempNode.quantity;
				tempNode = tempNode.next;
			} else {
				tempNode.quantity -= qty;
				break;
			}
		}

		head = tempNode;

	}
}
