package amadeus;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(List<Task> tasks) throws IOException {

        File file = new File(filePath);
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            for (Task t : tasks) {
                writer.write(t.toFileFormat());
                writer.newLine();
            }
        }
    }
    public List<Task> load() {

        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                if (task != null) tasks.add(task);
            }
        }
        catch (IOException e) {
            System.out.println("⚠️ Error while reading files: " + e.getMessage());
        }
        return tasks;
    }
}
