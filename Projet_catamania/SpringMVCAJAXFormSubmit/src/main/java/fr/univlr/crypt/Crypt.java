package fr.univlr.crypt;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * Hashing implementation for passwords
 *
 * @author mbouchau
 */
public class Crypt {

    private static byte saltL = 4;

    /**
     * Returns a hashed and salted password
     *
     * @param password string to be hashed and salted
     * @return hashed and salted password
     */
    public byte[] createDBPassword(String password) {
        byte[] objRetour = null;
        try {
            byte[] unsaltedPassword = createPasswordHash(password);
            byte[] saltValue = new byte[saltL];
            SecureRandom rng = new SecureRandom();
            rng.nextBytes(saltValue);
            objRetour = createSaltedPassword(saltValue, unsaltedPassword);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objRetour;
    }

    /**
     * Returns a hashed password without prefixing
     *
     * @param password password to hash
     * @return hashed password
     */
    private byte[] createPasswordHash(String password) {
        byte[] objRetour = null;
        MessageDigest sha1 = null;
        try {
            sha1 = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sha1.reset();
        try {
            sha1.update(password.getBytes("UTF-8"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        objRetour = sha1.digest();
        return objRetour;
    }

    //Cette fonction prend en paramètre le mot de passe, et le préfixe, pour en donner le mot de passe final
    /**
     * Generates the salted password with the unsalted one
     *
     * @param saltValue salt value
     * @param unsaltedPassword password to salt
     * @return salted password
     */
    private byte[] createSaltedPassword(byte[] saltValue, byte[] unsaltedPassword) {
        byte[] objRetour = null;
        byte[] rawSalted = new byte[unsaltedPassword.length + saltValue.length];
        System.arraycopy(unsaltedPassword, 0, rawSalted, 0, unsaltedPassword.length);
        System.arraycopy(saltValue, 0, rawSalted, unsaltedPassword.length, saltValue.length);
        MessageDigest sha1 = null;
        try {
            sha1 = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sha1.reset();
        sha1.update(rawSalted);
        byte[] saltedPassword = sha1.digest();
        byte[] dbPassword = new byte[saltedPassword.length + saltValue.length];
        System.arraycopy(saltedPassword, 0, dbPassword, 0, saltedPassword.length);
        System.arraycopy(saltValue, 0, dbPassword, saltedPassword.length, saltValue.length);
        objRetour = dbPassword;
        return objRetour;
    }

    /**
     * Returns the comparison of the database password against the password to
     * test
     *
     * @param motPasseStocke database password
     * @param motPasseSoumis password to test
     * @return true if the passwords are the same, false in the other case
     */
    public boolean comparerMotPasse(byte[] motPasseStocke, String motPasseSoumis) {
        boolean boolRetour = false;
        byte[] saltValue = new byte[saltL];
        int saltOffset = motPasseStocke.length - saltL;
        for (int i = 0; i < saltL; i++) {
            saltValue[i] = motPasseStocke[saltOffset + i];
        }
        byte[] hashedPassword = createPasswordHash(motPasseSoumis);
        byte[] saltedPassword = createSaltedPassword(saltValue, hashedPassword);
        boolRetour = compareByteArray(motPasseStocke, saltedPassword);
        return boolRetour;
    }

    /**
     * Compares two byte arrays
     *
     * @param arrayA array to compare A
     * @param arrayB array to compare B
     * @return true if the byte arrays are the same
     */
    private boolean compareByteArray(byte[] arrayA, byte[] arrayB) {
        boolean boolRetour = false;
        try {
            if (arrayA.length != arrayB.length) {
                return false;
            }
            for (int i = 0; i < arrayA.length; i++) {
                if (arrayA[i] != arrayB[i]) {
                    return false;
                }
            }
            boolRetour = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return boolRetour;
    }

    /**
     * Generates a random password and returns it as a String
     *
     * @return random generated password
     */
    public static String generateRandomPassword() {
        SecureRandom rg = null;
        String randomValue = null;
        try {
            rg = new SecureRandom();
            byte[] rno = new byte[10];
            rg.nextBytes(rno);
            randomValue = Base64.getEncoder().encodeToString(rno);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return randomValue;
    }
}
