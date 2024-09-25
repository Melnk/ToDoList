package ProjectАrchitecture;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;


public class TaskManager {
    private List<Task> tasks;
    Scanner scanner = new Scanner(System.in);

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(int index){
        if (index >= 0 && index < tasks.size()){
            tasks.remove(index);
            System.out.println("Задача удалена!");
        }
        else{
            System.out.println("Неверный индекс!");
        }

    }

    public void editTask(int index, String newDescription, int newPriorty){
        if (index >= 0 && index < tasks.size()){
            Task task = tasks.get(index);
            task.setDescription(newDescription);
            task.setPriority(newPriorty);
            System.out.println("Задача обновлена!");
        }
        else{
            System.out.println("Неверный индекс!");
        }
    }

    public void sortTasks(){
        Collections.sort(tasks, Comparator.comparingInt(Task::getPriority));
    }

    public void showTasks() {
        for (Task task : tasks){
            System.out.println(task);
        }
    }
    public int getTaskCount() {
        return tasks.size();
    }
    public  void saveTasksToFile(String filename){
        try (FileWriter writer = new FileWriter(filename)){ // try-with-resources
            for (Task task : tasks){
                writer.write(task.getDescription() + ";" + task.getPriority() + "\n");
            }
            System.out.println("Задачи сохранены");
        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }
    public void loadTasksFromFile(String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            tasks.clear();  // Очищаем текущий список задач перед загрузкой
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");
                String description = parts[0];
                int priority = Integer.parseInt(parts[1]);
                tasks.add(new Task(description, priority));
            }
            System.out.println("Задачи успешно загружены.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке задач: " + e.getMessage());
        }
    }
    public List<Task> searchTasks(String word){
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks){
            if (task.getDescription().toLowerCase().contains(word.toLowerCase())){
                foundTasks.add(task);
            }
            else{
                System.out.println("Задача не найдена");
            }
        }
        return foundTasks;
    }

}
