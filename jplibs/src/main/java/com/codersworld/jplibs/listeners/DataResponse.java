package com.codersworld.jplibs.listeners;


public interface DataResponse<T> {

    void onSuccess(T response);

    void onFaliure(String error);
}
