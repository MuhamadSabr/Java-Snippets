package com.mmd.scanner;

import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		File file = new File(new File("."), "testfile.txt");// U can create a new Scanner of String, File, FileReader, Path, or FileReader wrapped
		try (Scanner scanner = new Scanner(file)) {//A scanner is not automatically closed.												// in BufferedReader...
//			while (scanner.hasNext())
//				System.out.println(scanner.nextLine());
//			System.out.println(scanner.delimiter());  //Find out what delimiter is the scanner using.
			scanner.useDelimiter("\\p{Zl}");//Line Separator. U can just use $ which is a metacharacter for a new line.
//			scanner.tokens().forEach(System.out::println);
			scanner.findAll("[A-Za-z]{10,}") //Returns a Stream<MatchResult> items
					.map(MatchResult::group)		//use mr.group to return the current matched on String.
					.distinct()
					.sorted()
					.forEach(System.out::println);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		System.out.println("-".repeat(50));

//		Scanner will always take advantage of NIO.2 functionality even if u pass IO classes to it.
		try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("fixedWidth.txt")))){
			var sc = scanner.findAll("(.{15})(.{3})(.{12})(.{8})(.{2})")
					.map(mr-> mr.group(5).trim())
					.distinct()
					.sorted()
					.toArray();
			System.out.println(Arrays.toString(sc));

		}catch (IOException e){
			e.printStackTrace();
		}

	}
}
