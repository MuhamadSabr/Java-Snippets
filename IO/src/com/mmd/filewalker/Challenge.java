package com.mmd.filewalker;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Challenge {
	public static void main(String[] args) {
		Path startingPath = Path.of("C:\\");
		StatsVisitor statsVisitor = new StatsVisitor();
		try {//Resources will be closed as part of its execution so no try-with-resources is needed.
			Files.walkFileTree(startingPath, statsVisitor);//Takes a start path, n an instance of a class whose super is FileVisitor
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("-".repeat(30));
		var sizes = statsVisitor.getSizes();
		sizes.forEach((k, v)-> System.out.println(k + ": " + v));

		System.out.println("-".repeat(50));
		var fileNFolder = statsVisitor.getFolderContents();
		fileNFolder.forEach((path, content)-> {
			System.out.print(path + " contains ");
			System.out.println(content.get(StatsVisitor.DIR) + " Folders, " + content.get(StatsVisitor.FILE) + " Files");
		}
		);
	}


	private static class StatsVisitor implements FileVisitor<Path> {//U would want to extend SimpleFileVisitor<T> only if u want
											//to override a few methods, otherwise u can implement FileVisitor interface directly
		private Map<Path, Long> folderSizes = new LinkedHashMap<>();
		private Map<Path, Map<String, Long>> folderContents = new LinkedHashMap<>();
		private Path initialPath = null;
		public final static String FILE ="FILES";
		final public static String DIR = "DIR";
		private int folderCount;

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {//Hope u get it that
				Objects.requireNonNull(file);								//visitFile only visits files n not directories.
				Objects.requireNonNull(attrs);			//U can print directories in its own 2 methods, pre or post.
//				System.out.println( "\t".repeat(level)+ file.getFileName() + " Path file");
//				folderSizes.merge(file.getParent(), 0L, (o, n)-> o+attrs.size());

				folderSizes.merge(file.getParent(), attrs.size(), Long::sum);

			folderContents.merge(file.getParent(), new LinkedHashMap<>(),
					(o, n) -> new LinkedHashMap<>(Map.of(DIR, o.get(DIR), FILE, o.get(FILE)+1)));

				return FileVisitResult.CONTINUE;//This determines how the other methods will be processed(or skipped)
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			Objects.requireNonNull(dir);
			Objects.requireNonNull(attrs);

			folderSizes.put(dir, 0L);

			folderContents.put(dir, new LinkedHashMap<>(Map.of(DIR, 0L, FILE, 0L)));

			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			Objects.requireNonNull(dir);
			if (exc != null)
				throw exc;

			if (dir.getParent() != null){
				folderSizes.merge(dir.getParent(), folderSizes.get(dir), Long::sum);
				folderContents.merge(dir.getParent(), folderContents.get(dir),
					(o, n) -> new LinkedHashMap<>(Map.of(DIR, o.get(DIR) + n.get(DIR) +1,
														 FILE, o.get(FILE) + n.get(FILE))));
		}
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			Objects.requireNonNull(file);
			System.out.println(exc.getClass() +" " + exc.getMessage());
			return FileVisitResult.CONTINUE;//One of the great things about FileTreeWalker is u can ignore exceptions n continue
		}

		public Map<Path, Long> getSizes(){
			return Map.copyOf(folderSizes);
		}
		public Map<Path, Map<String, Long>> getFolderContents(){
			return Map.copyOf( folderContents );
		}

	}


}