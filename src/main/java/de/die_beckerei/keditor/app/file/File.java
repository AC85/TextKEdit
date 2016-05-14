package de.die_beckerei.keditor.app.file;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Flo on 15.05.16.
 */
public class File {

    private Path path;

    private String filename;

    private List<String> content;

    public File() {

        this.filename = "New File";
        this.content = new ArrayList<>();
    }

    public String getFilename() {
        return this.filename;
    }

    public List<String> getContent() {
        this.content.add("ess");
        return this.content;
    }

}
