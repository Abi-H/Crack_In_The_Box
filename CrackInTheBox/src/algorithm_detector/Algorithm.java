package algorithm_detector;

public abstract class Algorithm implements FormatDetector {
	
	protected int lengthInBytes;
	protected String algorithm;
	protected String hash;
	private boolean hasMatch;
	private boolean isValid;
	
	public Algorithm(String algorithm, int lengthInBytes, String hash) {
		this.algorithm = algorithm;
		this.lengthInBytes = lengthInBytes;
		setHasMatch(false);
	}
	
	@Override
	public String toString() {
		return algorithm;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	public void setValid(boolean valid) {
		isValid = valid;
	}
		
	public boolean hasMatch() {
		return hasMatch;
	}

	public void setHasMatch(boolean hasMatch) {
		this.hasMatch = hasMatch;
	}

	public void setLengthInBytes(int lengthInBytes) {
		this.lengthInBytes = lengthInBytes;
	}
	
	public int getLengthInBytes() {
		return lengthInBytes;
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
