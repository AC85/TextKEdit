package alex.cipher;

/**
 * Created by alexanderchristoph on 21.06.16.
 */
public class DES_PKCS5_CBC implements Cipher {

    private CipherSettings settings;

    public DES_PKCS5_CBC(CipherSettings settings) {
        this.settings = settings;
    }

    @Override
    public byte[] encrypt(byte[] plaintext) {
        return plaintext;
    }

    @Override
    public byte[] decrypt(byte[] ciphertext) {
        return ciphertext;
    }
}
