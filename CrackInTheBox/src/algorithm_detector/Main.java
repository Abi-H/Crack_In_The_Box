package algorithm_detector;

/* This program is designed to test the Reflection API
 * Run the Reflection class to test Reflection*/

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		printInstructions();
		new Handler(getInput());
	}

	private static String getInput() {

		Scanner scanner = new Scanner(System.in);

		String input = scanner.nextLine();
		scanner.close();

		return input;
	}

	private static void printInstructions() {

		System.out.println("ALGORITHM DETECTOR");
		System.out.println("Detects MD5, SHA-1, SHA-256, SHA-512 and Base64 algorithm hashes");
		System.out.println("Enter hash: ");
	}

}
