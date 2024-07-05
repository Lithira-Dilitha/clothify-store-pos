package edu.clothify.pos.bo.encryption;

import edu.clothify.pos.bo.SuperBo;

public interface PasswordEncryption extends SuperBo {
    String hashPassword(String password);
    boolean checkPassword(String plaintext,String hashed);
}
