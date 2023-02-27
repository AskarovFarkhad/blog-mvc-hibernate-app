package com.blog.repository;

import java.util.List;

public interface CrudRepository<T, E> {

    List<T> findAll();

    T findById(E id);

    void save(T obj);

    void update(E id, T obj);

    void delete(E id);
}