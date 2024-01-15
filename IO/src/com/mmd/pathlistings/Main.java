package com.mmd.pathlistings;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		Path path = Path.of("files/test.csv");
		Path path2 = Paths.get("testfile.txt");
		Path pathA= Path.of("C:\\files/test.csv");
//		printPathInfo(path);//The second \ is an escape \

		Path logPath = Path.of("exceptions/warnings/normal/logs/log1.txt");
		System.out.println("Multilevel parent:  " + logPath.getParent());
//		logStatement(logPath);

		printExtraInfo(Path.of("C:\\files/test.csv"));
	}

	private static void printPathInfo(Path path){
		System.out.println("Path: " + path);//Returns the path string it was constructed with.
		System.out.println("Path's name: " + path.getName(path.getNameCount()-1));//Returns the name element at the index
		//.getNameCount returns the number of name elements in the path.										in the path.
		System.out.println("File name: " + path.getFileName());//Returns the file name or the last element in the path
		System.out.println("Path's parent: " + path.getParent());//Parent directory or null if its directly in root of the project
		System.out.println("toAbsolute path: " + path.toAbsolutePath());//Returns the absolute path.
		System.out.println("Root component of the path: " + path.getRoot());
		System.out.println("Is absolute: " + path.isAbsolute());//Test whether the path is absolute.
		System.out.println("toUri: " + path.toUri());//Returns an instance of URI.
		int c=0;
		Iterator<Path> it = path.toAbsolutePath().iterator();//Doesn't include Root C:\
		while (it.hasNext()){
			System.out.println(".".repeat(c++) + it.next());
		}
		for(int i =0; i<path.toAbsolutePath().getNameCount(); i++){
			System.out.println(".".repeat(i) + path.toAbsolutePath().getName(i));
		}
		System.out.println("-".repeat(40));
	}

	private static void  printExtraInfo(Path path){
		try {
			Files.readAttributes(path, "lastModifiedTime")
			.entrySet().forEach(System.out::println);
//			System.out.println(LocalDateTime.ofInstant( attr.creationTime().toInstant(), ZoneId.systemDefault()));
//			System.out.println(LocalDateTime.ofInstant(attr.lastAccessTime().toInstant(),ZoneId.systemDefault()));
//			System.out.println(LocalDateTime.ofInstant(attr.lastModifiedTime().toInstant(), ZoneId.systemDefault()));
//			var attr = Files.readAttributes(path, "*");
//			attr.entrySet().forEach(System.out::println);
			System.out.println(Files.probeContentType(path));//Returns a string of the content of the path. If directory Null it is

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void logStatement(Path path){
		Path parent = path.getParent();
			try {
				if(!Files.exists(parent)) {
//					Files.createDirectory(parent);
					Files.createDirectories(parent);//U can use it to create a single directory or multi level directories.
				}									//Below we say if the file does not exist CREATE it, if exists APPEND to it
				Files.writeString(path, Instant.now()+ " writing to file\n", StandardOpenOption.CREATE,
																				StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
				try {
					Files.writeString(Path.of("C:\\files/exceptions/excpt1"), e.getMessage(), StandardOpenOption.CREATE,
																						StandardOpenOption.APPEND);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
	}

}
