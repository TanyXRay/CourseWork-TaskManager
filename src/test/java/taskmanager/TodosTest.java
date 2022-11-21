package taskmanager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TodosTest {
    private List<String> listOfTasks;

    @Test
    public void checkAddToList() {
        listOfTasks = new ArrayList<>();
        listOfTasks.add("Прогулка");

        Assertions.assertEquals(listOfTasks.add("Прогулка"), listOfTasks.contains("Прогулка"));
        Assertions.assertFalse(listOfTasks.isEmpty());
    }

    @Test
    public void checkRemoveFromList() {
        listOfTasks = new ArrayList<>();
        listOfTasks.add("Прогулка");
        listOfTasks.remove("Прогулка");

        Assertions.assertTrue(listOfTasks.isEmpty());
    }

    @Test
    public void checkReturnAllSortElementFromList() {
        Todos todos = new Todos();
        todos.addTask("Прогулка");
        todos.addTask("Готовка");
        String expected = "Готовка Прогулка";

        Assertions.assertNotNull(todos.getAllTasks());
        Assertions.assertEquals(expected, todos.getAllTasks());
    }
}
