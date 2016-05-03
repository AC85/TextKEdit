package textkedit;

import java.io.File;
import java.util.List;
import java.nio.file.Path;

/**
 * Created by Alex on 25.04.16.
 */
public class AsciiFile {

    private final Path file;

    private final List<String> content;

    public AsciiFile(Path file, List<String> content) {
        this.file = file;
        this.content = content;
    }

    public File getFile() {
        return this.file.toFile();
    }

    public Path getPath() {
        return this.file;
    }

    public List<String> getContent() {
        return content;
    }
}
