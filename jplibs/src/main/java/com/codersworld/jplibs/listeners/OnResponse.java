package com.codersworld.jplibs.listeners;


public interface OnResponse<T> {
    void onSuccess(T response);
    void onError(String type,String error);
}
