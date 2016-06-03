package alex.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by alexanderchristoph on 03.06.16.
 */
public class Document {

    private byte[] payload;

    private Path path;

    public Document(Path path) throws IOException {
        this.path = path;
        if(path != null) {
            this.payload = Files.readAllBytes(path);
        }
    }

    public Document(Path path, String payload) {
        this.path = path;
        this.setPayload(payload);
    }

    public void setPayload(String payload) {
        this.payload = payload.getBytes();
    }

    public String getPayloadAsString() {
        return new String(this.payload);
    }

    public byte[] getPayload() {
        return this.payload;
    }

    public boolean isTransient() {
        return (this.path == null);
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public void save() throws IOException {
        Files.write(this.path, this.getPayload());
    }
}
