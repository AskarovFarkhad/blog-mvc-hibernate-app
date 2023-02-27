package com.blog.repository.impl;

import com.blog.model.User;
import com.blog.repository.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserCrudRepositoryImpl implements CrudRepository<User, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserCrudRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    @Transactional
    public void save(User obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    @Transactional
    public void update(Long id, User obj) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        user.setUserName(obj.getUserName());
        user.setEmail(obj.getEmail());
        user.setPassword(obj.getPassword());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().remove(session.get(User.class, id));
    }
}