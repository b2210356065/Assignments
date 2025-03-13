import java.io.Serializable;
import java.util.*;

public class Project implements Serializable {
    static final long serialVersionUID = 33L;
    private final String name;
    private final List<Task> tasks;

    public Project(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    /**
     * @return the total duration of the project in days
     */
    public int getProjectDuration() {
        int projectDuration = 0;
        int max=0;
        int cax=0;
        int[] k=getEarliestSchedule();
        for (int i=0;i<k.length;i++){
            if(max<k[i]){
                max=k[i];
                cax=tasks.get(i).getDuration();
            }
        }
        projectDuration+=max;
        projectDuration+=cax;
        return projectDuration;
    }

    /**
     * Schedule all tasks within this project such that they will be completed as early as possible.
     *
     * @return An integer array consisting of the earliest start days for each task.
     */
    public int[] getEarliestSchedule() {

        // TODO: YOUR CODE HERE
        Integer[] schedule=new Integer[tasks.size()];
        for(int i=0;i<schedule.length;i++){
            schedule[i]=0;
        }
        queue(tasks,tasks.size()-1,schedule);
        int [] k = new int[schedule.length];
        for(int i=0;i<schedule.length;i++){
            if(k[i]==0){
                queue(tasks,i,schedule);
            }
            k[i]=schedule[i];
        }
        return k;
    }
    public int queue(List<Task> tasks,int i,Integer[] k){
        for(int j:tasks.get(i).getDependencies()){
            if(k[j]==0){
                k[j]=queue(tasks,j,k);
            }
            int l=k[j]+tasks.get(j).getDuration();
            if(l>k[i]){
                k[i]=l;
            }
        }
        return k[i];
    }

    public static void printlnDash(int limit, char symbol) {
        for (int i = 0; i < limit; i++) System.out.print(symbol);
        System.out.println();
    }

    /**
     * Some free code here. YAAAY! 
     */
    public void printSchedule(int[] schedule) {
        int limit = 65;
        char symbol = '-';
        printlnDash(limit, symbol);
        System.out.println(String.format("Project name: %s", name));
        printlnDash(limit, symbol);

        // Print header
        System.out.println(String.format("%-10s%-45s%-7s%-5s","Task ID","Description","Start","End"));
        printlnDash(limit, symbol);
        for (int i = 0; i < schedule.length; i++) {
            Task t = tasks.get(i);
            System.out.println(String.format("%-10d%-45s%-7d%-5d", i, t.getDescription(), schedule[i], schedule[i]+t.getDuration()));
        }
        printlnDash(limit, symbol);
        System.out.println(String.format("Project will be completed in %d days.", getProjectDuration()));
        printlnDash(limit, symbol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;

        int equal = 0;

        for (Task otherTask : ((Project) o).tasks) {
            if (tasks.stream().anyMatch(t -> t.equals(otherTask))) {
                equal++;
            }
        }

        return name.equals(project.name) && equal == tasks.size();
    }

}
