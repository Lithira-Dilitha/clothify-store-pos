package edu.clothify.pos.bo.email;

import edu.clothify.pos.bo.SuperBo;

public interface EmailSending extends SuperBo {
    void sendMail(String to,String subject,String content);
    String generateOtp();
}
