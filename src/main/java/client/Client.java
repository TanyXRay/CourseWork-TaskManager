package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * Класс-клиент, который соединяется с сервером и отправляет
 * ему строку-запрос в формате JSON
 */
public class Client {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8989);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // out.println("{ \"type\": \"ADD\", \"task\": \"Пробежка\" }");
            //out.println("{ \"type\": \"ADD\", \"task\": \"Готовка\" }");
            // out.println("{ \"type\": \"ADD\", \"task\": \"task #" + pickRandomChar() + "\" }");
            // out.println("{ \"type\": \"ADD\", \"task\": \"task #" + pickRandomChar() + "\" }");
            // out.println("{ \"type\": \"REMOVE\", \"task\": \"task #C\" }");
            System.out.println(in.readLine());
        }
    }

    public static char pickRandomChar() {
        String chars = "ABCDEFG";
        return chars.charAt(new Random().nextInt(chars.length()));
    }
}