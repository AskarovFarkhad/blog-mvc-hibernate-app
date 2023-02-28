package com.blog.repository.impl;

import com.blog.model.Comment;
import com.blog.repository.CrudRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CommentCrudRepositoryImpl implements CrudRepository<Comment, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public CommentCrudRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return sessionFactory.getCurrentSession().createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findById(Long id) {
        return sessionFactory.getCurrentSession().get(Comment.class, id);
    }

    @Override
    @Transactional
    public void save(Comment obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    @Transactional
    public void update(Long id, Comment obj) {
        Session session = sessionFactory.getCurrentSession();
        Comment comment = session.get(Comment.class, id);
        comment.setContent(obj.getContent());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Comment comment = session.get(Comment.class, id);
        if (comment != null) {
            session.remove(comment);
        }
    }
}