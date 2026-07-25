package md.chalk.note.repository.impl.files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import md.chalk.note.Note;
import md.chalk.note.NoteId;
import md.chalk.note.NoteSerializer;
import md.chalk.note.repository.NoteRepository;

/**
 * Note repository that stores notes on the disk
 */
@RequiredArgsConstructor
@Repository
public class FilesNoteRepository implements NoteRepository {

    private final FilesConfiguration config;
    private final NoteSerializer serializer;

    @Override
    public Note save(Note note) {
        Path path = Paths.get(config.getPath().toString(), "notes", note.getId().toString(), "note.md");
        String content = serializer.serialize(note);

        writeFile(path, content);

        return note;
    }

    @Override
    public Optional<Note> findById(NoteId id) {
        Path path = Paths.get(config.getPath().toString(), "notes", id.toString(), "note.md");

        if(!path.toFile().exists() || !path.toFile().isFile()) return Optional.empty();

        Note note = serializer.deserialize(readFile(path));

        if(note.getId() != id) {
            throw new RuntimeException("Frontmatter ID does not match the folder path ID! Something went wrong!");
        }

        return Optional.of(note);
    }

    @Override
    public List<Note> findAll() {
        Path path = Paths.get(config.getPath().toString(), "notes");
        
        if(!path.toFile().exists() || !path.toFile().isDirectory()) throw new RuntimeException("Notes folder does not exist! (path=" + path.toAbsolutePath().toString() + ")");

        return Arrays.stream(path.toFile().listFiles())
            .map(File::toPath)
            .map(p -> p.resolve("note.md"))
            .map(FilesNoteRepository::readFile)
            .map(serializer::deserialize)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteById(NoteId id) {
        Path path = Paths.get(config.getPath().toString(), "notes", id.toString());
        path.toFile().delete();
    }

    protected static void writeFile(Path path, String contents) {
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, contents, StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected static String readFile(Path path) {
        try {
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
