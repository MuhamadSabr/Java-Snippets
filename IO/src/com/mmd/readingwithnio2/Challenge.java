package com.mmd.readingwithnio2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Challenge {
	public static void main(String[] args) {

		Path shoeMakerPath = Path.of("shoemaker.txt");
		Path snowwhitePath = Path.of("snowwhite");

		try(Stream<String> fileStream = Files.lines(snowwhitePath)){

			var wordOccurrence = fileStream
					.map(fLine-> fLine.replaceAll("[\\p{Punct}“’”—]", ""))
					.map(fLine-> fLine.split(" "))
					.flatMap(Arrays::stream)
					.map(str-> str.trim().toLowerCase())
					.filter(w-> w.length()>5)
					.collect(Collectors.groupingBy(w-> w, Collectors.counting()) );
			wordOccurrence.entrySet().forEach(System.out::println);
			System.out.println("-".repeat(30));

			var top10MostUsedWords = wordOccurrence.entrySet().stream()
					.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
					.limit(10);

			top10MostUsedWords.forEach((e)-> System.out.println( e.getKey()+ ": " + e.getValue()) );

		}catch (IOException e){
			e.printStackTrace();
		}
		System.out.println("-".repeat(40));

		try {
			var str = Files.readString(snowwhitePath)
					.toLowerCase()
					.replaceAll("[\\p{Punct}“’”—\n]", "")
					.split(" ");
			Map <String, Integer> words = new LinkedHashMap<>();
			for (String s : str) {
				words.merge(s.trim(), 1, Integer::sum);
			}
			words.keySet().forEach(System.out::println);
			System.out.println(words.get("snowwhite"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
