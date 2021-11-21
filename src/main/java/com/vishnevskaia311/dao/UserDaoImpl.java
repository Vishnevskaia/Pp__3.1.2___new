package com.vishnevskaia311.dao;

import com.vishnevskaia311.model.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> index() {  //addAllUsers

        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User getUserByName(String name) {
        //String query = "FROM users WHERE name=:name";
        try {
            return (User) entityManager
                    .createQuery("from User u inner JOIN FETCH u.roles as roles WHERE u.name = :name", User.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Пользователь не найден в базе");
            return null;
        }
    }

    @Override
    public User show(Long id) {

        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User updatedUser, Long id) {
        entityManager.merge(updatedUser);
    }



    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}

