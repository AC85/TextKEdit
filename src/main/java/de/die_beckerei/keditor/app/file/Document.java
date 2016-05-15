package de.die_beckerei.keditor.app.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flo on 15.05.16.
 */
public class Document {

    private Path path;

    private String filename;

    private List<String> content;

    public Document() {

        this.filename = "New Document";
        this.content = new ArrayList<>();
    }

    private Document(Path file, String filename, List<String> lines) {
        this.path = file;
        this.filename = filename;
        this.content = lines;
    }

    public String getFilename() {
        return this.filename;
    }

    public List<String> getContent() {
        this.content.add("ess");
        return this.content;
    }

    public static Document load(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file, Charset.forName("ISO-8859-1")); //TODO: is this appropriate?
        String filename = file.getFileName().toString();

        return  new Document(file, filename, lines);
    }

}
