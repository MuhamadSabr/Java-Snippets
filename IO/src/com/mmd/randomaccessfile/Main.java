package com.mmd.randomaccessfile;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	private static final Map<Long, Long> indexedIds = new LinkedHashMap<>();//key represents record ID, 2nd starting file pointer position
	private static int recordsInFile;

	public static void main(String[] args) {

		BuildStudentData.build("studentdata", true);//Create the file once
		try(RandomAccessFile raf = new RandomAccessFile("studentdata.dat", "r")){
			loadIndex(new RandomAccessFile("studentdata.idx", "r"), 0);//Going to load the indices of the records into the indexedId map.
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter a student ID or 0 to quit");
			while (scanner.hasNext()){
				long studentId = Long.parseLong( scanner.nextLine() );
				if(studentId<1 || studentId>recordsInFile){
					break;
				}
				raf.seek(indexedIds.get(studentId));
				String targetedStudent = raf.readUTF();
				System.out.println(targetedStudent);
				System.out.println("Enter another student ID or 0 to quit");
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}


//		try(RandomAccessFile randomAccessFile = new RandomAccessFile("student.txt", "rw")){
//			randomAccessFile.seek(11);
//			int data = randomAccessFile.read();//Reading one byte by using read() method.
//			System.out.println( (char)data );
//			randomAccessFile.seek(19);
//
//			byte[] buffer = new byte[11];//Reading a byte array.
//			randomAccessFile.read(buffer);
//			System.out.println(new String(buffer));
//
//			randomAccessFile.seek(60);
//			randomAccessFile.write(buffer);
//
//		}catch (IOException e){
//			e.printStackTrace();
//		}
	}

	private static void loadIndex(RandomAccessFile raf, int indexPosition) throws IOException{
		raf.seek(indexPosition);
		recordsInFile = raf.readInt();
		System.out.println("Records in file: " + recordsInFile);
		for(int i=0; i<recordsInFile; i++){
			indexedIds.put(raf.readLong(), raf.readLong());
		}
	}

}
