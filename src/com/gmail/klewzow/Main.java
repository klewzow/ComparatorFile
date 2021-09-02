package com.gmail.klewzow;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
 
		File[] f = new File(".").listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("txt");
			}
		});

		ExecutorService ex = Executors.newFixedThreadPool(3);

		List<FileReader> fe = new ArrayList<>();

		for (File file : f) {
			fe.add(new FileReader(file, ex));
		}
		fe.sort(new FileReader()::compare);

		for (FileReader listFr : fe) {
			System.out.println( "File  - \"" + listFr.getFile().getName() + "\"  --->> " + listFr.getCountPunctuation());
		}
	}
}
