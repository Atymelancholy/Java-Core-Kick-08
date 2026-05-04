package com.atymelancholy.arrays.repository.specification;

public interface Specification<T> {

    boolean isSatisfiedBy(T value);
}
