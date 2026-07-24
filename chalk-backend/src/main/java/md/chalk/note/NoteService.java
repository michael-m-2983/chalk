package md.chalk.note;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import md.chalk.note.repository.NoteRepository;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository repository;

    public Note create(String content) {
        Note note = new Note(NoteId.generate(), Instant.now(), Instant.now(), new HashSet<>(), content);
        repository.save(note);
        return note;
    }

    public Note update(NoteId id, String content) {
        Note note = repository.findById(id).get().withContent(content, Instant.now());
        repository.save(note);
        return note;
    }

    public Note get(NoteId id) {
        return repository.findById(id).get();
    }

    public List<Note> list() {
        return repository.findAll();
    }

    public void delete(NoteId id) {
        repository.deleteById(id);
    }
}
