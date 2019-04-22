package algorithm_detector;

public interface FormatDetector {
	
	public boolean detectHexadecimal(String hash);
	
	public boolean detectDecimal(String hash);
	
	public boolean detectAlphaNumeric(String hash);
}
