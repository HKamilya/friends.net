package ru.mvc.dao;


import java.util.List;

public abstract class AbstractDao<T> {
    public abstract String insert(T adr);

    public abstract T findById(int id);

    public abstract void update(T adr);

    public abstract void delete(T adr);

    public abstract List<T> findAll();
}

