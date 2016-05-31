package keditor.app.crypto.cipher.types;

/**
 * Alexander Christoph
 * BMI
 */


public interface Algorithm {
    byte[] encrypt(byte[] context) throws Exception;
    byte[] decrypt(byte[] context) throws Exception;
}
