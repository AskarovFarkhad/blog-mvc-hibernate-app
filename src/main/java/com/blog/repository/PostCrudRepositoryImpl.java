package com.blog.repository;

import com.blog.model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PostCrudRepositoryImpl implements CrudRepository<Post, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public PostCrudRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return sessionFactory.getCurrentSession().createQuery("SELECT p FROM Post p", Post.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Post findById(Long id) {
        return sessionFactory.getCurrentSession().get(Post.class, id);
    }

    @Override
    @Transactional
    public void save(Post obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    @Transactional
    public void update(Long id, Post obj) {
        Session session = sessionFactory.getCurrentSession();
        Post post = session.get(Post.class, id);
        post.setTitle(obj.getTitle());
        post.setContent(obj.getContent());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Post.class, id));
    }
}