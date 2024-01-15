package com.mmd.filewalker;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
	public static void main(String[] args) {
		Path startingPath = Path.of(".");
		StatsVisitor statsVisitor = new StatsVisitor();
		try {//Resources will be closed as part of its execution so no try-with-resources is needed.
			Files.walkFileTree(startingPath, statsVisitor);//Takes a start path, n an instance of a class whose super is FileVisitor
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("-".repeat(30));
		var sizes = statsVisitor.getSizes();
		sizes.forEach((k, v)-> System.out.println(k + ": " + v));
	}


	private static class StatsVisitor extends SimpleFileVisitor<Path>{//U would want to extend SimpleFileVisitor<T> only if u want
											//to override a few methods, otherwise u can implement FileVisitor interface directly
		private Map<Path, Long> folderSizes = new LinkedHashMap<>();
		private Path initialPath = null;
		private int initialCount;

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {//Hope u get it that
				Objects.requireNonNull(file);								//visitFile only visits files n not directories.
				Objects.requireNonNull(attrs);			//U can print directories in its own 2 methods, pre or post.
//				System.out.println( "\t".repeat(level)+ file.getFileName() + " Path file");
//				folderSizes.merge(file.getParent(), 0L, (o, n)-> o+attrs.size());

				folderSizes.merge(file.getParent(), attrs.size(), Long::sum);

				System.out.println("file.getParent: " + file.getParent());
				return FileVisitResult.CONTINUE;//This determines how the other methods will be processed(or skipped)
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			Objects.requireNonNull(dir);
			Objects.requireNonNull(attrs);
			System.out.println(dir + ": " + dir.getNameCount());
			folderSizes.put(dir, 0L);

			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			Objects.requireNonNull(dir);
			if (exc != null)
				throw exc;
			System.out.println("Out : " + dir  + ": " + dir.getNameCount());

			if(dir.getParent()!=null)
				folderSizes.merge(dir.getParent(), folderSizes.get(dir), Long::sum);

			System.out.println("dir parent: " + dir.getParent() + ", dirsize: " + folderSizes.get(dir));


			return FileVisitResult.CONTINUE;
		}

		public Map<Path, Long> getSizes(){
			return folderSizes;
		}
	}


}