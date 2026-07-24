package md.chalk.note.dto;

import lombok.Data;
import md.chalk.note.NoteId;

@Data
public class CreateNoteResponse {
    private final NoteId id;
}
