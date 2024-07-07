package edu.clothify.pos.bo.user.impl;

import edu.clothify.pos.bo.user.UserBo;
import edu.clothify.pos.dao.DaoFactory;
import edu.clothify.pos.dao.user.UserDao;
import edu.clothify.pos.dto.User;
import edu.clothify.pos.entity.UserEntity;
import edu.clothify.pos.utill.DaoType;
import org.modelmapper.ModelMapper;

import java.util.List;

public class UserBoImpl implements UserBo {
    UserDao userDao = DaoFactory.getInstance().getDao(DaoType.USER);
    ModelMapper mapper =new ModelMapper();
    @Override
    public boolean save(User user) {
        return userDao.save(mapper.map(user, UserEntity.class));
    }

    @Override
    public List<User> getAllUserByIsActiveTrue() {
        return userDao.getAllUserByIsActiveTrue();
    }

    @Override
    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public boolean updateUser(String UserId, User user) {
        return userDao.update(UserId,mapper.map(user, UserEntity.class));
    }

    @Override
    public boolean deleteUser(String userId) {
        return userDao.delete(userId);
    }

    @Override
    public User getUserByEmail(String userEmail) {
        return userDao.getUserByEmail(userEmail);
    }

    @Override
    public String generateUserId() {
        return userDao.generateUserId();
    }
}
