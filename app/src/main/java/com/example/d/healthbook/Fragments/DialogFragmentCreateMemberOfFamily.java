package com.example.d.healthbook.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Appi.API_Controller;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.ResponseAlldataOfMemer;
import com.example.d.healthbook.Models.ResponseDeleteMemberData;
import com.example.d.healthbook.Models.ResponseMyFamilyMemberCreate;
import com.example.d.healthbook.R;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.d.healthbook.Controller.MainController.birthdayTextChangeListener;

/**
 * Created by D on 05.07.2017.
 */

public class DialogFragmentCreateMemberOfFamily extends DialogFragment {
    @BindView(R.id.nameProfileMember)
    EditText nameProfileMember;

    @BindView(R.id.surnameProfileMember)
    EditText surnameProfileMember;

    @BindView(R.id.middleameProfileMember)
    EditText middleameProfileMember;

    @BindView(R.id.birthdateProfileMember)
    EditText birthdateProfileMember;

    @BindView(R.id.heightProfileMember)
    EditText heightProfileMember;

    @BindView(R.id.weightProfileMember)
    EditText weightProfileMember;

    @BindView(R.id.email_et_member)
    EditText email_et_member;

    @BindView(R.id.clickBtnSaveProfileMember)
    Button clickBtnSaveProfileMember;

    @BindView(R.id.clickBtnExitProfileMember)
    Button clickBtnExitProfileMember;

    private ProgressDialog progressDialog;

    private String name = "", surname = "", middleName, gender,
            birthday, height, weight, role, email;


    private String LOG_TAG = "DIALOG";

    private ResponseMyFamilyMemberCreate registeredUser;
    private Spinner spinnerEditMemberGender;
    private Spinner spinnerEditMemberRole;

    private String aBirth, bBirth, cBirth;

