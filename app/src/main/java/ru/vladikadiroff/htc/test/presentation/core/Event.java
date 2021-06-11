package ru.vladikadiroff.htc.test.presentation.core;

public class Event<T> {

    private final T content;
    public Boolean contentHasBeenHandled = false;

    public Event(T content) {
        this.content = content;
    }

    public T getContentIfNotHandled() {
        if (contentHasBeenHandled) {
            return null;
        } else {
            contentHasBeenHandled = true;
            return content;
        }
    }

    public T peekContent() {
        return content;
    }

}
