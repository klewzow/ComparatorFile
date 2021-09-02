package com.gmail.klewzow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class FileReader implements Callable<FileReader>, Comparator<FileReader> {
	private Future<FileReader> future;
	private File file;
	private int countPunctuation;

	public FileReader(File file, ExecutorService ex) {
		super();
		this.file = file;
		future = ex.submit(this);
	}

	public FileReader() {
		super();
	}

	public int getCountPunctuation() {
		return countPunctuation;
	}

	public Future<FileReader> getFuture() {
		return future;
	}

	public File getFile() {
		return file;
	}

	private void read(File file) {
		int count = 0;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
			for (int str = 0; (str = br.read()) != -1;) {
				if ((char) str == 32 | (char) str == 33 | ((char) str >= 44 && (char) str <= 46) | (char) str == 63) {
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.countPunctuation = count;
	}

	@Override
	public FileReader call() throws Exception {
		read(this.file);
		return this;
	}

	@Override
	public int compare(FileReader o1, FileReader o2) {

		return o1.countPunctuation - o2.countPunctuation;
	}

}
