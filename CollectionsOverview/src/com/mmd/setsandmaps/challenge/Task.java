package com.mmd.setsandmaps.challenge;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task implements Comparable<Task>{
    private String assignee;
    private String projectName;
    private String taskDescription;
    private Status status;
    private Priority priority;

    public Task(String projectName, String taskDescription, Priority priority, String assignee) {
        this(projectName, taskDescription, priority, assignee.isBlank() ? Status.IN_QUEUE : Status.ASSIGNED, assignee);
    }

    public Task(String projectName, String taskDescription, Priority priority, Status status, String assignee) {
        this.assignee = assignee;
        this.projectName = projectName;
        this.taskDescription = taskDescription;
        this.status = status;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task o) {
        return (projectName + taskDescription).compareTo(o.projectName + o.taskDescription);
       // return Comparator.comparing(Task::getProjectName). thenComparing(Task::getTaskDescription);
    }

    @Override
    public String toString() {
        return "%-15s %-15s %-25s, %-10s, %-10s".formatted( assignee, projectName, taskDescription, priority, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!projectName.equals(task.projectName)) return false;
        return taskDescription.equals(task.taskDescription);
    }

    @Override
    public int hashCode() {
        int result = projectName.hashCode();
        result = 31 * result + taskDescription.hashCode();
        return result;
    }

//    public static Set<Task> getIntersect(Set<Task> task1, Set<Task> task2){
//        Set<Task> tasks = new HashSet<>(task1);
//        tasks.retainAll(task2);
//        return tasks;
//    }

//    public static Set<Task> getIntersect(Set<Task>... tasks){
//        Set<Task> intersectSets = new HashSet<>(tasks[0]);
//        for(int i = 1; i< tasks.length; i++){
//            intersectSets.retainAll(tasks[i]);
//        }
//        return intersectSets;
//    }
public static Set<Task> getIntersect(List<Set<Task>> tasks){
    Set<Task> intersectSets = new HashSet<>(tasks.get(0));
//    for(int i = 1; i< tasks.size(); i++){
//        intersectSets.retainAll(tasks.get(i));
//    }
    for( Set<Task> set : tasks){
        intersectSets.retainAll(set);
    }
    return intersectSets;
}


    public static Set<Task> getDifference(Set<Task> task1, Set<Task> task2){
        Set<Task> tasks = new HashSet<>(task1);
        tasks.removeAll(task2);
        return tasks;
    }

    public static Set<Task> getUnion(List<Set<Task>> task){
        Set<Task> tasks = new HashSet<>();
        for( Set<Task> set : task){
            tasks.addAll(set);
        }
        return tasks;
    }

    public enum Priority{
        HIGH, LOW, MEDIUM
    }
    public enum Status{
        ASSIGNED, IN_PROGRESS, NOT_ASSIGNED,IN_QUEUE
    }
    public String getAssignee() {
        return assignee;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
