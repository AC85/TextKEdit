package textkedit;

import java.util.List;
import java.nio.file.Path;

/**
 * Created by Flo on 25.04.16.
 */
public class AsciiFile {

    private final Path  file;

    private final List<String> content;

    public AsciiFile(Path file, List<String> content) {
        this.file = file;
        this.content = content;
    }

    public Path getFile() {
        return this.file;
    }

    public List<String> getContent() {
        return content;
    }
}
