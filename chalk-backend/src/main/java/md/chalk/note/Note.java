package md.chalk.note;

import java.time.Instant;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Note {
    private final NoteId id;
    private final Instant createdAt;
    private final Instant updatedAt;
    private final Set<String> tags;
    private final String content;

    public Note withContent(String newContent, Instant now) {
        return new Note(id, createdAt, now, tags, newContent);
    }

    public Note withTags(Set<String> newTags, Instant now) {
        return new Note(id, createdAt, now, newTags, content);
    }
}
