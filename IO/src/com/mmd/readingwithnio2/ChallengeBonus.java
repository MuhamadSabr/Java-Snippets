package com.mmd.readingwithnio2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChallengeBonus {
	public static void main(String[] args) {
		Path snowWhitePath = Path.of("snowwhite");

		Pattern pattern = Pattern.compile("\\p{javaWhitespace}");

		try(BufferedReader bufReader = new BufferedReader(new FileReader("snowwhite"))){
			var wordOccurrence = bufReader.lines()
					.map(fLine-> fLine.replaceAll("[\\p{Punct}“’”—]", ""))
					.flatMap(pattern::splitAsStream)
					.map(str-> str.trim().toLowerCase())
					.filter(w-> w.length()>5)
					.collect(Collectors.groupingBy(w-> w, Collectors.counting()) );
			wordOccurrence.entrySet().forEach(System.out::println);
			System.out.println("-".repeat(30));


			var top10MostUsedWords = wordOccurrence.entrySet().stream()
					.sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
					.limit(10)
					.collect(Collectors.toMap(
							Map.Entry::getKey,
							Map.Entry::getValue,
							(o, n)-> o,
							LinkedHashMap::new
					));

			System.out.println(top10MostUsedWords);

		}catch (IOException e){
			e.printStackTrace();
		}

		System.out.println("-".repeat(40));

		try {
			var str = Files.readString(snowWhitePath)
					.toLowerCase()
					.replaceAll("[\\p{Punct}“’”—\n]", "");
			Map <String, Integer> words = new LinkedHashMap<>();

			Matcher m = Pattern.compile("\\w{5,}").matcher(str);
			while (m.find()){
				words.merge(m.group(), 1, Integer::sum);
			}

			List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(words.entrySet());
			sortedEntries.sort(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
			for (int i=0; i<Math.min(10, sortedEntries.size()); i++){
				System.out.println(sortedEntries.get(i));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
