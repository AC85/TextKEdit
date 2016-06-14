package alex.file;

import de.die_beckerei.keditor.app.crypto.CipherSettings;

import javax.xml.bind.annotation.XmlTransient;
import java.io.File;

/**
 * Created by alexanderchristoph on 03.06.16.
 */
public class Document {

    private File file;

    private byte[] payload;

    private CipherSettings cipherSettings;

    protected Document() {
        this.payload = new byte[0];
        this.file = null;
        this.cipherSettings = null;
    }

    /**
     * nimmt das payload 1:1 als payload (byte zu byte array)
     * @param payload
     */
    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    /**
     * wandelt den String in ein byte Array (intern) um
     * @param payload
     */
    public void setPayload(String payload) {
        //TODO: richtig machen
        this.payload = payload.getBytes();
    }

    @XmlTransient
    public String getPayloadAsString() {
        return new String(this.payload);
    }

    public byte[] getPayload() {
        return this.payload;
    }

    public boolean isTransient() {
        return (this.file == null);
    }

    @XmlTransient
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
