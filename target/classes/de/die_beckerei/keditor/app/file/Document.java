package de.die_beckerei.keditor.app.file;

import com.google.common.primitives.Bytes;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.bouncycastle.util.encoders.Hex;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Flo on 15.05.16.
 */
public class Document {

    public static final String charset = "ISO-8859-1";

    private Path path;

    private SimpleStringProperty filename;

    private List<String> content;

    private Document(Path path) {
        this(path, new ArrayList<>());
    }

    private Document(Path path, List<String> content) {
        this.path = path;
        if(this.path != null) {
            this.filename = new SimpleStringProperty(this.path.getFileName().normalize().toString());
        } else {
            this.filename = new SimpleStringProperty("New File");
        }
        this.content = content;
    }

    private Document(Path file, String filename, List<String> lines) {
        this.path = file;
        this.filename = new SimpleStringProperty(filename);
        this.content = lines;
    }

    public Path getPath() { return this.path; }

    public String getFilename() {
        return this.filename.get();
    }

    public StringProperty filenameProperty() {
        return  this.filename;
    }

    public List<String> getContent() {
        return this.content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public boolean isEmpty() {
        return this.content.isEmpty();
    }

    public boolean isTransient() {
        return (this.path == null);
    }

    public void updateFilename() {
        this.filename.setValue(this.path.getFileName().normalize().toString());
    }

    /**
     * Convert document content to byte Array
     * @return byte[]
     */
    public byte[] toByte() {

        List<Byte> output = new ArrayList<>();

        for(String line : this.content) {
            byte[] c = line.getBytes(Charset.forName(Document.charset));
            for(byte b : c) {
                output.add(b);
            }
            //add new line separator
            output.add( (byte) '\n');
        }

        return Bytes.toArray(output);
    }

    public static Document load(Path file) throws IOException {
        List<String> lines = Files.readAllLines(file, Charset.forName(Document.charset)); //TODO: is this appropriate?
        String filename = file.getFileName().toString();

        return  new Document(file, filename, lines);
    }

    public static Document newInstance(Path path, byte[] input) {

        //convert bytes[] to List<String>
        String oneLine = new String(input, Charset.forName(Document.charset));
        List<String> lines = Arrays.asList(oneLine.split("\n"));

        return new Document(path, lines);
    }

    public static Document newInstance(Path path) {
        return new Document(path);
    }

    public static Document newInstance(Path path, List<String> content) {
        return new Document(path, content);
    }

    public static void save(Document document) throws IOException {
        document.updateFilename();
        Files.write(document.getPath(), document.getContent(), Charset.forName(Document.charset));
    }
}
