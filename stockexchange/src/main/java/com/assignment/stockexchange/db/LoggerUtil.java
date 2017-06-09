package com.assignment.stockexchange.db;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LoggerUtil {
	
	private static final String fileName = "logs.txt";
	
	public static synchronized void log(String contents) {
		try(FileWriter writer = new FileWriter(fileName, true)) {
			writer.write(contents);
			writer.append('\n');
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public static synchronized String readLogs() {
		String contents = "";
		StringBuffer buffer = null;
		try(
				FileReader reader = new FileReader(fileName);
				Scanner scanner = new Scanner(reader);
		   ) {
			while(scanner.hasNextLine()) {
				if(buffer == null) {
					buffer = new StringBuffer();
				}
				buffer.append(scanner.nextLine());
				buffer.append('\n');
			}
		} catch (IOException e) {
			System.err.println(e);
		}
		
		if(buffer != null) {
			contents = buffer.toString();
		} else {
			contents = "No logs available";
		}
		return contents;
	}

}
