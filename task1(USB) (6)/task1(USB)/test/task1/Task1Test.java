import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import task1.Task1;

class Task1Test {
    @Test
    void testAddTasks() {
        Task1.addTasks();
        assertEquals(3, Task1.taskNamesList.size());
        assertEquals(3, Task1.developerList.size());
        assertEquals(3, Task1.taskDurationList.size());
        assertEquals(3, Task1.taskStatusList.size());
    }

    @Test
    void testShowReports() {
        Task1.addTasks();
        Task1.showReports();
        // Manually verify the report output
    }

    @Test
    void testGenerateTaskID() {
        Task1.Task task = new Task1.Task();
        String taskID = task.generateTaskID();
        assertNotNull(taskID);
        assertTrue(taskID.startsWith("T"));
    }
}