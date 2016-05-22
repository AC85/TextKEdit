package de.die_beckerei.keditor.app.crypto;

import de.die_beckerei.keditor.app.crypto.cipher.AES;
import de.die_beckerei.keditor.app.crypto.cipher.Cipher;
import de.die_beckerei.keditor.app.crypto.cipher.DES;
import de.die_beckerei.keditor.app.crypto.cipher.types.AlgoAES;
import de.die_beckerei.keditor.app.crypto.cipher.types.AlgoDES;

/**
 *
 */
public class CipherFactory {

    public static Cipher getInstance(Cipher.TYPE ciphertype, CipherSettings settings) throws Exception {

        switch (ciphertype) {
            case AES:
                return new AES(new AlgoAES(), settings);
            case DES:
                return new DES(new AlgoDES());
            default:
                return null;
        }
    }
}