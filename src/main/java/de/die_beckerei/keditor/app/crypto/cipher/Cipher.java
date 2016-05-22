package de.die_beckerei.keditor.app.crypto.cipher;

import de.die_beckerei.keditor.app.crypto.CipherSettings;
import de.die_beckerei.keditor.app.crypto.cipher.types.Algorithm;

/**
 * Created by Flo on 16.05.16.
 */
public abstract class Cipher {

    private Algorithm algorithm;

    private CipherSettings settings;

    public enum TYPE {
        DES,
        AES
    }

    Cipher(Algorithm algorithm, CipherSettings settings) throws Exception {
        this.algorithm = algorithm;

        this.settings = settings;

        if(!this.validateSettings())
            throw new Exception("Ciphersettings do not match or are not valid");
    }

    private boolean validateSettings() {
        //keep it simple, check only if settings are set
        return !(this.settings.getBlockmode() == null || this.settings.getPadding() == null || this.settings.getKey() == null || this.settings.getKey().isEmpty());
    }

    public byte[] encrypt(byte[] context) throws Exception {
        return algorithm.encrypt(context);
    }

    public byte[] decrypt(byte[] context) throws Exception {
        return algorithm.decrypt(context);
    }
}
