package server;

import com.google.gson.Gson;
import taskmanager.Todos;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * Класс-сервер, который принимает запрос от клиента
 * и отправляет ему ответ
 */
public class TodoServer {
    private final int port;
    private final Todos todos;

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
                    mapValidation(mapFromJson);
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
     * @param map список из json строки
     */
    private void mapValidation(Map<String, String> map) {
        if (map.containsValue("ADD")) {
            todos.addTask(map.get("task"));
        } else if (map.containsValue("REMOVE")) {
            todos.removeTask(map.get("task"));
        } else if (map.containsValue("RESTORE")) {
            todos.removeTwoLastTask();
        } else {
            System.out.println("такой операции нет");
        }
    }
}