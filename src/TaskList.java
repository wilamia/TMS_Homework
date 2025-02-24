import java.util.ArrayDeque;

public class TaskList<String> {
    private ArrayDeque<String> list;

    TaskList() {
        list = new ArrayDeque<>();
    }

    public void addTask(String task) {
        list.addLast(task);
    }

    public void addUrgentTask(String task) {
        list.addFirst(task);
    }

    public String processTask() {
        list.removeFirst();
        return list.getFirst();
    }

    public void printTasks() {
        System.out.println(list);
    }
}
