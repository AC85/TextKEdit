package de.die_beckerei.keditor.app.crypto.cipher;

import de.die_beckerei.keditor.app.crypto.CipherSettings;
import de.die_beckerei.keditor.app.crypto.cipher.types.Algorithm;
/**
 * Created by Flo on 16.05.16.
 */
public class AES extends Cipher {

    public AES(Algorithm Algorithm, CipherSettings settings) throws Exception {
        super(Algorithm, settings);
    }
}
