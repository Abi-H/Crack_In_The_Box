package test;

public class Main {

	public static void main(String[] args) {

		String hash = "123b!";
		
		if(checkHex(hash)) {
			System.out.println("Hexadecimal!");
		} else {
			System.out.println("Not hexadecimal!");
		}
		
		if(checkDecimal(hash)) {
			System.out.println("Decimal!");
		} else {
			System.out.println("Not decimal!");
		}
		
		if(checkAN(hash)) {
			System.out.println("Alphanumeric!");
		} else {
			System.out.println("Not alphanumeric!");
		}

	}

	public static boolean checkHex(String hash) {

		for (int i = 1; i < hash.length(); i++) {

			if (Character.digit(hash.charAt(i), 16) == -1)
				return false;
		}

		return true;
	}
	
	public static boolean checkDecimal(String hash) {
		for (int i = 1; i < hash.length(); i++) {

			if (Character.digit(hash.charAt(i), 10) == -1)
				return false;
		}

		return true;
	}
	
	public static boolean checkAN(String hash) {
		for (int i = 0; i <hash.length(); i++) {
            char c = hash.charAt(i);
            if (!Character.isDigit(c) && !Character.isLetter(c))
                return false;
        }

        return true;
	}

}
