package keditor.app.crypto.cipher;

import keditor.app.crypto.CipherSettings;
import keditor.app.crypto.cipher.types.Algorithm;

/**
 * Alexander Christoph
 * BMI
 */
public class DES extends Cipher {
    public DES(Algorithm Algorithm, CipherSettings settings) throws Exception {
        super(Algorithm, settings);
    }
}
