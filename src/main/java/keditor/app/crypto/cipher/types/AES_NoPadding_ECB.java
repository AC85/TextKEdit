package keditor.app.crypto.cipher.types;

import keditor.app.crypto.CipherSettings;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Alexander Christoph
 * BMI
 */

/**
 * uses AES with NoPadding and ECB
 */
public class AES_NoPadding_ECB implements Algorithm {

    Cipher cipher;
    SecretKeySpec key;

    public AES_NoPadding_ECB(CipherSettings settings) throws Exception {

        //settings.getKey().getBytes(Document.charset)
        //TODO generate appropriate key
        byte[] keyBytes = new byte[] {
            0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
            0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f,
            0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17 };

        this.cipher = Cipher.getInstance("AES/ECB/NoPadding", settings.getProvider());
        this.key = new SecretKeySpec(keyBytes, "AES");

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