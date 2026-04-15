package com.atymelancholy.arrays.repository.observer;

public interface RepositoryObserver<T> {

    void onAdd(T value);

    void onUpdate(T value);

    void onRemove(T value);
}
