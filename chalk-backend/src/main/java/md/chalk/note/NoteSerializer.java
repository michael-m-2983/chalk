package md.chalk.note;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import md.chalk.note.NoteId.NoteIdException;

/**
 * Serializes and deserializes notes to strings
 */
@Component
public class NoteSerializer {

    public String serialize(Note note) {
        StringBuilder builder = new StringBuilder();

        // frontmatter
        builder.append("---\n");
        builder.append("id: ").append(note.getId().toString()).append("\n");
        builder.append("created_at: ").append(note.getCreatedAt().toString()).append("\n");
        builder.append("updated_at: ").append(note.getUpdatedAt().toString()).append("\n");
        builder.append("tags:\n");
        note.getTags().forEach(tag -> builder.append("\t- ").append(tag).append("\n"));
        builder.append("---\n");

        // content
        builder.append(note.getContent());

        return builder.toString();
    }

    public Note deserialize(String raw) {
        if (!raw.startsWith("---")) {
            throw new NoteDeserializationException("Missing frontmatter!");
        }

        //TODO: I'd like to validate that the id in the frontmatter matches the file path

        Note.NoteBuilder builder = Note.builder();
        

        // Split the body into sections
        String[] components = raw.split("---\n");
        if(components.length < 2) throw new NoteDeserializationException("Missing the closing '---' in frontmatter");

        // Extract the frontmatter
        String frontmatter = components[1];

        // Calculate the length of the frontmatter plus the start and end of it
        int prefixLength = frontmatter.length() + ("---\n".length() * 2);

        // Use that length to extract the body
        builder.content(raw.substring(prefixLength));

        // Split the frontmatter into lines
        String[] frontmatterLines = frontmatter.trim().split("\n");
        if(frontmatterLines.length < 4) throw new NoteDeserializationException("Not enough fields in the frontmatter!");

        // Extract id
        if(!frontmatterLines[0].startsWith("id: ")) throw new NoteDeserializationException("Frontmatter does not start with ID field!");
        try {
            NoteId id = NoteId.load(frontmatterLines[0].substring("id: ".length()).trim());
            System.out.println("id=" + id);
            builder.id(id);
        } catch (NoteIdException e) {
            throw new NoteDeserializationException("Invalid Note ID!");
        }

        // Extract created_at
        if(!frontmatterLines[1].startsWith("created_at: ")) throw new NoteDeserializationException("Frontmatter does not have a 'created_at' field!");
        builder.createdAt(Instant.parse(frontmatterLines[1].substring("created_at: ".length())));

        // Extract updated_at
        if(!frontmatterLines[2].startsWith("updated_at: ")) throw new NoteDeserializationException("Frontmatter does not have an 'updated_at' field!");
        builder.updatedAt(Instant.parse(frontmatterLines[2].substring("updated_at: ".length())));

        // Extract tags
        if(!frontmatterLines[3].startsWith("tags:")) throw new NoteDeserializationException("Frontmatter does not have a 'tags' field!");
        Set<String> tags = new HashSet<>();
        for(int i = 4; i < frontmatterLines.length; i++) {
            if(!frontmatterLines[i].matches("^[\t ]+- .*$")) throw new NoteDeserializationException(String.format("Invalid tag entry in frontmatter on line %d!", i+2));
            String tag = frontmatterLines[i].replaceFirst("^[\t ]+- ", "").trim();
            //TODO: validate tag
            tags.add(tag);
        }
        builder.tags(tags);

        return builder.build();
    }

    public static class NoteDeserializationException extends RuntimeException {
        public NoteDeserializationException(String message) {
            super(message);
        }
    }
}
