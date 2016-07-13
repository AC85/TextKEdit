package alex.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * Created by alexanderchristoph on 08/07/16.
 *
 * Stream Cipher
 * Kein Blockmod und kein Padding notwendig
 */
public class ARC4 implements Cipher {

    private CipherSettings settings;
    private SecretKeySpec key;
    private javax.crypto.Cipher cipher;

    public ARC4(CipherSettings settings) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        this.settings = settings;

        // cipher mit ARC4 initalisieren
        this.cipher = javax.crypto.Cipher.getInstance("ARC4", settings.getProvider());

        //fester key
        byte[] keyBytes = new byte[]{
                0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef};

        //key für ARC4 generieren
        this.key = new SecretKeySpec(keyBytes, "ARC4");
    }

    @Override
    public byte[] encrypt(byte[] plaintext) throws InvalidKeyException, ShortBufferException, BadPaddingException, IllegalBlockSizeException {
        //länge des ciphertextes ist identisch mit der plaintext länge
        byte[] ciphertext =  new byte[plaintext.length];

        // Cipher für die Verschlüsselung vorbereiten
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, this.key);

        // Verschlüsselung durch Cipher update
        int ctLength = cipher.update(plaintext, 0, plaintext.length, ciphertext, 0);

        // Verschlüsselung des letzten Blocks
        ctLength += cipher.doFinal(ciphertext, ctLength);

        return ciphertext;
    }

    @Override
    public byte[] decrypt(byte[] ciphertext) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // festlegung der Größe des Buffers
        byte[] plaintext = new byte[ciphertext.length];

        // Cipher für die Entschlüsselung vorbereiten
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, this.key);

        // Entschlüsselung durch cipher.update
        int ptLength = cipher.update(ciphertext, 0, ciphertext.length, plaintext, 0);

        // Verschlüsselung des letzten Blocks
        ptLength += cipher.doFinal(plaintext, ptLength);

        //plaintext gleiche länge wie ciphertext
        return plaintext;
    }
}
