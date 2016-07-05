package alex.cipher;


/**
 * Created by alexanderchristoph on 21.06.16.
 */
public class CipherFactory {

    public static Cipher getInstance(CipherSettings settings) throws Exception {

        switch (settings.getCipher()) {
            case DES:
                return new DES(settings);
            default:
                throw new Exception("No suitable Cipher found");
        }
    }
}
