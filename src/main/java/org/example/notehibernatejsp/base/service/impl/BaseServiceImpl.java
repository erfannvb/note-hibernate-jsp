package org.example.notehibernatejsp.base.service.impl;

import lombok.AllArgsConstructor;
import org.example.notehibernatejsp.base.entity.BaseEntity;
import org.example.notehibernatejsp.base.repository.BaseRepository;
import org.example.notehibernatejsp.base.service.BaseService;
import org.hibernate.Session;
import org.hibernate.TransactionException;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
public class BaseServiceImpl<ID extends Serializable, Entity extends BaseEntity<ID>,
        Repository extends BaseRepository<ID, Entity>> implements BaseService<ID, Entity> {

    protected final Session session;
    protected final Repository repository;

    @Override
    public void save(Entity entity) {
        try {
            session.beginTransaction();
            repository.save(entity);
            session.getTransaction().commit();
        } catch (TransactionException e) {
            throw new TransactionException(e.getMessage());
        }
    }

    @Override
    public void update(Entity entity) {
        try {
            session.beginTransaction();
            repository.update(entity);
            session.getTransaction().commit();
        } catch (TransactionException e) {
            throw new TransactionException(e.getMessage());
        }
    }

    @Override
    public void remove(Entity entity) {
        try {
            session.beginTransaction();
            repository.remove(entity);
            session.getTransaction().commit();
        } catch (TransactionException e) {
            throw new TransactionException(e.getMessage());
        }
    }

    @Override
    public Optional<Entity> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Collection<Entity> findAll() {
        session.beginTransaction();
        Collection<Entity> entities = repository.findAll();
        session.getTransaction().commit();
        return entities;
    }
}
