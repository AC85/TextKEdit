package de.die_beckerei.keditor.app.editor.dialog.crypto;

/**
 * Alexander Christoph
 * BMI
 */
public class CipherSettings {

    private PADDING padding;
    private BLOCK blockmode;
    private String key;
    private String provider = "BC";

    public enum PADDING {
        NoPadding ("No Padding"),
        PKCS5 ("PKCS5"),
        PKCS7 ("PKCS7"),
        ZeroByte ("Zero Byte Padding");

        private final String name;

        PADDING(String s) {
            name = s;
        }

        @Override
        public String toString() {
            return this.name;
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

        @Override
        public String toString() {
            return this.name;
        }
    }

    public void setPadding(PADDING padding) {
        this.padding = padding;
    }

    public void setBlockmode(BLOCK blockmode) {
        this.blockmode = blockmode;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public PADDING getPadding() {
        return padding;
    }

    public BLOCK getBlockmode() {
        return blockmode;
    }

    public String getKey() {
        return key;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
