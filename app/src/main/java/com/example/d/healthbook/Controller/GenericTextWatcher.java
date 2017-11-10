package com.example.d.healthbook.Controller;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.d.healthbook.Models.RegisterRequestModel;
import com.example.d.healthbook.R;

/**
 * Created by D on 29.05.2017.
 */

public class GenericTextWatcher implements TextWatcher {

    private View view;
    private RegisterRequestModel model;
    public GenericTextWatcher(View view, RegisterRequestModel model) {
        this.view = view;
        this.model = model;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        switch(view.getId()){
            case R.id.email_ed_field:
                model.setEmail(text);
                break;
            case R.id.telephone_ed_field:
                model.setPhone(text);
                break;
            case R.id.name_ed_field:
                model.setName(text);
                break;
            case R.id.surname_ed_field:
                model.setSurname(text);
                break;
            case R.id.password_ed_field:
                model.setPassword(text);
                break;
            case R.id.password_ed_field_repeat:
                model.setRepeatPassword(text);
                break;
        }
    }
}
