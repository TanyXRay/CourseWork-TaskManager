package taskmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Todos {
    private List<String> taskList = new ArrayList<>();

    /**
     * Метод добавления задачи
     *
     * @param task
     */
    public void addTask(String task) {
        if (!task.isBlank()) {
            taskList.add(task);
        } else {
            System.out.println("Невозможно добавить пустую задачу");
        }
    }

    /**
     * Метод удаления задачи
     *
     * @param task
     */
    public void removeTask(String task) {
        if (taskList.isEmpty()) {
            System.out.print("\nудалять больше нечего");
        }
        taskList.remove(task);
    }

    /**
     * Метод выводит на экран все актуальные задачи,
     * отстортированные в лексикографическом (словарном) порядке
     */
    public String getAllTasks() {
        Collections.sort(taskList);
        return String.join(" ", taskList);
    }
}
