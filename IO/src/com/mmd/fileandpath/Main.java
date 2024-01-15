package com.mmd.fileandpath;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		useFile("testfile.txt");
		usePath("testpath.txt");
	}

	private static void useFile(String fileName){
		File file = new File(fileName);
		System.out.println(file.length());
		System.out.println(Arrays.toString(file.listFiles()));
		System.out.println((file.isDirectory()? "Directory" : file.isFile() ? "File" : "Should never happen"));
		boolean isExists = file.exists();
		System.out.printf("%s, %s%n", file, (isExists ? "exists" : "does not exist"));

		if(isExists){
			System.out.println("Deleting: " + file);
			isExists = !file.delete();
		}

		if(!isExists){
			try {
				file.createNewFile();//Returns a boolean
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			System.out.println(file + " is created");
		}

		if(file.canWrite()){
			System.out.println("Would write to method here");
		}
	}

	private static void usePath(String fileName){
		Path path = Path.of(fileName);
		System.out.println((Files.isDirectory(path)? "Directory" : Files.isRegularFile(path) ? "File" : "Should never happen"));
		boolean isExists = Files.exists(path);
		System.out.printf("%s, %s%n", path, (isExists ? "exists" : "does not exist"));

		if(isExists){
			System.out.println("Deleting: " + path);
			try {
				isExists = !Files.deleteIfExists(path);//If u use just delete it will have a return type of void n not boolean
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if(!isExists){
			try {
				Files.createFile(path);//Returns a Path
				System.out.println(path + " is created");

				if(Files.isWritable(path)){//Files.writeString method must be thrown or caught
					Files.writeString(path, """
							Here is some data,
							For my file,
							just to prove,
							Using Files n path is much more modern and convenient than File class,
							Also File is not used to actual I/O operations. but used to represent file n directory pathnames""");
				}

				System.out.println("Size of file " + Files.size(path));
				Files.list(path).forEach(c-> System.out.println(c + " "));
				System.out.println();
				if(Files.isReadable(path)){
					Files.readAllLines(path)//Like .writeString method .readAllLines method must also be thrown or caught
							.forEach(System.out::println);
				}

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}


			System.out.println(
					Period.between(LocalDate.of(2024,2,1), LocalDate.now())
			);
			System.out.println(
					ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.of(2024, 2, 1))
			);

		}

	}

}
