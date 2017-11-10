package com.example.d.healthbook.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.d.healthbook.Activities.AuthActivity;
import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Appi.API_Controller;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Controller.GenericTextWatcher;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.Models.RegisterRequestModel;
import com.example.d.healthbook.Models.RegisterResponse;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.NonSwipeableViewPager;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D on 29.05.2017.
 */

public class RegistrationFragment extends Fragment {
    @BindView(R.id.email_ed_field)
    EditText email_ed_field;
    @BindView(R.id.telephone_ed_field)
    EditText telephone_ed_field;

    @BindView(R.id.name_ed_field)
    EditText name_ed_field;
    @BindView(R.id.surname_ed_field)
    EditText surname_ed_field;

    @BindView(R.id.password_ed_field)
    EditText password_ed_field;
    @BindView(R.id.password_ed_field_repeat)
    EditText password_ed_field_repeat;


    FrameLayout frameLayout;
    NonSwipeableViewPager viewpager;
    ViewPagerAdapter viewpager_adapter;
    private RegisterResponse registeredUser;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_registration, container, false));
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        viewpager = (NonSwipeableViewPager) view.findViewById(R.id.viewpager);
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);
        viewpager.setAdapter(viewpager_adapter = new ViewPagerAdapter());
        indicator.setViewPager(viewpager);
        viewpager.setCurrentItem(0);
    }

    public boolean isPagerInBounds(int index) {
        return (index >= 0 && index < (viewpager_adapter.getCount()));
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            view.removeView((View) object);
        }

        RegisterRequestModel sendModel;

        RegisterRequestModel SetDataToModel() {
            if (sendModel == null)
                sendModel = new RegisterRequestModel();
            return sendModel;
        }


        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            String[] hints = {"", "Ваш рост", "Ваш вес", "Дата рождения", "Пол"};
            View child = null;
            switch (position) {
                case 0:
                    child = getActivity().getLayoutInflater().inflate(R.layout.map_plain_text, null);
//                    ((Button) child.findViewById(R.id.btn_back)).setEnabled(false);
//                    ((Button) child.findViewById(R.id.btn_back)).setTextColor(ContextCompat.getColor(getContext(), R.color.bpLight_gray));
                    ((HtmlTextView) child.findViewById(R.id.plain_text)).setHtml("<p>Запрашивает следующие данные для нормальной работы приложения и возможности отслеживания ваших данных:</p><ul><li> Доступ к галерее</li><li>Использование данных из списка ваших контактов</li><li>Доступ к геолокации</li><li>Доступ к аккаунту</li></ul><p>Все ваши данные находятся под защитой и будут использованы согласно оферте</p>");
                    break;

                case 1:
                    child = getActivity().getLayoutInflater().inflate(R.layout.map_edittexts, null);
                    EditText emailEd = (EditText) child.findViewById(R.id.email_ed_field);
                    emailEd.setText(SetDataToModel().getEmail());
                    emailEd.addTextChangedListener(new GenericTextWatcher(emailEd, sendModel));

                    EditText phoneEd = (EditText) child.findViewById(R.id.telephone_ed_field);
                    phoneEd.setText(SetDataToModel().getPhone());
                    phoneEd.addTextChangedListener(new GenericTextWatcher(phoneEd, sendModel));

                    //  ((EditText) child.findViewById(R.id.text_field)).setHint(hints[position]);
                    break;

                case 2:
                    child = getActivity().getLayoutInflater().inflate(R.layout.map_text_field, null);
                    EditText nameET = (EditText) child.findViewById(R.id.name_ed_field);
                    nameET.setText(SetDataToModel().getName());
                    nameET.addTextChangedListener(new GenericTextWatcher(nameET, sendModel));

                    EditText surnameET = (EditText) child.findViewById(R.id.surname_ed_field);
                    surnameET.setText(SetDataToModel().getSurname());
                    surnameET.addTextChangedListener(new GenericTextWatcher(surnameET, sendModel));

//                    ((EditText) child.findViewById(R.id.text_field)).setHint(hints[position]);
                    break;

                case 3:
                    child = getActivity().getLayoutInflater().inflate(R.layout.map_password, null);
                    EditText passwordET = (EditText) child.findViewById(R.id.password_ed_field);
                    passwordET.setText(SetDataToModel().getPassword());
                    passwordET.addTextChangedListener(new GenericTextWatcher(passwordET, sendModel));

                    EditText password_repeatET = (EditText) child.findViewById(R.id.password_ed_field_repeat);
                    password_repeatET.setText(SetDataToModel().getPassword());
                    password_repeatET.addTextChangedListener(new GenericTextWatcher(password_repeatET, sendModel));
                    break;

                case 4:
                    child = getActivity().getLayoutInflater().inflate(R.layout.map_spinner, null);
//                    ((TextView) child.findViewById(R.id.text_field)).setText(hints[position]);
                    break;
            }
            child.setPadding(0, 64, 0, 0);
            ((Button) child.findViewById(R.id.btn_back)).setText("Назад");
            ((Button) child.findViewById(R.id.btn_forward)).setText("Далее");

            child.findViewById(R.id.btn_back).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int index = viewpager.getCurrentItem() - 1;
                            if (isPagerInBounds(index)) {
                                viewpager.setCurrentItem(index);
                            } else if (index == -1) {
                                Intent intent = new Intent(getContext(), AuthActivity.class);
                                startActivity(intent);
                            }

                        }
                    }
            );


            child.findViewById(R.id.btn_forward).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int index = viewpager.getCurrentItem() + 1;

                            if (index == 1) {
                                if (isPagerInBounds(index)) viewpager.setCurrentItem(index);
                            }
                            if (index == 2) {
                                if (sendModel.getEmail().equals("")) {
                                    Toast.makeText(getActivity(), "Введите eMail", Toast.LENGTH_SHORT).show();
                                } else if (sendModel.getPhone().equals("")) {
                                    Toast.makeText(getActivity(), "Введите телефон", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (isPagerInBounds(index)) viewpager.setCurrentItem(index);
                                }
                            }

                            if (index == 3) {
                                if (sendModel.getName().equals("")) {
                                    Toast.makeText(getActivity(), "Введите имя", Toast.LENGTH_SHORT).show();
                                } else if (sendModel.getSurname().equals("")) {
                                    Toast.makeText(getActivity(), "Введите фамилию", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (isPagerInBounds(index)) viewpager.setCurrentItem(index);
                                }
                            }
                            if (index == 4) {
                                if (sendModel.getPassword().equals("")) {
                                    Toast.makeText(getActivity(), "Введите пароль", Toast.LENGTH_SHORT).show();
                                } else if (sendModel.getRepeatPassword().equals("")) {
                                    Toast.makeText(getActivity(), "Повторите пароль", Toast.LENGTH_SHORT).show();
                                } else if (!sendModel.getPassword().equals("") && !sendModel.getRepeatPassword().equals("") &&
                                        !sendModel.getPassword().equals(sendModel.getRepeatPassword())) {
                                    Toast.makeText(getActivity(), "Вы не верно повторили пароль", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (isPagerInBounds(index)) viewpager.setCurrentItem(index);
                                }
                            }

                            if (index == viewpager_adapter.getCount()) {

                                registerMethod("REGISTRATION", sendModel.getEmail(), sendModel.getPhone(),
                                        sendModel.getName(), sendModel.getSurname(), sendModel.getPassword());

                                // Application.getInstance().setActivity(Application.getInstance().getCurrentActivity(), AuthActivity.class);
                                return;
                            }


                        }
                    }
            );

            view.addView(child, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            return child;
        }
    }

    public void registerMethod(String type, String email, String phone, String name, String surnmae, String password) {

        App.getApi().registerUser(API_Controller.registerJson(type, email, phone, name, surnmae, password)).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                if (response.code() == 200) {
                    response.body().toString();
                    registeredUser = response.body();
                    MainController.showPreparedToast(getActivity(), "Success");
                    Intent intent= new Intent(getActivity(),MainPageActivity.class);
                    startActivity(intent);
                } else {
                    MainController.showPreparedToast(getActivity(), "Failed");
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
