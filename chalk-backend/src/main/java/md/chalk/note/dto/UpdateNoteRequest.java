package md.chalk.note.dto;

import lombok.Data;

/**
 * Update request
 * 
 * The ID is in the request path (i.e. /api/note/{id})
 */
@Data
public class UpdateNoteRequest {
    private final String content;
}
