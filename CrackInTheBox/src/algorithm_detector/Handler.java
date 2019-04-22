package algorithm_detector;

import java.util.ArrayList;

public class Handler {

	private String hash;
	private MD5 md5;
	private SHA1 sha1;
	private SHA256 sha256;
	private SHA512 sha512;
	private Base64 base64;
	private ArrayList<Algorithm> algorithms;
	private ArrayList<Algorithm> matches;

	public Handler(String hash){
		this.hash = hash;
		md5 = new MD5("MD5", 16, hash);
		sha1 = new SHA1("SHA-1", 20, hash);
		sha256 = new SHA256("SHA-256", 32, hash);
		sha512 = new SHA512("SHA-512", 64, hash);
		base64 = new Base64("Base64", 0, hash);
		algorithms = new ArrayList<>();
		matches = new ArrayList<>();

		fillArrayList();
		process(hash.getBytes().length / 2, hash);
		printResults(matches, hash);

	}

	private void fillArrayList() {
		algorithms.add(md5);
		algorithms.add(sha1);
		algorithms.add(sha256);
		algorithms.add(sha512);
		algorithms.add(base64);
	}

	private void process(int hashLength, String hash) {
		
		for(Algorithm algorithm : algorithms) {		
			
			if(algorithm.toString().equals("Base64") && algorithm.isValid()) {
				matches.add(algorithm);
				
			} else if (algorithm.getLengthInBytes() == hashLength && algorithm.isValid()) {
				matches.add(algorithm);
			}
		}
	}
	
	private void printResults(ArrayList<Algorithm> matches, String hash) {
		
		System.out.println("\nPossible algorithms:");
		
		for(Algorithm algorithm : matches) {
			System.out.printf("%s\n", algorithm.toString());
		}
	}

}



