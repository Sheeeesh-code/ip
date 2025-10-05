package amadeus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void handleCommand(String input, Storage storage, Ui ui) throws IOException, AmadeusException {
        if ("list".equalsIgnoreCase(input)) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        } else if (input.toLowerCase().startsWith("mark")) {
            int idx = Integer.parseInt(input.split(" ")[1]) - 1;
            tasks.get(idx).markAsDone();
            storage.save(tasks);
            Ui.taskUpdated("Nice! I've marked this task as done:", tasks.get(idx));
        } else if (input.toLowerCase().startsWith("unmark")) {
            int idx = Integer.parseInt(input.split(" ")[1]) - 1;
            tasks.get(idx).markAsUndone();
            storage.save(tasks);
            Ui.taskUpdated("OK, I've marked this task as not done yet:", tasks.get(idx));
        } else if (input.toLowerCase().startsWith("todo")) {
            String desc = input.substring(5).trim();
            if (desc.isEmpty()) throw new AmadeusException("The description of a todo cannot be empty.");
            tasks.add(new ToDo(desc));
            storage.save(tasks);
            Ui.taskAdded(tasks);
        } else if (input.toLowerCase().startsWith("deadline")) {
            String[] parts = input.substring(9).split("/by");
            String desc = parts[0].trim();
            String by = parts.length > 1 ? parts[1].trim() : "";
            if (desc.isEmpty() || by.isEmpty())
                throw new AmadeusException("Usage: deadline <desc> /by yyyy-MM-dd HHmm");
            tasks.add(new Deadline(desc, by));
            storage.save(tasks);
            Ui.taskAdded(tasks);
        } else if (input.toLowerCase().startsWith("event")) {
            String[] parts = input.substring(6).split("/from");
            String desc = parts[0].trim();
            if (parts.length < 2) throw new AmadeusException("Usage: event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
            String[] timeParts = parts[1].split("/to");
            String from = timeParts[0].trim();
            String to = timeParts.length > 1 ? timeParts[1].trim() : "";
            tasks.add(new Event(desc, from, to));
            storage.save(tasks);
            Ui.taskAdded(tasks);
        } else if (input.toLowerCase().startsWith("delete")) {
            int idx = Integer.parseInt(input.split(" ")[1]) - 1;
            Task removed = tasks.remove(idx);
            Ui.taskDeleted(removed, tasks.size());
        } else {
            throw new AmadeusException("Sorry, I don't know that command.");
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getAll() {
        return tasks;
    }
}
