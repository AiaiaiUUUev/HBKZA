package com.example.d.healthbook.Controller;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D on 16.06.2017.
 */

public class RequestController<T> {
    RequestInterface viewInterface;

    public RequestController(RequestInterface viewInterface) {
        this.viewInterface = viewInterface;
    }

    public <T> void makeRequest(Call<T> call){
         call.enqueue(new Callback<T>() {
             @Override
             public void onResponse(Call<T> call, Response<T> response) {
                 int s = response.code();
                 if (response.errorBody() != null) {
                     //ERROR
                     viewInterface.onRequestFailure();
                 }
                 else {
                     T registeredUser = response.body();
                     //SUCCESS
                     viewInterface.<T>onRequestSuccess(registeredUser);
                 }
             }

             @Override
             public void onFailure(Call<T> call, Throwable t) {
                 //ERROR
                 viewInterface.onRequestFailure();
             }
         });
    }
}
