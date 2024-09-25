package main;
import ProjectАrchitecture.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {

    public static void main(String[] args){
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTask(taskManager, scanner);
                    break;
                case 2:
                    editTask(taskManager, scanner);
                    break;
                case 3:
                    deleteTask(taskManager, scanner);
                    break;
                case 4:
                    taskManager.sortTasks();
                    taskManager.showTasks();
                    break;
                case 5:
                    taskManager.saveTasksToFile("file.txt");
                    break;
                case 6:
                    taskManager.loadTasksFromFile("file.txt");
                    break;
                case 7:
                    searchTasks(taskManager, scanner);
                    break;
                case 8:
                    System.out.println("Ещё увидемся ฅ^•ﻌ•^ฅ");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");

            }
        }

    }
    private static void addTask(TaskManager taskManager, Scanner scanner){
        System.out.println("Введите название задачи: ");
        String description = scanner.nextLine();

        int propirty = 0;
        while (true){
            System.out.println("Введите приоритет задачи (1 - Высокий, 2 - Средний, 3 - Низкий)");
            try {
                propirty = scanner.nextInt();
                if (propirty < 1 || propirty > 3){
                    System.out.println("Приоритет должен быть от 1 до 3");
                    continue;
                }
                break;
            }catch (InputMismatchException e) {
                System.out.println("Пожалуйтса введите число (1, 2, 3)");
                scanner.nextLine(); // очистка
            }
        }
        Task task = new Task(description, propirty);
        taskManager.addTask(task);
        System.out.println("Задача добавлена!");
        scanner.nextLine();
    }
    private static void editTask(TaskManager taskManager, Scanner scanner){
        if (taskManager.getTaskCount() == 0){
            System.out.println("Нет задач для редактирования");
            return;
        }
        System.out.println("Введите индекс задачи для редактирования");
        taskManager.showTasks();
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= taskManager.getTaskCount()){
            System.out.println("Индекс введен неверное");
            return;
        }

        System.out.println("Введите новое описание задачи");
        String newDescription = scanner.nextLine();
        System.out.println("Введите новый приоритет задачи");
        int newPriority = scanner.nextInt();
        taskManager.editTask(index, newDescription, newPriority);
        System.out.println("Задача обновлена!");
    }

    private static void deleteTask(TaskManager taskManager, Scanner scanner){
        if (taskManager.getTaskCount() == 0){
            System.out.println("Нет элементов для удаления");
            return;
        }
        System.out.println("Введите индекс элемента для удаления");
        taskManager.showTasks();
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= taskManager.getTaskCount()){
            System.out.println("Индекс введен неверное");
            return;
        }
        taskManager.removeTask(index);
        System.out.println("Элемент удален!");

    }
    public static void showMenu() {
        System.out.println("Выберите опцию:");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Редактировать задачу");
        System.out.println("3. Удалить задачу");
        System.out.println("4. Показать список задач");
        System.out.println("5. Сохранить задачи в файл");
        System.out.println("6. Загрузить задачи из файла");
        System.out.println("7. Поиск задач");
        System.out.println("8. Выйти");
    }
    public static void searchTasks(TaskManager taskManager, Scanner scanner){
        System.out.println("Ключевое слово для поиска: ");
        String word = scanner.nextLine();
        List<Task> foundTasks = taskManager.searchTasks(word);

        if (foundTasks.isEmpty()){
            System.out.println("Задача не найдена");
        }
        else{
            System.out.println("Найденный задачи:");
            for (Task task : foundTasks){
                System.out.println(task);
            }
        }
    }

}
