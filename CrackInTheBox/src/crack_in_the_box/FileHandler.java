/**************************************************************
 *   Crack in the Box - Distributed SHA-512 Password Cracker  *
 *   Student ID: 2151241							          *
 *************************************************************/

package crack_in_the_box;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FileHandler implements Runnable {

	private int queueSize;
	private ArrayBlockingQueue<String> passwordQueue;
	private String filename;

	public FileHandler(String filename) {
		this.filename = filename;
		this.queueSize = 200;
		this.passwordQueue = new ArrayBlockingQueue<String>(queueSize);
	}

	@Override
	public void run() {
		readFromFile(filename);
	}
	
	private void readFromFile(String filename) {
		
		File file = new File(filename);
		
		String line;

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				passwordQueue.put(line);
			}
			
			passwordQueue.put("#ENDOFFILE");
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println(file + " not found");
		} catch (IOException e) {
			System.out.println(file + " could not be opened");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public BlockingQueue<String> getPasswordQueue() {
		return passwordQueue;
	}
}
