package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Класс-клиент, который соединяется с сервером и отправляет
 * ему строку-запрос в формате JSON,
 * в которой описывается тип операции и наименование задачи
 */
public class Client {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8989);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            //out.println("{ \"type\": \"ADD\", \"task\": \"Готовка\" }");
            out.println("{ \"type\": \"ADD\", \"task\": \"Плаванье\" }");
            //out.println("{ \"type\": \"ADD\", \"task\": \"Акробатика\" }");
            //out.println("{ \"type\": \"ADD\", \"task\": \"Магазин-покупки\" }");
            //out.println("{ \"type\": \"ADD\", \"task\": \"Учеба\" }");
            //out.println("{ \"type\": \"ADD\", \"task\": \"Рыбалка\" }");

            //out.println("{ \"type\": \"RESTORE\" }");

            //out.println("{ \"type\": \"REMOVE\", \"task\": \"Чтение\" }");
            //out.println("{ \"type\": \"REMOVE\", \"task\": \"Лингвистика\" }");
            //out.println("{ \"type\": \"REMOVE\", \"task\": \"Акробатика\" }");
            //out.println("{ \"type\": \"REMOVE\", \"task\": \"Готовка\" }");

            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}