    private String formatterCheck;
    private boolean checkBirthDate = false;
    private Matcher matcher;
    private long birthDateLong;
    private ResponseAlldataOfMemer allDataOfMember;
    private ResponseDeleteMemberData deleteMemberOfFamily;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            loadOldData(getArguments().getString("id"));


        }


    }

    private void loadOldData(String id) {
        seeAlldataOfMember(id);

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.dialog_fragment_create_member_of_family, null);


        spinnerEditMemberGender = (Spinner) v.findViewById(R.id.spinnerEditMemberGender);
        final String[] spinnerGender = {"Мужской", "Женский"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),
                R.layout.text_spinner_item_custom_gray, spinnerGender);
        adapter2.setDropDownViewResource(R.layout.my_spinner_drop_down);
        spinnerEditMemberGender.setAdapter(adapter2);
        spinnerEditMemberGender.setSelection(adapter2.getCount() - 1);


        spinnerEditMemberRole = (Spinner) v.findViewById(R.id.spinnerEditMemberRole);
        final String[] spinnerRole = {"Родитель", "Супруг(а)", "Ребенок"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(),
                R.layout.text_spinner_item_custom_gray, spinnerRole);
        adapter1.setDropDownViewResource(R.layout.my_spinner_drop_down);
        spinnerEditMemberRole.setAdapter(adapter1);
        spinnerEditMemberRole.setSelection(adapter1.getCount() - 1);


        ButterKnife.bind(this, v);


        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            clickBtnSaveProfileMember.setText("Обновить");
        }

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        birthdayTextChangeListener(birthdateProfileMember);

        clickBtnSaveProfileMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameProfileMember.getText().toString();
                surname = surnameProfileMember.getText().toString();
                middleName = middleameProfileMember.getText().toString();


                if (spinnerEditMemberGender.getSelectedItem().toString().equals("Мужской")) {
                    gender = "1";
                } else {
                    gender = "0";
                }

                if (spinnerEditMemberRole.getSelectedItem().toString().equals("Родитель")) {
                    role = "1";
                } else if (spinnerEditMemberRole.getSelectedItem().toString().equals("Супруг(а)")) {
                    role = "2";
                } else if (spinnerEditMemberRole.getSelectedItem().toString().equals("Ребенок")) {
                    role = "3";
                }


                birthday = birthdateProfileMember.getText().toString();


                height = heightProfileMember.getText().toString();

                weight = weightProfileMember.getText().toString();
                email = email_et_member.getText().toString();


                if (name.equals("")) {
                    MainController.showToast(getActivity(), "Пожалуйста введите Имя");
                } else if (surname.equals("")) {
                    MainController.showToast(getActivity(), "Пожалуйста введите Фамилию");
                } else if (birthday.equals("")) {
                    MainController.showToast(getActivity(), "Введите день рожденья (ДД)");
                } else if (!birthday.equals("")) {

                    String birthdaySplit[] = birthday.split("/");

                    aBirth = birthdaySplit[0];
                    bBirth = birthdaySplit[1];
                    cBirth = birthdaySplit[2];

                    if (checkMathcher(aBirth)) {
                        MainController.showToast(getActivity(), "Введите день рожденья (ДД)");
                    } else if (checkMathcher(bBirth)) {
                        MainController.showToast(getActivity(), "Введите месяц рожденья (MM)");
                    } else if (checkMathcher(cBirth)) {
                        MainController.showToast(getActivity(), "Введите год рожденья (ГГГГ)");
                    } else {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = sdf.parse(birthday);

                            birthDateLong = date.getTime();
                            birthday = String.valueOf(birthDateLong);

                            checkBirthDate = true;


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (getArguments() != null) {
                            updateFamilyMember(name + " " + surname + " " + middleName, name, surname, middleName,
                                    gender, role, birthday,
                                    height, weight, "2",
                                    email, allDataOfMember.getId());

                        } else {

                            progressDialog = ProgressDialog.show(getActivity(), "", "Отравка данных");
                            progressDialog.setCancelable(false);

                            createMyfamilyMember(name + " " + surname + " " + middleName, name, surname, middleName,
                                    gender, role, birthday,
                                    height, weight, "2",
                                    email);
                        }
                    }


                }


            }
        });


        email_et_member.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    clickBtnSaveProfileMember.performClick();
                    return true;
                }
                return false;
            }
        });

        clickBtnExitProfileMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO ВВОД ДАТЫ РОЖДЕНИЯ, НУЖНО ОТПРАВЛЯТЬ В LONGE


                dismiss();


            }
        });
    }


    public boolean checkMathcher(String a) {
        Pattern pattern = Pattern.compile("[а-яА-ЯёЁa-zA-Z]");
        matcher = pattern.matcher(a);
        return matcher.find();

    }

    public void setOldData() {
        String nameSplit[] = allDataOfMember.getName().split(" ");
        nameProfileMember.setText(nameSplit[0]);
        surnameProfileMember.setText(allDataOfMember.getUserSurname());
        middleameProfileMember.setText(allDataOfMember.getUserPatronymic());


        if (allDataOfMember.getGender() == 1) {
            spinnerEditMemberGender.setSelection(0);
        } else {
            spinnerEditMemberGender.setSelection(1);
        }

        if (allDataOfMember.getRole() == 1) {
            spinnerEditMemberRole.setSelection(0);
        } else if (allDataOfMember.getRole() == 2) {
            spinnerEditMemberRole.setSelection(1);
        } else if (allDataOfMember.getRole() == 3) {
            spinnerEditMemberRole.setSelection(2);
        }


        String[] birthdaySplit = allDataOfMember.getBirthday().split(" ");

        birthdateProfileMember.setText(birthdaySplit[0]);


        if(allDataOfMember.getHeight()!=null)
            heightProfileMember.setText(allDataOfMember.getHeight() + "");
        if(allDataOfMember.getWeight()!=null)
            weightProfileMember.setText(allDataOfMember.getWeight() + "");
        if(allDataOfMember.getEmail()!=null)
            email_et_member.setText(allDataOfMember.getEmail());


    }


    private void updateFamilyMember(String name, String user_name, String user_surname,
                                    String user_patronymic, String gender, String role,
                                    String birthday, String height, String weight,
                                    String type, String email, String id) {

        App.getApi().updateFamilyMember(GlobalVariables.user_auth_token,
                API_Controller.createFamilyMember(name, user_name, user_surname,
                        user_patronymic, gender, role,
                        birthday, height, weight,
                        type, email, id)).enqueue(new Callback<ResponseMyFamilyMemberCreate>() {
            @Override
            public void onResponse(Call<ResponseMyFamilyMemberCreate> call, Response<ResponseMyFamilyMemberCreate> response) {
                int s = response.code();

                registeredUser = response.body();

                if (registeredUser != null) {
                    String b = registeredUser.getId();

                    Toast.makeText(getActivity(), "Загрузка прошла успешно ", Toast.LENGTH_SHORT).show();

                    dismiss();

                } else {
                    Toast.makeText(getActivity(), "Ошибка при загрузке ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseMyFamilyMemberCreate> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void createMyfamilyMember(String name, String user_name, String user_surname,
                                     String user_patronymic, String gender, String role,
                                     String birthday, String height, String weight,
                                     String type, String email) {


        App.getApi().createFamilyMember(GlobalVariables.user_auth_token,
                API_Controller.createFamilyMember(name, user_name, user_surname,
                        user_patronymic, gender, role,
                        birthday, height, weight,
                        type, email, "")).enqueue(new Callback<ResponseMyFamilyMemberCreate>() {
            @Override
            public void onResponse(Call<ResponseMyFamilyMemberCreate> call, Response<ResponseMyFamilyMemberCreate> response) {
                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                progressDialog.dismiss();
                registeredUser = response.body();

                if (registeredUser != null) {
                    String b = registeredUser.getId();

                    Toast.makeText(getActivity(), "Загрузка прошла успешно ", Toast.LENGTH_SHORT).show();

                    dismiss();

                } else {
                    Toast.makeText(getActivity(), "Ошибка при загрузке ", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<ResponseMyFamilyMemberCreate> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

    public void seeAlldataOfMember(String id) {

        App.getApi().seeAlldataofmember(GlobalVariables.user_auth_token, id).enqueue(new Callback<ResponseAlldataOfMemer>() {
            @Override
            public void onResponse(Call<ResponseAlldataOfMemer> call, Response<ResponseAlldataOfMemer> response) {
                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                allDataOfMember = response.body();

                if (allDataOfMember != null) {
                    String b = allDataOfMember.getId();
                    setOldData();


                } else {
                    Toast.makeText(getActivity(), "Ошибка при загрузке ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseAlldataOfMemer> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        dismiss();
    }


}
