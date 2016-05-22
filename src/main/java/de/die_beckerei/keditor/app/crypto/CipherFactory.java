package de.die_beckerei.keditor.app.crypto;

import de.die_beckerei.keditor.app.crypto.cipher.AES;
import de.die_beckerei.keditor.app.crypto.cipher.Cipher;
import de.die_beckerei.keditor.app.crypto.cipher.DES;
import de.die_beckerei.keditor.app.crypto.cipher.types.AES_NoPadding_ECB;
import de.die_beckerei.keditor.app.crypto.cipher.types.AES_PKCS7_ECB;
import de.die_beckerei.keditor.app.crypto.cipher.types.AlgoDES;
import de.die_beckerei.keditor.app.crypto.cipher.types.Algorithm;

/**
 *
 */
public class CipherFactory {

    public static Cipher getInstance(Cipher.TYPE ciphertype, CipherSettings settings) throws Exception {

        Algorithm usedAlgo = CipherFactory.determineAlgorithm(ciphertype, settings);

        switch (ciphertype) {
            case AES:
                return new AES(usedAlgo, settings);
            case DES:
                return new DES(new AlgoDES());
            default:
                return null;
        }
    }

    private static Algorithm determineAlgorithm(Cipher.TYPE ciphertype, CipherSettings settings) throws Exception {


        switch (settings.getPadding()) {
            case NoPadding:
                return CipherFactory.Algorithm_NoPadding(settings);
            case PKCS5:
            case PKCS7:
                return  CipherFactory.Algorithm_PKCS7(settings);
            case ZeroByte:
            default:
                throw new Exception("No padding mechanism set.");
        }

    }

    private static Algorithm Algorithm_NoPadding(CipherSettings settings) throws Exception {
        switch (settings.getBlockmode()) {
            case ECB:
                return new AES_NoPadding_ECB(settings);
            case CBC:
            case CTS:
            default:
                throw new Exception("No block cipher mode set.");
        }
    }

    private static Algorithm Algorithm_PKCS7(CipherSettings settings) throws Exception {
        switch (settings.getBlockmode()) {
            case ECB:
                return  new AES_PKCS7_ECB(settings);
            case CBC:
            case CTS:
            default:
                throw new Exception("No block cipher mode set");
        }
    }
}