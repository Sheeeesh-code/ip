package amadeus;

public abstract class Task {
    protected final String description;
    protected boolean isDone;
    public abstract String toFileFormat();
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUndone() {
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public abstract String getTypeIcon();


    @Override
    public String toString() {
        return "[" + getTypeIcon() + "]" + getStatusIcon() + " " + description;
    }

    public static Task fromFileFormat(String line) {
        try {
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            boolean done = parts[1].trim().equals("1");
            String desc = parts[2].trim();

            switch (type) {
            case "T":
                Task t = new ToDo(desc);
                if (done) t.markAsDone();
                return t;
            case "D":
                String by = parts[3].trim();
                Task d = new Deadline(desc, by);
                if (done) d.markAsDone();
                return d;
            case "E":
                String fromStr = parts[3].trim();
                String toStr = parts.length > 4 ? parts[4].trim() : "";
                Task e = new Event(desc, fromStr, toStr);
                if (done) e.markAsDone();
                return e;
            }
        } catch (Exception e) {
            System.out.println("⚠️ L corrupted line : " + line);
        }
        return null;
    }
}