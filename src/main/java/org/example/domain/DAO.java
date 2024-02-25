package org.example.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface DAO<T> {
    public ArrayList<T> getAll();
    public T get(Integer id);
    public T save(T data);
    public T update(T data);
    public void delete(T data);
    public void saveAll(List<T> data);
}
