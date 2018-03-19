package com.mgil.java.labs.tickets.dao;


import java.util.List;

public interface DAO<T> {


    public T find(Object id);
    public void remove(T entity);
    public T edit(T entity);
    public void persist(T entity);
    public List<T> findAll();
    public void deleteAll();


}