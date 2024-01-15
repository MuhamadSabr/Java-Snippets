package com.mmd.setsandmaps.challenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TaskData {

    private static String annTasks= """
    Infrastructure, Security, High, In Progress
    Infrastructure, Password Policy,Medium, In Progress
    Research, Cloud solutions, Medium, In Progress
    Data Design, Encryption Policy, High
    Data Design, Project Table, Medium
    Data Access, Write Views,Low, In Progress""";

    private static String bobTasks= """
    Infrastructure, Security, High, In Progress
    Infrastructure, Password Policy, Medium
    Data Design,Encryption Policy,High
    Data Access,Write Views, Low, In Progress""";

    private static String carolTasks= """
    Infrastructure, Logging, High, In Progress
    Infrastructure, DB Access, Medium
    Infrastructure, Password Policy, Medium
    Data Design, Task Table, High
    Data Access, Write Views, Low""";

    private static String allTasks="""
    Infrastructure, Logging, High
    Infrastructure, DB Access, Medium
    Infrastructure, Security, High
    Infrastructure, Password Policy, Medium
    Data Design, Task Table, Medium
    Data Design, Employee Table, Medium
    Data Design, Cross Reference Tables, High
    Data Design, Encryption Policy, High
    Data Access, Write Views, Low
    Data Access, Set Up Users, Low
    Data Access, Set Up Access Policy, Low""";


    public static Set<Task> getData(String employeeName){
        Set<Task> temp = new HashSet<>();
        Scanner scanner = new Scanner(employeeName.equalsIgnoreCase("carol") ? carolTasks
                : employeeName.equalsIgnoreCase("bob") ? bobTasks
                : employeeName.equalsIgnoreCase("ann") ? annTasks
                : allTasks);
        while (scanner.hasNext()){
            String[] data = scanner.nextLine().split(",");
            Arrays.asList(data).replaceAll(String::trim);
            Arrays.asList(data).replaceAll(s-> s.replace(" ", "_"));
            String assignee = employeeName.equalsIgnoreCase("carol") ? "Carol"
                    :         employeeName.equalsIgnoreCase("bob")   ? "Bob"
                    :         employeeName.equalsIgnoreCase("ann")   ? "Ann"
                    : "";

            if (data.length==4)
                temp.add(new Task(data[0], data[1], Task.Priority.valueOf(data[2].toUpperCase()), Task.Status.valueOf(data[3].toUpperCase()), assignee)  );
            else
                temp.add(new Task(data[0], data[1], Task.Priority.valueOf(data[2].toUpperCase()), assignee) );
        }
        return temp;
    }


}
