package alex.file;

import alex.cipher.Cipher;
import alex.cipher.CipherFactory;

import javax.xml.bind.JAXB;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by alexanderchristoph on 14.06.16.
 */
public class DocumentService {

    public static Document getEmpty() {
        return new Document();
    }

    /**
     * löd ein Document unter dem angegeben Pfad
     * @param file
     * @return
     * @throws IOException
     */
    public static Document load(File file) throws IOException {
        Document doc = DocumentService.getEmpty();
        doc.setFile(file);

        Path path = file.toPath();
        byte[] payload = Files.readAllBytes(path);
        doc.setPayload(payload);

        return doc;
    }

    public static Document loadEncrypted(File file) {
        return JAXB.unmarshal(file, Document.class);
    }

    /**
     * Speichert das übergebene Document ab
     * Der Pfad findet sich im Document selbst (file)
     * @param document
     * @throws IOException
     */
    public static void save(Document document) throws IOException {
        Files.write(document.getFile().toPath(), document.getPayload());
    }

    public static void saveAsXml(Document document) {
        JAXB.marshal(document, document.getFile());
    }

    public static Document encrypt(Document document) throws Exception {

        Cipher cipher = CipherFactory.getInstance(document.getCipherSettings());

        byte[] plaintext = document.getPayload();
        byte[] ciphertext = cipher.encrypt(plaintext);

        Document encryptedDoc = new Document();
        encryptedDoc.setPayload(ciphertext);
        encryptedDoc.setEncrypted(true);
        encryptedDoc.setCipherSettings(document.getCipherSettings());

        return encryptedDoc;
    }

    public static Document decrypt(Document document) throws Exception {
        Cipher cipher = CipherFactory.getInstance(document.getCipherSettings());

        byte[] ciphertext = document.getPayload();
        byte[] plaintext = cipher.decrypt(ciphertext);

        Document decryptedDoc = new Document();
        decryptedDoc.setPayload(plaintext);
        return decryptedDoc;
    }
}
