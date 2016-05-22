package de.die_beckerei.keditor.app.crypto.cipher.types;

import de.die_beckerei.keditor.app.crypto.CipherSettings;
import de.die_beckerei.keditor.app.file.Document;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * uses AES with NoPadding and ECB
 */
public class AES_NoPadding_ECB implements Algorithm {

    Cipher cipher;
    SecretKeySpec key;

    public AES_NoPadding_ECB(CipherSettings settings) throws Exception {

        this.cipher = Cipher.getInstance("AES/ECB/NoPadding", settings.getProvider());
        this.key = new SecretKeySpec(settings.getKey().getBytes(Document.charset), "AES");

    }

    @Override
    public byte[] encrypt(byte[] context) throws Exception {

        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] cipherText = new byte[context.length];

        int ctLength = cipher.update(context, 0, context.length, cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);

        return cipherText;

    }

    @Override
    public byte[] decrypt(byte[] context) throws Exception {

        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] plainText = new byte[cipher.getOutputSize(context.length)];

        int ptLength = cipher.update(context, 0, context.length, plainText, 0);
        ptLength += cipher.doFinal(plainText, ptLength);

        return plainText;
    }
}