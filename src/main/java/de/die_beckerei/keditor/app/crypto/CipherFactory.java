package de.die_beckerei.keditor.app.crypto;

import de.die_beckerei.keditor.app.crypto.cipher.AES;
import de.die_beckerei.keditor.app.crypto.cipher.Cipher;
import de.die_beckerei.keditor.app.crypto.cipher.DES;
import de.die_beckerei.keditor.app.crypto.cipher.types.AlgoAES;
import de.die_beckerei.keditor.app.crypto.cipher.types.AlgoDES;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 *
 */
public class CipherFactory {

    public static Cipher getInstance(Cipher.TYPE ciphertype) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {

        switch (ciphertype) {
            case AES:
                return new AES(new AlgoAES());
            case DES:
                return new DES(new AlgoDES());
            default:
                return null;
        }
    }
}