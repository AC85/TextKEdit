package alex.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import java.security.InvalidKeyException;

/**
 * Created by alexanderchristoph on 21.06.16.
 */
public interface Cipher {
    public byte[] encrypt(byte[] plaintext) throws InvalidKeyException, ShortBufferException, BadPaddingException, IllegalBlockSizeException;
    public byte[] decrypt(byte[] ciphertext) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException;
}
