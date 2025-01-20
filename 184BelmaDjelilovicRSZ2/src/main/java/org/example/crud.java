package org.example;

import java.util.List;

public interface crud<T> {
    void create(T value) throws Exception;
    List<T> readAll() throws Exception;
    void deleteById(int id) throws Exception;
}
