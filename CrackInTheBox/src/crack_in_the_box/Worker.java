/*************************************************************
 *   Crack in the Box - Distributed SHA-512 Password Cracker *
 *   Student ID: 2151241							         *
 *************************************************************/

package crack_in_the_box;

import java.util.concurrent.ArrayBlockingQueue;

import crack_in_the_box.HashConverter;

public class Worker implements Runnable{
	
	private ArrayBlockingQueue<String> passwordQueue;
	private String hash;

	public Worker(ArrayBlockingQueue<String> passwordQueue, String hash) {
		this.passwordQueue = passwordQueue;
		this.hash = hash;
	}
	
	@Override
	public void run() {
		
		while(!Master.matchFound) {		
			
			String password;
			
			try {
				password = passwordQueue.take();		
				
				if (hash.equals(HashConverter.getSHA512Hash(password))) {						
					Master.crackedPassword = password;
					Master.matchFound = true;
				}
				
			} catch (InterruptedException e) {
				System.out.println("Worker Thread interrupted");
			}
								
		}
	}	
}
