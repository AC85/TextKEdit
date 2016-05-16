package de.die_beckerei.keditor.app.crypto.cipher.types;

/**
 * Created by Flo on 16.05.16.
 */
public interface Algorithm {
    byte[] encrypt(byte[] contex);
    byte[] decrypt(byte[] contex);
}
