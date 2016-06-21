package alex.cipher;

/**
 * Created by alexanderchristoph on 21.06.16.
 */
public interface Cipher {
    public byte[] encrypt(byte[] plaintext);
    public byte[] decrypt(byte[] ciphertext);
}
