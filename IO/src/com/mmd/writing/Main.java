package com.mmd.writing;

import com.mmd.student.Course;
import com.mmd.student.Student;
import jdk.javadoc.doclet.Doclet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {

		String header = """
				Student Id,Country Code,Enrolled Year,Age,Gender,\
				Experienced,Course Code,Engagement Month,Engagement Year,\
				Engagement Type""";//The backslashes r for not include newline. Note that nothing should come after it.

		Course jmc = new Course("JMC", "Java Masterclass");
		Course pymc= new Course("PYMC", "Python Masterclass");

		List<Student> students = Stream.generate(()-> Student.getRandomStudent(jmc, pymc))
				.limit(25)
				.toList();

		Path studentPath = Path.of("student.csv");
//		try {
//			Files.writeString(studentPath, header);//Writing methods open n close the file
//			for(Student std : students){							//directly after writing to it. It will open n close the file
//				Files.write(studentPath, std.getEngagementRecords(), StandardOpenOption.APPEND);//for every write method.
//			}								//This is pretty inefficient. So it is better to write only once to the file. See below
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		try {
			List<String> data = new ArrayList<>();
			data.add(header);
			for(Student std : students){
				data.addAll( std.getEngagementRecords() );
			}
			Files.write(studentPath, data); //Collections are iterable n u can pass an iterable to the write method.
		}catch (IOException e){									//to write everything at once, instead of incremental write.
			e.printStackTrace();
		}

		//BufferedWriter does not accept an iterable. Also there is new end line by default. Is used for incremental writing.
		try (BufferedWriter bufWriter = new BufferedWriter(Files.newBufferedWriter(Path.of("student2.csv")))) {
			bufWriter.write(header);
			bufWriter.newLine();
			int count = 0;
			for(Student std : students){
				for (String record : std.getEngagementRecords()){//Again u cannot just pass getEngagementRecords which returns a List.
					bufWriter.write(record);									//since bufferedWriter.write does not accept iterables
					bufWriter.newLine();
					count++;
					if(count%5==0){
						Thread.sleep(2000);//If something happens, like shutdown while the buffer has unflushed data the data gets lost.
						System.out.print(".");
//						bufWriter.flush();
					}
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		try (FileWriter fileWriter = new FileWriter("student3.csv")) {
			fileWriter.write(header);
			fileWriter.append(System.lineSeparator());
			for(Student std : students){
				for (String record : std.getEngagementRecords()){
					fileWriter.write(record);
					fileWriter.write(System.lineSeparator());
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}

		try (PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(Path.of("student4.csv")))) {
			printWriter.write(header);
			printWriter.println();
			for(Student std : students){
				for (String record : std.getEngagementRecords()){
					printWriter.println(record);
				}
			}

		}catch (IOException e){
			e.printStackTrace();
		}

		try (PrintWriter printWriter = new PrintWriter("student.txt")){//If u pass a String to the constructor, PrintWriter
			printWriter.write(header);				//under the covers creates a BufferedWriter
			printWriter.println();
			for(Student std : students){
				for (String record : std.getEngagementRecords()){
					String[] recordData = record.split(",");
					printWriter.printf("%-12d%-5s%4d%2d%3d%-1s",
							std.getStudentId(),
							std.getCountry(),
							std.getEnrollmentYear(),
							std.getEnrollmentMonth(),
							std.getAge(),
							std.getGender());

					printWriter.format("%-1s", (std.hasExperience() ? "Y" : "N"));

					printWriter.format("%-4s%10.2f%-10s%-4s%-30s",
							recordData[7], //Course code
							std.getPercentComplete(recordData[7]),
							recordData[8],//EngagementMonth
							recordData[9],//EngagementYear
							recordData[10]//Engagement Type
							);

					printWriter.println();
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}

	}
}
