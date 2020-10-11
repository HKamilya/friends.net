package ru.mvc.dao;

public interface DaoInterface<T> {
    public T findById(int id);
}
