package md.chalk.note.dto;

import lombok.Data;
import md.chalk.note.Note;

@Data
public class ViewNoteResponse {
    private final Note note;
}
