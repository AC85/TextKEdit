package de.die_beckerei.keditor.app.crypto.cipher.types;

/**
 * Alexander Christoph
 * 634389
 * BMI
 */


public interface Algorithm {
    byte[] encrypt(byte[] context) throws Exception;
    byte[] decrypt(byte[] context) throws Exception;
}
