package alex.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;

/**
 * Created by alexanderchristoph on 08/07/16.
 */
public class ARC4 implements Cipher {

    private CipherSettings settings;
    private SecretKeySpec key;
    private javax.crypto.Cipher cipher;

    public ARC4(CipherSettings settings) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        this.settings = settings;

        this.cipher = javax.crypto.Cipher.getInstance("ARC4", settings.getProvider());

        byte[] keyBytes = new byte[]{
                0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef};

        this.key = new SecretKeySpec(keyBytes, "ARC4");
    }

    @Override
    public byte[] encrypt(byte[] plaintext) throws InvalidKeyException, ShortBufferException, BadPaddingException, IllegalBlockSizeException {
        byte[] ciphertext =  new byte[plaintext.length];
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, this.key);
        int ctLength = cipher.update(plaintext, 0, plaintext.length, ciphertext, 0);
        ctLength += cipher.doFinal(ciphertext, ctLength);

        return ciphertext;
    }

    @Override
    public byte[] decrypt(byte[] ciphertext) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        byte[] plaintext = new byte[ciphertext.length];
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, this.key);
        int ptLength = cipher.update(ciphertext, 0, ciphertext.length, plaintext, 0);
        ptLength += cipher.doFinal(plaintext, ptLength);

        return Arrays.copyOf(plaintext, ptLength);
    }
}
