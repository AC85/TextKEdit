package de.die_beckerei.keditor.app.crypto.cipher;

import de.die_beckerei.keditor.app.crypto.cipher.types.Algorithm;

/**
 * Created by Flo on 16.05.16.
 */
public abstract class Cipher {

    private Algorithm algorithm;

    public enum TYPE {
        DES,
        AES
    }

    Cipher(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public byte[] encrypt(byte[] context) throws Exception {
        return algorithm.encrypt(context);
    }

    public byte[] decrypt(byte[] context) throws Exception {
        return algorithm.decrypt(context);
    }
}
