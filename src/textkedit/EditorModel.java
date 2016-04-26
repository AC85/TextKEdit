package textkedit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Flo on 21.04.16.
 */
public class EditorModel {


    public void save(AsciiFile file) {

        try {
            Files.write(file.getPath(), file.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IOResult<AsciiFile> load(Path file) {
        try {
            List<String> lines = Files.readAllLines(file);

            return new IOResult<>(new AsciiFile(file, lines), null);

        } catch (IOException e) {
            e.printStackTrace();
            return new IOResult<>(null, e);
        }
    }
}
