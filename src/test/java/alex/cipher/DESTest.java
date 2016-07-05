package alex.cipher;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by alexanderchristoph on 05/07/16.
 */
public class DESTest {

    private static  byte[] MSG = new byte[] {
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
            0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
            0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17
    };

    CipherSettings settings = new CipherSettings();
    Cipher cipher;

    @Before
    public void setUp() throws Exception {
        settings.setCipher(CipherSettings.CIPHER.DES);
        settings.setPadding(CipherSettings.PADDING.PKCS5);
        settings.setMode(CipherSettings.MODE.ECB);

        cipher = CipherFactory.getInstance(settings);
    }

    @Test
    public void testEncrypt() throws Exception {
        assertNotEquals(MSG, cipher.encrypt(MSG));
    }

    @Test
    public void testDecrypt() throws Exception {
        byte[] encrypted = cipher.encrypt(MSG);
        byte[] decrypted = cipher.decrypt(encrypted);
        assertNotEquals(MSG, decrypted);
    }
}