package repository;

import domain.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Repository<T extends Entity> implements IRepository<T> {
    List<T> entities = new ArrayList<>();

    @Override
    public void add(T item) throws RepositoryException {
        for(T object : entities)
            if(object.getId() == item.getId())
                throw new RepositoryException("Duplicate id!");
        entities.add(item);

    }

    @Override
    public void remove(int id) throws RepositoryException {
        boolean removed = entities.removeIf(entity -> entity.getId() == id);
        if (!removed) {
            throw new RepositoryException("Id not found!");
        }



    }

    @Override
    public Collection<T> getAll() {
        return entities;
    }

    @Override
    public void update(int id, T item) throws RepositoryException {
        int ok = 1;
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId() == id) {
                entities.set(i, item);
                ok = 0;
            }
        }
        if (ok == 1) {
            throw new RepositoryException("Id not found!");
        }
    }

    @Override
    public T findById(int id) throws RepositoryException {
        for (T entity : entities) {
            if (entity.getId() == id) {
                return entity;
            }
        }
        throw new RepositoryException("Id not found!");
    }



    @Override
    public Iterator<T> iterator() {
        return entities.iterator();
    }

}
