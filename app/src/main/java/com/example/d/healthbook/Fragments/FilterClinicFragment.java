package com.example.d.healthbook.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.ClickedFilterItemsClinic;
import com.example.d.healthbook.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by D on 24.07.2017.
 */

public class FilterClinicFragment extends Fragment {
    private String specialityAutocompleteTV = "";
    public  AutoCompleteTextView myAutoComplete;
    private boolean checkAutocomplete = false;
    @BindView(R.id.searchBtn)
    Button searchBtn;
    @BindView(R.id.titleClinicET)
    EditText titleClinicET;
    @BindView(R.id.nameClinicDoc)
    TextView nameClinicDoc;


    @BindView(R.id.invisibleView)
    View invisibleView;

    @BindView(R.id.main_container)
    LinearLayout main_container;

    private String titleClicnic = "";
    private String cityName;
    private Realm mRealm;
    private Spinner spinner2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //App.getInstance().setCurrentFragment(this);

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.filter_fragment, container, false);
        ButterKnife.bind(this, view);
        mRealm = Realm.getInstance(getActivity());
        return view;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        final String[] SPINNERLIST_CITY = {"Алматы"};

//        CustomAdapterSpinner customPagerAdapter = new CustomAdapterSpinner(getActivity(), R.layout.text_spinner_item_custom, SPINNERLIST,
//                "This is hint displayed in spinner");
//
//        customPagerAdapter.setDropDownViewResource(R.layout.my_spinner_drop_down);
//        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerSpeciality);
//        spinner.setAdapter(customPagerAdapter);
//        spinner.setSelection(customPagerAdapter.getCount());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                main_container.setVisibility(View.VISIBLE);
                main_container.startAnimation(AnimationUtils
                        .loadAnimation(getContext(),
                                R.anim.from_up_fragment));
                invisibleView
                        .startAnimation(AnimationUtils
                                .loadAnimation(getContext(),
                                        R.anim.appeare_animation));
            }
        }, 100);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), R.layout.text_spinner_item_custom_gray, SPINNERLIST_CITY);
        adapter2.setDropDownViewResource(R.layout.my_spinner_drop_down);
        spinner2 = (Spinner) view.findViewById(R.id.spinnerCity);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(adapter2.getCount() - 1);


        myAutoComplete = (AutoCompleteTextView) view.findViewById(R.id.myAutoComplete);
        myAutoComplete.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.my_spinner_drop_down, SPINNERLIST));
        myAutoComplete.setThreshold(0);


        nameClinicDoc.setText("Наименование");
        titleClinicET.setHint("Введите наименование клиники");
        ((MainPageActivity) getActivity()).changeMenuItems(0);
        loadRealmItem();


        clickAndTouchListeners();


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleClicnic = titleClinicET.getText().toString();
                cityName = spinner2.getSelectedItem().toString();

                for (int i = 0; i < SPINNERLIST.length; i++) {
                    if (specialityAutocompleteTV.equals(SPINNERLIST[i])) {

                        checkAutocomplete = true;
                    }
                }
                if (checkAutocomplete) {

                    Bundle bundle = new Bundle();
                    bundle.putString(GlobalVariables.titleClinic_key, titleClicnic);
                    bundle.putString(GlobalVariables.cityName_key, cityName);
                    bundle.putString(GlobalVariables.speciality_key, specialityAutocompleteTV);
                    bundle.putBoolean("fromClinicFilter", true);


                    ((See_Clinic_List_Fragment) ((ViewPagerFragment) getActivity()
                            .getSupportFragmentManager()
                            .findFragmentById(R.id.changable_fragment_container)
                    ).customPagerAdapter.getCurrentFragment()).
                            seeClinicListFilterMethod("1", specialityAutocompleteTV, titleClicnic);


                    ((MainPageActivity) getActivity()).changeMenuItems(1);
                    App.getInstance().removeSubFragment((MainPageActivity) getActivity());


                    MainPageActivity.hideKeyboard(myAutoComplete);
                    MainPageActivity.mToggle.setDrawerIndicatorEnabled(true);

                    setRealmItem();


                    checkAutocomplete = false;

                } else {
                    Toast.makeText(getActivity(), "Выберите из того что есть в списке специальностей", Toast.LENGTH_SHORT).show();

                }
//
            }
        });
    }


    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) myAutoComplete.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(myAutoComplete.getWindowToken(), 0);
    }

    private void clickAndTouchListeners() {

        myAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                hideKeyboard();
            }

        });


        final InputMethodManager imm = (InputMethodManager) myAutoComplete.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        final int[] counter = {0};
        myAutoComplete.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                myAutoComplete.showDropDown();
                return false;
