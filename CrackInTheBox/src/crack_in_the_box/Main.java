/*************************************************************
 *   Crack in the Box - Distributed SHA-512 Password Cracker *
 *   Student ID: 2151241							         *
 *************************************************************/

package crack_in_the_box;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
				
		printLogo();
		 
		new Thread(new Master(getInput(), "passwords.txt"), "Master_Thread").start();		 	 	
	}
	
	private static void printLogo() {
		System.out.println("+-----------------------------------------+");
		System.out.println("+            CRACK IN THE BOX             +");
		System.out.println("+   Distributed SHA-512 Password Cracker  +");
		System.out.println("+-----------------------------------------+");
	}
	
	private static String getInput() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter SHA-512 Hash: ");
		String input = scanner.nextLine();
		
		scanner.close();
		
		return input;
	}
}
