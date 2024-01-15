package com.mmd.managingfiles;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        File file = new File("student.json");
        File renamedFile = new File("files/students.json");//One of the problems with IO classes, is they don't throw an exception, instead they return a boolean result.
//        System.out.println( file.renameTo(renamedFile) );//Returns true if succeeded, if not returns false(If no file with such name or user-permission, connectivity...).


        Path path = file.toPath(); //Turn file into NIO.2 path instance by simply calling toPath on file. Path also have toFile. So u can work both ways.
        Path renamedPath = renamedFile.toPath();
        Path resources = Path.of("resources");

        try {//There is no rename method on Files, like Linux u v to ues move command n specify the name u want.
//            Files.createDirectory(renamedPath.getParent());//If it exists u get FileAlreadyExistsException.
            System.out.println( Files.move(path, renamedPath) );//If the new path is in a directory other than the one that the old path resides in.
        } catch (IOException e) {                                             //The Path contents(oll folders n sub-folders) must already exist. It does not create folders.
            System.out.println("Ignore");
        }

//        try {
//            Files.move(renamedPath.getParent(), Path.of("dir"));//As u know a Path is either a file or directory, the same
//        }catch (IOException e){// way u just moved n renamed a file u can also rename, move or move n rename a directory.
//            e.printStackTrace();
//        }

//        try {
//            Files.copy(renamedPath.getParent(), Path.of("resources") );//Copy of directories only does a shallow copy
//        }catch (IOException e){//Meaning its contents won't be copied along with it. Also, if it already exists, U have to use
//            e.printStackTrace();    //StandardCopyOption.Replace_EXISTING otherwise u will see alreayExistsException. Even after this
//            //U cannot replace a directory that has content in it. U cannot even delete a directory that has content either.
//        }//See below for custom deep copy.


        try {
            recurseDelete(resources);
            recursCopy(Path.of("files"), resources);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(BufferedReader reader = new BufferedReader(//transferTo(Writer) might be more efficient for very large files, especially
                new FileReader("files//students.json"));// if a file is being copied across different network drives.
            PrintWriter writer = new PrintWriter("students-backup.json")){//For other files stick to Files.copy()

            reader.transferTo(writer);//If u open the written to file, u see that u simply made a copy of the read file

        }catch (IOException e){
            e.printStackTrace();
        }

        String urlString = "https://api.census.gov/data/2019/pep/charagegroups?get=NAME,POP&for=state:*";
        URI uri = URI.create(urlString);
        try(InputStream urlInputStream = uri.toURL().openStream()){
            urlInputStream.transferTo(System.out);

        }catch (IOException e){
            e.printStackTrace();
        }

        Path USPopulationPath = Path.of("USPopulationByState.txt");//reading from a uri, opening a stream from it.
        try(InputStreamReader reader = new InputStreamReader(uri.toURL().openStream());// writing the output directly to a file
            var writer = Files.newBufferedWriter(USPopulationPath)){

            reader.transferTo(writer);

        }catch (IOException e){
            e.printStackTrace();
        }

        try(InputStreamReader reader = new InputStreamReader(uri.toURL().openStream());//Where the transferTo method really shines though,
            PrintWriter writer = new PrintWriter("USPopulationByState.csv")){// is when one of ur input streams, differs from the output stream type.

            reader.transferTo(new Writer() {
                @Override
                public void write(char[] cbuf, int off, int len) throws IOException {//Here u do u transformation
                    String jsonString = new String(cbuf, off, len).trim(); //Creating a local variable by the input that is passed to this
                    jsonString = jsonString.replace('[', ' ').trim();
                    jsonString = jsonString.replaceAll("]", "");//U could use each one of these to remove them.
                    writer.write(jsonString);
                }

                @Override
                public void flush() throws IOException {//For close n flush delegate it to Writer class in try-with-resources declaration
                    writer.flush();
                }

                @Override
                public void close() throws IOException {
                    writer.close();
                }
            });

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void recursCopy(Path source, Path target) throws IOException{
        Files.copy(source, target);
        if(Files.isDirectory(source)){
            try(Stream<Path> children = Files.list(source)){
                    children.forEach(p-> {
                        try {
                            recursCopy(p, target.resolve(p.getFileName()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            }
        }
    }

    public static void recurseDelete(Path target) throws IOException{
        if(Files.isDirectory(target)){
            try(Stream<Path> children = Files.list(target)){
                children.forEach(p-> {
                    try {
                        recurseDelete(p);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        Files.delete(target);
    }
}
