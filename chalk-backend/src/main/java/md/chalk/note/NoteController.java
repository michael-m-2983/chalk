package md.chalk.note;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import md.chalk.note.NoteId.NoteIdException;
import md.chalk.note.dto.CreateNoteRequest;
import md.chalk.note.dto.CreateNoteResponse;
import md.chalk.note.dto.ListNotesResponse;
import md.chalk.note.dto.UpdateNoteRequest;
import md.chalk.note.dto.ViewNoteResponse;

@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService service;

    @PostMapping
    public CreateNoteResponse create(@RequestBody CreateNoteRequest request) {
        Note note = service.create(request.getContent());
        return new CreateNoteResponse(note.getId());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody UpdateNoteRequest request) {
        NoteId noteId;
        try {
            noteId = NoteId.load(id);
        } catch (NoteIdException e) {
            //TODO: cleaner error handling
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        service.update(noteId, request.getContent());
    }

    @GetMapping
    public ListNotesResponse list() {
        return new ListNotesResponse(service.list().stream().map(note -> note.getId()).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ViewNoteResponse view(@PathVariable String id) {
        NoteId noteId;
        try {
            noteId = NoteId.load(id);
        } catch (NoteIdException e) {
            //TODO: cleaner error handling
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return new ViewNoteResponse(service.get(noteId));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        NoteId noteId;
        try {
            noteId = NoteId.load(id);
        } catch (NoteIdException e) {
            //TODO: cleaner error handling
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        service.delete(noteId);
    }
}
