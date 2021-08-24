package com.epam.task2.jwd.registar;

import com.epam.task2.jwd.entity.Shape;

import java.util.EventObject;

public class ShapeEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */

    public ShapeEvent(Shape source) {
        super(source);
    }

    @Override
    public Shape getSource() {
        return (Shape) super.getSource();
    }
}
