package algorithm_detector;

public class Base64 extends Algorithm implements FormatDetector{
	
	public Base64(String algorithm, int lengthInBytes, String hash) {
		super(algorithm, lengthInBytes, hash);
		
		if(detectAlphaNumeric(hash)) {
			setValid(true);
		} else {
			setValid(false);
		}
	}

	@Override
	public boolean detectHexadecimal(String hash) {
		
		for (int i = 1; i < hash.length(); i++) {
			if (Character.digit(hash.charAt(i), 16) == -1)
				return false;
		}

		return true;
	}

	@Override
	public boolean detectDecimal(String hash) {
		
		for (int i = 1; i < hash.length(); i++) {
			if (Character.digit(hash.charAt(i), 10) == -1)
				return false;
		}

		return true;
	}

	@Override
	public boolean detectAlphaNumeric(String hash) {
		
		for (int i = 0; i <hash.length(); i++) {			
            char c = hash.charAt(i);
            
            if (!Character.isDigit(c) && !Character.isLetter(c))
                return false;
        }

        return true;
	}
}
