package de.die_beckerei.keditor.app.crypto.cipher.types;


import de.die_beckerei.keditor.app.crypto.CipherSettings;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

/**
 *  uses AES with PKCS7 and ECB
 */
public class AES_PKCS7_ECB implements Algorithm {

    Cipher cipher;
    SecretKeySpec key;

    public AES_PKCS7_ECB(CipherSettings settings) throws Exception {

        //settings.getKey().getBytes(Document.charset)
        //TODO generate appropriate key
        byte[] keyBytes = new byte[] {
                0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
                0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 };

        this.cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", settings.getProvider());
        this.key = new SecretKeySpec(keyBytes, "AES");
    }

    @Override
    public byte[] encrypt(byte[] context) throws Exception {

        this.cipher.init(Cipher.ENCRYPT_MODE, this.key);

        byte[] cipherText = new byte[this.cipher.getOutputSize(context.length)];
        int ctLength = this.cipher.update(context, 0, context.length, cipherText, 0);
        ctLength += this.cipher.doFinal(cipherText, ctLength);

        return cipherText;
    }

    @Override
    public byte[] decrypt(byte[] context) throws Exception {

        this.cipher.init(Cipher.DECRYPT_MODE, this.key);

        byte[] plainText = new byte[this.cipher.getOutputSize(context.length)];
        int ptLength = cipher.update(context, 0, context.length, plainText, 0);
        ptLength += cipher.doFinal(plainText, ptLength);

        //trim padding
        return Arrays.copyOf(plainText, ptLength);
    }
}
