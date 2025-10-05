package amadeus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, String fromStr, String toStr) throws AmadeusException {
        super(description);
        try {
            this.from = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new AmadeusException("Invalid start date format. Please use yyyy-MM-dd HHmm.");
        }
        LocalDateTime tmpTo = null;
        if (!toStr.isEmpty()) {
            try {
                tmpTo = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new AmadeusException("Invalid end date format. Please use yyyy-MM-dd HHmm.");
            }
        }
        this.to = tmpTo;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toFileFormat() {
        String toStr = to != null ? to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) : "";
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " +
                from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " + toStr;
    }

    @Override
    public String toString() {
        String toStr = to != null ? " to " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) : "";
        return "[" + getTypeIcon() + "]" + getStatusIcon() + " " + description +
                " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + toStr + ")";
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }
}

