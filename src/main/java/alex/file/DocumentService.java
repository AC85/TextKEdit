package alex.file;

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
}
