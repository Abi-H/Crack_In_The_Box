/*************************************************************
 *   Crack in the Box - Distributed SHA-512 Password Cracker *
 *   Student ID: 2151241							         *
 *************************************************************/

package crack_in_the_box;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Master implements Runnable {

	private int numberOfWorkers, passwordQueueSize;
	public static volatile boolean matchFound;
	public static volatile String crackedPassword;
	private Queue<BlockingQueue<String>> workerQueues;
	private FileHandler fh;
	private String filename, hash;

	public Master(String hash, String filename) {
		Master.matchFound = false;
		this.passwordQueueSize = 200;
		this.numberOfWorkers = 20;
		this.filename = filename;
		this.fh = new FileHandler(filename);
		this.hash = hash;
	}

	@Override
	public void run() {

		long start = System.currentTimeMillis();
		
		System.out.println("Password crack in progress ...\n");
		
		createWorkers(numberOfWorkers);
		createFileHandler();
		process(start);
		
		System.exit(0);
	}

	private void createFileHandler() {		
		Thread fileHandler = new Thread(fh);
		fileHandler.start();
	}

	private void process(long start) {

		String password = "";
		
		do {
			try {
				BlockingQueue<String> currentWorkerQueue = workerQueues.poll();
				password = fh.getPasswordQueue().take();
				currentWorkerQueue.offer(password);
				workerQueues.offer(currentWorkerQueue);
				
			} catch (InterruptedException e) {
				System.out.println("Master thread interrupted");
			}				
		} while (!Master.matchFound && !password.equals("#ENDOFFILE"));

		long end = System.currentTimeMillis();
		
		if (Master.matchFound) {
			System.out.printf("Success! The password is %s", Master.crackedPassword);
			System.out.printf("\nTook %d milliseconds to crack the hash", (end - start));			
		} else {
			System.out.printf("Could not crack hash %s", hash);
			System.out.printf("\nSpent %d milliseconds attempting to crack the hash", (end - start));
		}
	}

	private void createWorkers(int numberOfWorkers) {

		workerQueues = new LinkedList<BlockingQueue<String>>();

		for (int i = 0; i < numberOfWorkers; i++) {			
			ArrayBlockingQueue<String> workerQueue = new ArrayBlockingQueue<>(passwordQueueSize);
			Thread worker = new Thread(new Worker(workerQueue, hash), "Worker-" + i);	
			
			worker.start();
			workerQueues.offer(workerQueue);
		}
	}
}