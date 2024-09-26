package main;

import ProjectАrchitecture.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListGUI {

    private TaskManager taskManager;
    private JFrame frame;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;

    public ToDoListGUI() {
        taskManager = new TaskManager();
        frame = new JFrame("Task Scheduler");
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        initialize();
    }

    public void initialize() {
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Панель с кнопками
        JPanel panel = new JPanel();
        JButton addTaskButton = new JButton("Добавить задачу");
        JButton editTaskButton = new JButton("Редактировать задачу");
        JButton deleteTaskButton = new JButton("Удалить задачу");
        panel.add(addTaskButton);
        panel.add(editTaskButton);
        panel.add(deleteTaskButton);

        // Добавляем элементы на форму
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Добавление функциональности
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        editTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTask();
            }
        });

        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        frame.setVisible(true);
    }

    private void addTask() {
        String description = JOptionPane.showInputDialog(frame, "Введите описание задачи:");
        if (description != null && !description.trim().isEmpty()) {
            taskManager.addTask(new Task(description, 1));
            updateTaskList();
        }
    }

    private void editTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String newDescription = JOptionPane.showInputDialog(frame, "Введите новое описание задачи:");
            if (newDescription != null && !newDescription.trim().isEmpty()) {
                taskManager.editTask(selectedIndex, newDescription, 1);
                updateTaskList();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Выберите задачу для редактирования.");
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskManager.removeTask(selectedIndex);
            updateTaskList();
        } else {
            JOptionPane.showMessageDialog(frame, "Выберите задачу для удаления.");
        }
    }

    private void updateTaskList() {
        taskListModel.clear();
        for (Task task : taskManager.getTasks()) {
            taskListModel.addElement(task.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListGUI();
            }
        });
    }
}
