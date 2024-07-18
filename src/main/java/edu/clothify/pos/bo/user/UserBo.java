package edu.clothify.pos.bo.user;
import edu.clothify.pos.bo.SuperBo;
import edu.clothify.pos.dto.User;

import java.util.List;

public interface UserBo extends SuperBo {
    boolean save(User user);
    List<User> getAllUserByIsActiveTrue();
    User getUserById(String userId);
    boolean updateUser(String UserId ,User user);
    boolean deleteUser(String userId);
    User getUserByEmail(String userEmail);
    String generateUserId();
    Long getAllEmployeeCount();
}
