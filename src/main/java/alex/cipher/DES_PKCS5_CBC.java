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
 * Created by alexanderchristoph on 21.06.16.
 */
public class DES_PKCS5_CBC implements Cipher {

    private CipherSettings settings;
    private SecretKeySpec key;
    private javax.crypto.Cipher cipher;

    public DES_PKCS5_CBC(CipherSettings settings) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        this.settings = settings;

        byte[] keyBytes = new byte[] {
                0x01, 0x23, 0x45, 0x67, (byte)0x89, (byte)0xab, (byte)0xcd, (byte)0xef };

        this.key = new SecretKeySpec(keyBytes, "DES");
        this.cipher = javax.crypto.Cipher.getInstance("DES/ECB/PKCS7Padding", "BC");
    }

    @Override
    public byte[] encrypt(byte[] plaintext) throws InvalidKeyException, ShortBufferException, BadPaddingException, IllegalBlockSizeException {

        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);

        byte[] cipherText = new byte[cipher.getOutputSize(plaintext.length)];

        int ctLength = cipher.update(plaintext, 0, plaintext.length, cipherText, 0);

        ctLength += cipher.doFinal(cipherText, ctLength);

        return cipherText;
    }

    @Override
    public byte[] decrypt(byte[] ciphertext) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {

        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);

        byte[] plainText = new byte[cipher.getOutputSize(ciphertext.length)];

        int ptLength = cipher.update(ciphertext, 0, ciphertext.length, plainText, 0);

        ptLength += cipher.doFinal(plainText, ptLength);

        return Arrays.copyOf(plainText, ptLength);
    }
}
