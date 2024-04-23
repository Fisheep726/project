package com.example.myapplication.listener;

import java.util.List;

public interface ResultListener<T> {

    void onSuccess(T data);

    void onFailure(String message);

    default void onSuccess(List<T> data){};
}
