package md.chalk.note.repository;

import java.util.List;
import java.util.Optional;

import md.chalk.note.Note;
import md.chalk.note.NoteId;

public interface NoteRepository {
    Note save(Note note);
    Optional<Note> findById(NoteId id);
    List<Note> findAll();
    void deleteById(NoteId id);
}
