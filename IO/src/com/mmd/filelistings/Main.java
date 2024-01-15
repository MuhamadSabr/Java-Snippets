package com.mmd.filelistings;

import javax.swing.event.ListDataEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		Path path = Path.of("");
		System.out.println(path.toAbsolutePath());//A Stream is lazily executed, that means a reference to an open directory
		try (Stream<Path> pathStream = Files.list(path)) {//is maintained n remains open until the Stream's terminal operation is executed
			pathStream
					.map(Main::listDir)
					.forEach(System.out::println);//Methods that return Streams r opening a resource.
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println("-".repeat(50));//walk is the same as list if u specify 1 as the max depth parameter(2nd arg)
		try (Stream<Path> pathStream = Files.walk(path,3)) {//If u omit max depth or specify more than 1 its recursive list
			pathStream												//omitting 2nd arg means complete recursive(all sub folders n...
					.filter(Files::isRegularFile)
					.map(Main::listDir)
					.forEach(System.out::println);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println("-".repeat(50));
		try (Stream<Path> pathStream = Files.find(path, 2,
							(p, bfa)-> LocalDateTime.ofInstant(bfa.creationTime().toInstant(), ZoneId.systemDefault()).isAfter(
									(LocalDateTime.now().minusDays(1)))//BasicFileAttribute is already associated with each Stream Path
										&& bfa.isRegularFile() && bfa.size()>184)){
			pathStream
					.map(Main::listDir)
					.forEach(System.out::println);
		}catch (IOException e){
			throw new RuntimeException(e);
		}
		System.out.println("-".repeat(50));

		path = path.resolve(".idea"); //Change the directory u r starting at
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path, "*{.xml,re}")) {//ending with .xml or re
			directoryStream.forEach(dirS-> System.out.println(listDir(dirS)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}System.out.println("-".repeat(50));

		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path,
				p-> p.getFileName().toString().endsWith(".xml")//getFileName() returns a Path
					&&Files.size(p)>280)){
			directoryStream.forEach(dirS-> System.out.println(listDir(dirS)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	private static String listDir(Path path){
		boolean isDir = Files.isDirectory(path);
		try {
			LocalDateTime lastModifiedTime = LocalDateTime.ofInstant(Files.getLastModifiedTime(path).toInstant(),
					ZoneId.systemDefault());
			return "%tD %tT %-5s %12s %s".formatted(
					lastModifiedTime, lastModifiedTime, (isDir? "<DIR>": ""), ( Files.size(path)), path
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
