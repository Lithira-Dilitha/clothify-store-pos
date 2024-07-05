package edu.clothify.pos.bo.encryption.impl;

import edu.clothify.pos.bo.encryption.PasswordEncryption;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptionImpl implements PasswordEncryption {

    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    @Override
    public boolean checkPassword(String plaintext, String hashed) {
        return BCrypt.checkpw(plaintext,hashed);
    }
}
