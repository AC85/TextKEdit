package de.die_beckerei.keditor.app.crypto.cipher.types;

import de.die_beckerei.keditor.app.crypto.CipherFactory;
import de.die_beckerei.keditor.app.crypto.CipherSettings;
import de.die_beckerei.keditor.app.crypto.cipher.Cipher;
import de.die_beckerei.keditor.app.file.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AES_PKCS7_ECBTest {

    private static  byte[] MSG = new byte[] {
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
            0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
            0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17
    };

    CipherSettings settings = new CipherSettings();
    Cipher cipher;


    @Before
    public void setUp() throws Exception {
        settings.setPadding(CipherSettings.PADDING.PKCS7);
        settings.setBlockmode(CipherSettings.BLOCK.ECB);
        settings.setKey("wasd");

        cipher = CipherFactory.getInstance(Cipher.TYPE.AES, settings);
    }

    @Test
    public void encrypt() throws Exception {
        assertNotEquals(MSG, cipher.encrypt(MSG));
    }

    @Test
    public void decrypt() throws Exception {

        byte[] encrypted = cipher.encrypt(MSG);
        byte[] decrypted = cipher.decrypt(encrypted);
        assertArrayEquals(MSG, decrypted);
    }

}