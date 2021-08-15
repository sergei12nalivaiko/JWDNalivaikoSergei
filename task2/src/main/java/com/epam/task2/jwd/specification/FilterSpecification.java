package com.epam.task2.jwd.specification;

import com.epam.task2.jwd.entity.Shape;


public interface FilterSpecification<T extends Shape> {
    boolean specify(T t);
}
