package ProjectАrchitecture;
import java.time.LocalDate;

public class Task {
    private String description; //название задачи
    private int priority; //приоритет

    public Task(String description, int priority){
        this.description = description;
        this.priority = priority;
    }

    //Геттеры и сеттеры
    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getPriority(){
        return priority;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }


    @Override
    public String toString(){
        return "Task: " + description + " | Priority: " + priority;
    }

}
