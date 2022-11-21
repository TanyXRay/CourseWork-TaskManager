package taskmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Todos {
    private List<String> rawTaskList = new ArrayList<>();
    private List<String> taskList;

    /**
     * Метод добавления задачи
     *
     * @param task
     */
    public void addTask(String task) {
        if (!task.isBlank()) {
            rawTaskList.add(task);
        } else {
            System.out.println("Невозможно добавить пустую задачу");
        }
        taskList = rawTaskList.stream()
                .limit(8)
                .distinct()
                .collect(Collectors.toList());
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

    /**
     * Метод удаления 2 последних задач
     */
    public void removeTwoLastTask() {
       // TODO сделать функцию изъятия 2-х последних элементов из списка задач
    }
}
