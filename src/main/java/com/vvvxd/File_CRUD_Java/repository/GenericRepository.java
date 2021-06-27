package com.vvvxd.File_CRUD_Java.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    T getById(ID id);

    List<T> getAll();

    T save(T s);

    T update(T s);

    void deleteById(ID id);

}
