package com.blog.repository.impl;

import com.blog.model.Comment;
import com.blog.model.Tag;
import com.blog.repository.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class TagCrudRepositoryImpl implements CrudRepository<Tag, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public TagCrudRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> findAll() {
        return sessionFactory.getCurrentSession().createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Tag findById(Long id) {
        return sessionFactory.getCurrentSession().get(Tag.class, id);
    }

    @Override
    @Transactional
    public void save(Tag obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    @Transactional
    public void update(Long id, Tag obj) {
        Session session = sessionFactory.getCurrentSession();
        Comment comment = session.get(Comment.class, id);
        comment.setContent(obj.getName());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Comment.class, id));
    }
}