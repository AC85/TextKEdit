package de.die_beckerei.keditor.app.crypto;

/**
 * Created by Flo on 21.05.16.
 */
public class CipherSettings {

    public enum PADDING {
        NoPadding ("No Padding"),
        PKCS5 ("PKCS5"),
        PKCS7 ("PKCS7"),
        ZeroByte ("Zero Byte Padding");

        private final String name;

        PADDING(String s) {
            name = s;
        }
    }

    public enum BLOCK {
        ECB ("ECB (Electronic Codebook)"),
        CBC ("CBC (Cipher Block Chaining)"),
        CTS ("CTS (Ciphertext Stealing)");

        private final String name;

        BLOCK(String s) {
            name = s;
        }
    }
}
