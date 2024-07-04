package edu.clothify.pos.dao.user.impl;

import edu.clothify.pos.dao.user.UserDao;
import edu.clothify.pos.dto.Employee;
import edu.clothify.pos.dto.User;
import edu.clothify.pos.entity.EmployeeEntity;
import edu.clothify.pos.entity.UserEntity;
import edu.clothify.pos.utill.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> getAllUserByIsActiveTrue() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        List<UserEntity> allUsers = session.createQuery("from UserEntity where isActive =true",
                        UserEntity.class)
                .getResultList();
        List<User> userList = new ArrayList<>();
        allUsers.forEach(userEntity ->{
            userList.add(new ModelMapper().map(userEntity,User.class));
        });
        session.getTransaction().commit();
        session.close();
        return userList;
    }

    @Override
    public User getUserById(String userId) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        UserEntity user = session.createQuery("from UserEntity where id = :userId"
                        , UserEntity.class)
                .setParameter("userId", userId)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return new ModelMapper().map(user, User.class);
    }

    @Override
    public User getUserByEmail(String userEmail) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        UserEntity user = session.createQuery("from UserEntity where email = :email"
                        , UserEntity.class)
                .setParameter("email", userEmail)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return new ModelMapper().map(user, User.class);
    }

    @Override
    public boolean save(UserEntity dto) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(dto);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(String id, UserEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("update UserEntity set name = :name" +
                ",role = :role" +
                ",email = :email,password = :password,isActive = :isActive where userId =:userId");
//        query.setParameter("customerId",customer.getCustomerId());
        query.setParameter("name",entity.getName());
        query.setParameter("role",entity.getRole());
        query.setParameter("email",entity.getEmail());
        query.setParameter("password",entity.getPassword());
        query.setParameter("isActive",entity.getIsActive());
        query.setParameter("userId",id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("update UserEntity set isActive = :isActive " +
                "where userId = :userId");
        query.setParameter("isActive",false);
        query.setParameter("userId",id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
