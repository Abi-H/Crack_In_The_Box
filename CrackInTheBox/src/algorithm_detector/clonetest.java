import java.util.*;

public class CloneTest{
	Algorithm a = new Algorithm("SHA-1", 20, "e7c02076bcc829fc12a7bf385c8a787cea641939");
	a.setHasMatch(false);
	
	Algorithm a2 = (Algorithm)a.clone();
}

public abstract class Algorithm implements Cloneable {
	
	protected int lengthInBytes;
	protected String algorithm;
	protected String hash;
	private boolean isValid;
	
	public Algorithm(String algorithm, int lengthInBytes, String hash) {
		this.algorithm = algorithm;
		this.lengthInBytes = lengthInBytes;
	}
	
	public Object clone(){
		try{
			Algorithm cloned = (Algorithm)super.clone();
			cloned.isValid = 
	}
	
	@Override
	public String toString() {
		return algorithm;
	}
	
	public boolean hasMatch() {
		return hasMatch;
	}

	public void setHasMatch(boolean hasMatch) {
		this.hasMatch = hasMatch;
	}
	
	public boolean isValid() {
		return isValid;
	}
	
	public void setValid(boolean valid) {
		isValid = valid;
	}

	public void setLengthInBytes(int lengthInBytes) {
		this.lengthInBytes = lengthInBytes;
	}
	
	public int getLengthInBytes() {
		return lengthInBytes;
	}
}
