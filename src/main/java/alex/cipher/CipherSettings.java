package alex.cipher;

/**
 * Created by alexanderchristoph on 03.06.16.
 */
public class CipherSettings {

    public enum CIPHER {
        DES,
        AES,
        ARC4
    }

    public enum MODE {
        ECB,
        CBC,
        CTS
    }

    public enum PADDING {
        NoPadding,
        PKCS5,
        PKCS7,
        ZeroBytePadding
    }

    private CIPHER cipher;

    public CIPHER getCipher() {
        return cipher;
    }

    public void setCipher(CIPHER cipher) {
        this.cipher = cipher;
    }

    public MODE getMode() {
        return mode;
    }

    public void setMode(MODE mode) {
        this.mode = mode;
    }

    public PADDING getPadding() {
        return padding;
    }

    public void setPadding(PADDING padding) {
        this.padding = padding;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    private MODE mode;
    private PADDING padding;
    private String key;
    private String provider = "BC";
}
