package com.mmd.readingwithnio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		System.out.println(System.getProperty("file.encoding"));//Two ways to know the default encoding of ur app
		System.out.println(Charset.defaultCharset());

		Path path = Path.of("fixedwidth.txt");
		try {
			String fileS = new String(Files.readAllBytes(path)); //Files.readAllBytes returns byte[], n String has a constructor
			System.out.println(fileS);																	//that takes a byte[]
			System.out.println("-".repeat(40));
			System.out.println( Files.readString(path) ); //Returns the whole file as a single String. When dealing with text-based files(e.g., CSV, JSON0 that are not
			System.out.println("-".repeat(40));//excessively large, reading the entire content as a single string provides a convenient way to perform parsing and analysis
			Files.readAllLines(path).forEach(System.out::println);//Returns all lines as a List<String> each line is an item in it.
			System.out.println("-".repeat(40));

			Pattern p = Pattern.compile("(.{15})(.{3})(.{12})(.{8})(.{2})");
			Set<String> values = new TreeSet<>();
			Files.readAllLines(path).forEach(line -> {
				if(!line.startsWith("Name")){//This way it skips the first line.
					Matcher m = p.matcher(line); //After that if there is a match it will get its third group trim it n assign it to a set.
					if(m.matches())//If found any match
						values.add(m.group(3).trim());
				}
			});
			values.forEach(System.out::println);
			System.out.println("-".repeat(40));
																//until a terminal operation is called on it. so try-with-resources is needed remember.
			try (Stream<String> stringStream = Files.lines(path) ) {//Returns a Stream<String>, a line as an item. //Since it is Steam it means it will not close
					stringStream.skip(1L)
						.map(p::matcher)
						.filter(Matcher::matches)
						.map(matcher -> matcher.group(3))
						.distinct()
						.sorted()
						.forEach(System.out::println);
					System.out.println("-".repeat(40));
			}

			try (Stream<String> stringStream = Files.lines(path) ) {
				var result = stringStream.skip(1L)
										.map(p::matcher)
										.filter(Matcher::matches)
										.collect(Collectors.groupingBy(matcher -> matcher.group(3), Collectors.counting()));
				result.entrySet().forEach(System.out::println);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		Charset utf8Charset = StandardCharsets.UTF_8;
		// Encoding: String to Byte Buffer
		ByteBuffer byteBuffer = utf8Charset.encode("Hello, World!");
		// Decoding: Byte Buffer to String
		String decodedString = utf8Charset.decode(byteBuffer).toString();

	}
}
