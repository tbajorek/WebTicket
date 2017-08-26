package tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * SHA-256 hash generator
 * 
 * @author Tomasz Bajorek
 */
public class Logger {
	/**
	 * Prefix direction and name of file with logs
	 */
	private static String dir = "logs/wt_";
	
	/**
	 * Save the given log to file
	 * @param filename Name of file with logs
	 * @param content Log to save
	 */
	public static void saveLog(String filename, String content) {
		BufferedWriter output = null;
		Date currentTime = new Date();
		try {
			File file = new File(dir+filename);
			output = new BufferedWriter(new FileWriter(file, true));
			output.write("["+currentTime+"]"+content);
			output.newLine();
		} catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			try {
				if ( output != null ) {
					output.close();
				}
			} catch(IOException e) {
				System.out.println("Failed !!! " + e.getMessage());
			}
		}
	}
}
