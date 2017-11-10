package com.example.d.healthbook.Controller;

/**
 * Created by D on 16.06.2017.
 */

public interface RequestInterface {
    <T> void  onRequestSuccess(T out);
    void onRequestFailure();
}
