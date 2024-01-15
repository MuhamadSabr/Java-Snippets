package com.mmd.setsandmaps.challenge;

import com.mmd.setsandmaps.Contact;
import jdk.swing.interop.LightweightFrameWrapper;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Task> tasks = TaskData.getData("all");
        Set<Task> carolTasks = TaskData.getData("Carol");
        Set<Task> bobTasks = TaskData.getData("bob");
        Set<Task> annTasks = TaskData.getData("ann");

        printData("All tasks", tasks);

        //printData("Sorted by priority", tasks, Comparator.comparing(Task::getPriority).reversed());

        List<Set<Task>> listOfSet = List.of(carolTasks, bobTasks, annTasks, tasks);
        Set<Task> allTasks = Task.getUnion(listOfSet);
        printData("The full task list ", allTasks);

        Set<Task> missingTasks = new HashSet<>();
        missingTasks = Task.getDifference(allTasks, tasks);
        printData("Missing tasks", missingTasks);

        Set<Task> assignedTasks = new HashSet<>( getAssignedProjects(allTasks) );
        printData("Projects that have assignees" ,assignedTasks);

        Set<Task> notAssignedTasks = new HashSet<>(getAssignedProjects(allTasks, false));
        printData("Tasks that do not have assignees", notAssignedTasks);

        Set<Task> assignedToMultipleEmp = new HashSet<>();
        assignedToMultipleEmp = Task.getUnion( List.of( Task.getIntersect(List.of(carolTasks, bobTasks)),
                                                        Task.getIntersect(List.of(carolTasks, annTasks)),
                                                        Task.getIntersect(List.of(annTasks, bobTasks))));
        printData("Tasks assigned to more than one employee", assignedToMultipleEmp);

        List<Task> allEmployeeOverlaps = new ArrayList<>();
        for(Set<Task> set : List.of(carolTasks, bobTasks, annTasks)){
            allEmployeeOverlaps.addAll( Task.getIntersect(List.of(set, assignedToMultipleEmp)) );
        }
        printData("All employees that have tasks in the common list", allEmployeeOverlaps, Comparator.comparing(Task::getAssignee));

    }



    public static Set<Task> getAssignedProjects(Set<Task> tasks){
        return getAssignedProjects(tasks, true);
    }
    public static Set<Task> getAssignedProjects(Set<Task> tasks, boolean condition){
        Set<Task> assignedTasks = new HashSet<>();
        Set<Task> notAssignedTasks = new HashSet<>();
        for (Task task : tasks){
            if(!task.getAssignee().isBlank()){
                assignedTasks.add(task);
            }
            else notAssignedTasks.add(task);
        }
        return condition ? assignedTasks : notAssignedTasks;
    }

    public static void printData(String header, Collection<Task> tasks){
        printData(header, tasks, null); // Remember null means the Comparable that is implemented is used to do the sorting.
    }
    public static void printData(String header, Collection<Task> tasks, Comparator<Task> sorter){
        System.out.println("-".repeat(90));
        System.out.println(header);
        System.out.println("-".repeat(90));

        List<Task> output = new ArrayList<>(tasks);
        output.sort(sorter);
        output.forEach(System.out::println);



    }
}
