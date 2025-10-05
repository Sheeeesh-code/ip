package amadeus;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String toFileFormat() {
        return "T | " + getStatusIcon() + " | " + description;
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }
}
