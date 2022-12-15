package server;

import com.google.gson.Gson;
import taskmanager.Todos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс-сервер, который принимает запрос от клиента,
 * где одна операция соответствует одному запросу
 * и отправляет ему ответ в виде списка всех актуальных задач.
 */
public class TodoServer {
    private final int port;
    private final Todos todos;
    private List<String> operationsList = new ArrayList<>();
    private List<String> tasksList = new ArrayList<>();

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    /**
     * Метод запуска сервера
     */
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Starting server at " + port + "...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    Gson gson = new Gson();
                    Map<String, String> mapFromJson = gson.fromJson(in.readLine(), Map.class);

                    if (mapValidation(mapFromJson)) {
                        savingOperations(mapFromJson);
                    }
                    String allTasks = todos.getAllTasks();
                    out.println(allTasks);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод валидации данных по мапе
     *
     * @param mapFromJson список из json строки
     */
    private boolean mapValidation(Map<String, String> mapFromJson) {
        if (mapFromJson.containsValue("ADD")) {
            todos.addTask(mapFromJson.get("task"));
        } else if (mapFromJson.containsValue("REMOVE")) {
            todos.removeTask(mapFromJson.get("task"));
        } else if (mapFromJson.containsValue("RESTORE")) {
            tasksAndOperationsListValidation(operationsList, tasksList);
        } else {
            System.out.println("такой операции нет");
            return false;
        }
        return true;
    }

    /**
     * Метод валидации данных по листам, которые хранят операции и задачи
     *
     * @param operationsList лист, хранящий список всех оперций
     * @param tasksList      лист, хранящий список всех задач от клиента
     */
    private void tasksAndOperationsListValidation(List<String> operationsList, List<String> tasksList) {
        int tasksIndex = tasksList.size() - 1;
        int operationsIndex = operationsList.size() - 1;
        if (operationsList.get(operationsIndex).contains("ADD")) {
            todos.removeTask(tasksList.get(tasksIndex));
        } else if (operationsList.get(operationsIndex).contains("REMOVE")) {
            todos.addTask(tasksList.get(tasksIndex));
        }
        todos.setRawTaskList(todos.getTaskList());
        operationsList.remove(operationsIndex);
        tasksList.remove(tasksIndex);
    }

    /**
     * Метод сохранения данных по операциям и задачам, которые поступают из мапы
     *
     * @param mapFromJson список из json строки
     */
    private void savingOperations(Map<String, String> mapFromJson) {
        if (!(mapFromJson.get("type").equals("RESTORE"))) {
            operationsList.add(mapFromJson.get("type"));
            tasksList.add(mapFromJson.get("task"));
        }
    }
}