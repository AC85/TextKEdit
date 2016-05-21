package de.die_beckerei.keditor.app.crypto;

/**
 * Created by Flo on 21.05.16.
 */
public class CipherSettings {

    public enum PADDING {
        NoPadding,
        PKCS5,
        PKCS7,
        ZeroByte,
    }

    public enum BlockModus {
        ECB,
        CBC,
        CTS
    }
}
