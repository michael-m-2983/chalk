package md.chalk.note.repository.impl.files;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "chalk.storage")
@Data
public class FilesConfiguration {
    private String path;

    public Path asPath() {
        return Path.of(path);
    }
}
