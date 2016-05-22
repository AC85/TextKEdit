package de.die_beckerei.keditor.app.crypto.cipher;

import de.die_beckerei.keditor.app.crypto.CipherSettings;
import de.die_beckerei.keditor.app.crypto.cipher.types.Algorithm;
/**
 * Created by Flo on 16.05.16.
 */
public class AES extends Cipher {

    CipherSettings settings;

    public AES(Algorithm Algorithm, CipherSettings settings) throws Exception {
        super(Algorithm);

        this.settings = settings;

        if(!this.validateSettings())
            throw new Exception("Ciphersettings do not match or are not valid");

    }

    private boolean validateSettings() {
        //keep it simple, check only if settings are set
        return !(this.settings.getBlockmode() == null || this.settings.getPadding() == null || this.settings.getKey() == null);
    }
}
