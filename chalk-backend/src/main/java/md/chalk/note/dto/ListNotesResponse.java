package md.chalk.note.dto;

import java.util.List;

import lombok.Data;
import md.chalk.note.NoteId;

@Data
public class ListNotesResponse {
    private final List<NoteId> ids;
}