//                if (!clickedFirst && counter[0] <=2) {
//                    myAutoComplete.showDropDown();
//                    imm.hideSoftInputFromWindow(myAutoComplete.getWindowToken(), 0);
//                    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//
//                    counter[0]++;
//                    return true;
//                } else {
//                    counter[0]++;
////                    myAutoComplete.showDropDown();
//                    myAutoComplete.requestFocus();
//                    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//                    imm.showSoftInput(myAutoComplete, InputMethodManager.SHOW_IMPLICIT);
//                    clickedFirst = false;
//                    return true;
//
//                }
            }

        });

        myAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                specialityAutocompleteTV = String.valueOf(s);

            }
        });


        spinner2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        main_container.setVisibility(View.VISIBLE);
                        main_container.startAnimation(AnimationUtils
                                .loadAnimation(getContext(),
                                        R.anim.to_down_fragment));
                        invisibleView
                                .startAnimation(AnimationUtils
                                        .loadAnimation(getContext(),
                                                R.anim.disappeare_animation));


                    }
                }, 100);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        App.getInstance().removeSubFragment((MainPageActivity) getActivity());


                    }
                }, 600);


                MainPageActivity.hideKeyboard(myAutoComplete);
                MainPageActivity.mToggle.setDrawerIndicatorEnabled(true);
                ((MainPageActivity) getActivity()).changeMenuItems(1);


                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void loadRealmItem() {
        RealmResults<ClickedFilterItemsClinic> clickedFilterItemses = mRealm.allObjects(ClickedFilterItemsClinic.class);
        if (!clickedFilterItemses.isEmpty()) {
            mRealm.beginTransaction();
            specialityAutocompleteTV = clickedFilterItemses.get(0).getSpeciality();
            myAutoComplete.setText(specialityAutocompleteTV);
            titleClinicET.setText(clickedFilterItemses.get(0).getTitleClicnic());
            mRealm.commitTransaction();
        }
    }

    public void setRealmItem() {
        RealmResults<ClickedFilterItemsClinic> clickedFilterItemses = mRealm.allObjects(ClickedFilterItemsClinic.class);
        if (clickedFilterItemses.isEmpty()) {
            mRealm.beginTransaction();
            ClickedFilterItemsClinic clickedFilterItemses2 = mRealm.createObject(ClickedFilterItemsClinic.class);
            clickedFilterItemses2.setTitleClicnic(titleClicnic);
            clickedFilterItemses2.setCityName(cityName);
            clickedFilterItemses2.setSpeciality(specialityAutocompleteTV);
            mRealm.commitTransaction();
        } else {
            mRealm.beginTransaction();
            clickedFilterItemses.get(0).setTitleClicnic(titleClicnic);
            clickedFilterItemses.get(0).setCityName(cityName);
            clickedFilterItemses.get(0).setSpeciality(specialityAutocompleteTV);
            mRealm.commitTransaction();
        }
    }

    final String[] SPINNERLIST = {"Акушер-гинеколог",
            "Аллерголог",
            "Аллерголог-иммунолог",
            "Андролог",
            "Анестезиолог",
            "Анестезиолог-реаниматолог",
            "Вакцинолог",
            "Вертебролог",
            "Врач восстановительной медицины",
            "Врач ЛФК",
            "Врач МРТ",
            "Врач общей практики",
            "Врач УЗИ",
            "Врач функциональной диагностики",
            "Гастроэнтеролог",
            "Гематолог",
            "Гепатолог",
            "Гинеколог",
            "Гинеколог-онколог",
            "Гинеколог-эндокринолог",
            "Гирудотерапевт (лечение пиявками)",
            "Гомеопат",
            "Дерматовенеролог",
            "Дерматокосметолог",
            "Дерматолог",
            "Дерматоонколог",
            "Диабетолог",
            "Диетолог",
            "Иглорефлексотерапевт",
            "Иммунолог",
            "Инфекционист",
            "Кардиолог",
            "Кардиохирург",
            "Кинезиолог",
            "Клинический психолог",
            "Логопед",
            "Логопед-дефектолог",
            "ЛОР (отоларинголог)",
            "Маммолог",
            "Мануальный терапевт",
            "Массажист",
            "Медицинский генетик",
            "Миколог",
            "Нарколог",
            "евролог",
            "невропатолог",
            "Нейрофизиолог",
            "Нейрохирург",
            "Неонатолог",
            "Нефролог",
            "Онколог",
            "Онколог-маммолог",
            "Ортопед",
            "Остеопат",
            "Оториноларинголог",
            "Офтальмолог (окулист)",
            "Офтальмохирург",
            "Педиатр",
            "Пластический хирург",
            "Проктолог",
            "Психиатр",
            "Психоаналитик",
            "Психолог",
            "Психоневролог",
            "Психотерапевт",
            "Пульмонолог",
            "Радиолог",
            "Реаниматолог",
            "Ревматолог",
            "Рентгенолог",
            "Репродуктолог",
            "Рефлексотерапевт",
            "Сексопатолог",
            "Семейный врач",
            "Семейный психолог",
            "Сомнолог",
            "Сосудистый хирург",
            "Стоматолог",
            "Стоматолог-имплантолог",
            "Стоматолог-ортодонт",
            "Стоматолог-ортопед",
            "Стоматолог-терапевт",
            "Стоматолог-хирург",
            "Терапевт",
            "Травматолог",
            "Травматолог-ортопед",
            "Трихолог",
            "Уролог",
            "Уролог-андролог",
            "Физиотерапевт",
            "Флеболог",
            "Хирург",
            "Хирург-ортопед",
            "Хирург-флеболог",
            "Челюстно-лицевой хирург",
            "Эндокринолог",
            "Эндоскопист"};
}



