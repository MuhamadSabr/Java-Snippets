package com.mmd.readingfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try (FileReader reader = new FileReader("testfile.txt")) {
			int data;
//			while ((data=reader.read())!=-1){//reader.read() returns integer for each character it reads. character = unsignedInt
//				System.out.println((char)data);//So to get the characters as it is, u v to cast it to char explicitly
//			}
			char[] block = new char[1000];
			while ((data=reader.read(block))!=-1){//By passing a char array to the .read() method u can read more than one
				String content = new String(block, 0, data); //character n avoid the cast.
				System.out.printf("==> [%d chars] %s%n", data, content);//When surpassing 1000, the output will be divided into multiple char arrays
			}
		}catch (IOException e){
			e.printStackTrace();
		}

		System.out.println("-".repeat(50));

		try(BufferedReader bufReader = new BufferedReader(new FileReader("testfile.txt"))){
			String line;
//			while ((line=bufReader.readLine())!=null)//The FileReader reads either int or car[]
//					System.out.println(line);	//But with BufferedReader u can use .readLine() to read line by line.
			bufReader.lines().forEach(System.out::println);//with .lines providing a Stream<String> it's even easier than above
		}catch (IOException e){
			e.printStackTrace();
		}

	}
}
