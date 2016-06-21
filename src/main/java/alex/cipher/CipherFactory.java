package alex.cipher;


/**
 * Created by alexanderchristoph on 21.06.16.
 */
public class CipherFactory {

    public static Cipher getInstance(CipherSettings settings) {
        //settings nochmal checken, default nehmen
        return new DES_PKCS5_CBC(settings);
    }
}
