package taskmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Todos {
    private List<String> rawTaskList = new ArrayList<>();
    private List<String> taskList;

    public List<String> getRawTaskList() {
        return rawTaskList;
    }

    public void setRawTaskList(List<String> rawTaskList) {
        this.rawTaskList = rawTaskList;
    }

    public List<String> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<String> taskList) {
        this.taskList = taskList;
    }

    /**
     * Метод добавления задачи
     *
     * @param task
     */
    public void addTask(String task) {
        int limit = 8;
        rawTaskList.add(task);
        taskList = rawTaskList.stream()
                .limit(limit)
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
        setRawTaskList(taskList);
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