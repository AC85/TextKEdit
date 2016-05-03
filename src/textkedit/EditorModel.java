package textkedit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Alex on 21.04.16.
 */
public class EditorModel {


    /**
     * will return the AsciiFile wrapped withing IOResult, can be used to access a possible exception
     * @param file
     * @return
     */
    public IOResult<AsciiFile> save(AsciiFile file) {

        try {
            Files.write(file.getPath(), file.getContent());

            return new IOResult<>(file, null);
        } catch (IOException e) {
            return new IOResult<>(file, e);
        }
    }

    /**
     * will return IOResult either with a AsciiFile inside, if successful,
     * if not the data will be null and contain an exception
     * @param file
     * @return
     */
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
