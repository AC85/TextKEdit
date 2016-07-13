package alex.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Arrays;

/**
 * Created by alexanderchristoph on 21.06.16.
 */
public class DES implements Cipher {

    private CipherSettings settings;
    private SecretKeySpec key;
    private javax.crypto.Cipher cipher;


    public DES(CipherSettings settings) throws Exception {
        this.settings = settings;

        //cipher anhand der gewählten Parameter setzen
        if (settings.getPadding() == CipherSettings.PADDING.PKCS5) {
            if (settings.getMode() == CipherSettings.MODE.CBC) {
                //zufälliger Initialisierungs Vektor wird generiert, sofern man keinen angibt
                this.cipher = javax.crypto.Cipher.getInstance("DES/CBC/PKCS5Padding", "BC");
            } else if (settings.getMode() == CipherSettings.MODE.ECB) {
                this.cipher = javax.crypto.Cipher.getInstance("DES/ECB/PKCS5Padding", "BC");
            }
        }
        if (this.cipher == null) throw new Exception("No suitable Settings for DES");


        //fester schlüssel zum Speichern
        byte[] keyBytes = new byte[]{
                0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef};

        this.key = new SecretKeySpec(keyBytes, "DES");
    }

    @Override
    public byte[] encrypt(byte[] plaintext) throws InvalidKeyException, ShortBufferException, BadPaddingException, IllegalBlockSizeException {

        //pseudo zufälligen IV auslesen und in settings abspeichern
        this.settings.setIv(cipher.getIV());

        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);

        // größe des Outputs Buffers festlegen
        byte[] cipherText = new byte[cipher.getOutputSize(plaintext.length)];

        // Verschlüsselung durch cipher.update
        int ctLength = cipher.update(plaintext, 0, plaintext.length, cipherText, 0);

        // Verschlüsselung des letzten Blocks
        ctLength += cipher.doFinal(cipherText, ctLength);

        return cipherText;
    }

    @Override
    public byte[] decrypt(byte[] ciphertext) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        //initialisierungsvektor auslesen
        byte[] iv = this.settings.getIv();

        //iv für decryption setzen
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

        // größe des Output Buffers festlegen
        byte[] plainText = new byte[cipher.getOutputSize(ciphertext.length)];

        // Verschlüsselung durch cipher.update
        int ptLength = cipher.update(ciphertext, 0, ciphertext.length, plainText, 0);

        // Verschlüssung des letzten Blocks
        ptLength += cipher.doFinal(plainText, ptLength);

        //padding entfernen
        return Arrays.copyOf(plainText, ptLength);
    }
}
