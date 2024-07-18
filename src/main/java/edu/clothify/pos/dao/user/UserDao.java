package edu.clothify.pos.dao.user;

import edu.clothify.pos.dao.CrudDao;
import edu.clothify.pos.dto.User;
import edu.clothify.pos.entity.UserEntity;

import java.util.List;

public interface UserDao extends CrudDao<UserEntity> {
    List<User> getAllUserByIsActiveTrue();
    User getUserById(String employeeId);
    User getUserByEmail(String userEmail);
    String generateUserId();
    Long getAllEmployeeCount();
}
