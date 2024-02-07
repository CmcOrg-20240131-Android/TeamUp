package com.cmcorg20230301.teamup.util;

public class CallBack<T> {

    private T value;

    public CallBack() {
    }

    public CallBack(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
