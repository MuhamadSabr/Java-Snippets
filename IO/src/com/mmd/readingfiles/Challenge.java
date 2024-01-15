package com.mmd.readingfiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.stream.Stream;

public class Challenge {
    public static void main(String[] args) {
        Path path = Path.of("public", "assets", "icon");
        try {
            Files.createDirectories(path);
            generateIndexFile(path.getName(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateIndexFile(Path startingPath) throws IOException{
        try(var x =  Files.walk(startingPath)){//Files.list only checks the current directory n not sub-directories.
            x.filter(Files::isDirectory)
                    .forEach(path -> {
                        try {
                            Files.createFile(path.resolve("index.txt"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }

}
