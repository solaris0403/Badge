package com.example.badgelibrary;

/**
 * Created by tony on 5/16/17.
 */

public abstract class Component {
    protected abstract void add(Component company);

    protected abstract void romove(Component company);

    protected abstract void display(int depth);
}
