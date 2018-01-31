package com.neotechlabs.concurrency.indexer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class IOUtil {
	public static void read(List<String> data, String filename) {
		try (BufferedReader br = new BufferedReader(new
				InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
			String line;
			while ((line = br.readLine()) != null) {
				data.add(line);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String read(InputStream in) throws IOException {
		StringBuilder text = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
			String line;
			while ((line = br.readLine()) != null) {
				text.append(line).append("\n");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw e;
		}
		return text.toString();
	}

	public static void write(String webpage, long id) {
		//D:\Project\JavaPjt\thrillio
		String filename = "D:\\Project\\JavaPjt\\thrillio\\pages\\" + String.valueOf(id) + ".html";
		try (BufferedWriter writer = new BufferedWriter(new
				OutputStreamWriter(new FileOutputStream(filename), "UTF-8"))) {
			writer.write(webpage);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
