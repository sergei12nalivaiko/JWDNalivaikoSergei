package com.epam.task2.jwd.repository;

import com.epam.task2.jwd.entity.Shape;


public interface Specification<T extends Shape> {
    boolean specify(T t);
}
