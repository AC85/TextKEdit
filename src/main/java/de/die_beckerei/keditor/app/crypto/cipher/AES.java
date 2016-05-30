package de.die_beckerei.keditor.app.crypto.cipher;

import de.die_beckerei.keditor.app.crypto.CipherSettings;
import de.die_beckerei.keditor.app.crypto.cipher.types.Algorithm;
/**
 * Alexander Christoph
 * 634389
 * BMI
 */
public class AES extends Cipher {

    public AES(Algorithm Algorithm, CipherSettings settings) throws Exception {
        super(Algorithm, settings);
    }
}
