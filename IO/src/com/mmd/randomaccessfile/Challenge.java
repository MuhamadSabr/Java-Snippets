package com.mmd.randomaccessfile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


record Employee(int id, double salary, String firstName, String lastName){}

public class Challenge {
	private static Map<Integer, Long> indexedId = new LinkedHashMap<>();
	private static int recordsInFile;

	public static void main(String[] args) {

		try(RandomAccessFile raf = new RandomAccessFile("employees.dat", "rw")){
			loadIndex(raf);
			indexedId.entrySet().forEach(System.out::println);

			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter an employee ID or 0 to quit");
			while (true){
				int employeeId = scanner.nextInt();
				if(employeeId<1){
					break;
				}
				raf.seek(indexedId.get(employeeId));
				int id = raf.readInt();
				double salary = raf.readDouble();
				String firstName = raf.readUTF();
				String lastName  = raf.readUTF();
				Employee employee = new Employee(id, salary, firstName, lastName);
				System.out.println(employee);

				System.out.println("If u want to modify the salary type 1 ");
				if(scanner.nextInt()==1){
					System.out.println("Type the new salary");
					double newSalary = scanner.nextDouble();
					double oldSalary = modifySalary(raf, id, newSalary);
					System.out.println("New salary: " + newSalary + ", old salary: " + oldSalary);
				}

				System.out.println("Enter another student ID or 0 to quit");
			}

		}catch (IOException e){
			e.printStackTrace();
		}
	}

	private static void loadIndex(RandomAccessFile raf) throws  IOException{
		raf.seek(0);
		recordsInFile = raf.readInt();
		System.out.println("Number of records: " + recordsInFile);
		for(int i=0; i<recordsInFile; i++){
			indexedId.put(raf.readInt(), raf.readLong());
		}
	}

	private static double modifySalary(RandomAccessFile raf, int employeeId, double newSalary) throws IOException{
		raf.seek(indexedId.get(employeeId));
		raf.readInt();
		double oldSalary = raf.readDouble();
		raf.seek(raf.getFilePointer()-8);
		raf.writeDouble(newSalary);
		return oldSalary;
	}

}
