package com.neotechlabs.exceptions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TryWithResourcesDemo {

	private static final String inFileStr = "dog-image.jpg";
	private static final String outFileStr = "dog-image-out.jpg";

	private static void fileCopyWithArm() throws IOException {
		System.out.println("\nInside fileCopyWithArm ...");

		// Test t1 = new Test(); Test t2 = new Test();
		try (Test t1 = new Test(); Test2 t2 = new Test2(); BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))) {
			byte[] byteBuf = new byte[4000];
			int numBytesRead;
			while ((numBytesRead = in.read(byteBuf)) != -1) {
				out.write(byteBuf, 0, numBytesRead);
			}
			
			throw new IOException("Important Exception!!");
		}

		//System.out.println("\nEnd of fileCopyWithArm ...");
	}

	public static void main(String[] args) {		
		try {
			fileCopyWithArm();
		} catch (IOException ex) {
			//ex.printStackTrace();

			Throwable[] suppressed = ex.getSuppressed();
			System.out.println(suppressed[0].getMessage());
			System.out.println(suppressed[1].getMessage());
		}
	}
}

class Test implements AutoCloseable {
	@Override
	public void close() throws IOException {
		throw new IOException("Trivial Exception");
	}
}

class Test2 implements AutoCloseable {
	@Override
	public void close() throws IOException {
		throw new IOException("Trivial Exception 2");
	}
}