package repository;

import domain.Entity;

import java.util.Collection;

public interface IRepository<T extends Entity> extends Iterable<T>{
    void add(T item) throws RepositoryException;

    void remove(int id) throws RepositoryException;

    Collection<T> getAll();

    void update(int id, T item) throws RepositoryException;

    T findById(int id) throws RepositoryException;
}

