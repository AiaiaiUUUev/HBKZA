package com.example.d.healthbook.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Appi.API_Controller;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.Controller.RequestController;
import com.example.d.healthbook.Controller.RequestInterface;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.LoginResponseModel;
import com.example.d.healthbook.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener , RequestInterface {

    @BindView(R.id.activity_start_bts_container)
    LinearLayout activity_start_bts_container;

    @BindView(R.id.activity_start_login_btn)
    Button activity_start_login_btn;

    @BindView(R.id.activity_start_login_ets_container)
    LinearLayout activity_start_login_ets_container;

    @BindView(R.id.activity_start_social_container)
    LinearLayout activity_start_social_container;

    @BindView(R.id.activity_start_login_btns_container)
    LinearLayout activity_start_login_btns_container;

    @BindView(R.id.activity_start_back_tv)
    TextView activity_start_back_tv;

    @BindView(R.id.activity_welcome_action_authenticate)
    Button activity_welcome_action_authenticate;

    @BindView(R.id.emailET)
    EditText emailET;

    @BindView(R.id.passwordET)
    EditText passwordET;

    @BindView(R.id.activity_welcome_action_register)
    Button activity_welcome_action_register;

    protected RequestController authPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        authPresenter = new RequestController(this);
        //View Bind Initialize
        ButterKnife.bind(this);
        init_clicks();
//        emailET.setText("drugoi.nik@gmail.com");
        emailET.setText("alpamis.nurtas@mail.ru");
        passwordET.setText("123");
        authPresenter.<LoginResponseModel>makeRequest(
                App.getApi().loginUser(API_Controller.loginJson(emailET.getText().toString(), passwordET.getText().toString(), false))
        );
    }

    private LoginResponseModel registeredUser;

    public void logInMethod(String email, String password, Boolean rememberMe) {

        App.getApi().loginUser(API_Controller.loginJson(email, password, rememberMe)).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

        if(response.code()==200 ){
            registeredUser = response.body();
            MainController.showPreparedToast(AuthActivity.this,"Success");
            GlobalVariables.user_auth_token = registeredUser.getAuthToken();
            GlobalVariables.user_id =registeredUser.getUserId();

            Intent intent= new Intent(AuthActivity.this,UserActivityInfo.class);
            startActivity(intent);

        }
        else{
            MainController.showPreparedToast(AuthActivity.this,"Failed");
        }

    }

    @Override
    public void onFailure(Call<LoginResponseModel> call, Throwable t) {
        Toast.makeText(AuthActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
    }
});
    }


    void init_clicks() {
        activity_welcome_action_authenticate.setOnClickListener(this);
        activity_start_back_tv.setOnClickListener(this);
        activity_start_login_btn.setOnClickListener(this);
        activity_welcome_action_register.setOnClickListener(this);
    }

    void viewConvertion(String typeOfConvertion) {
        final String type = typeOfConvertion;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (type) {
                    case "ShowLogin": {
                        forStartViews(View.GONE);
                        forLoginViews(View.VISIBLE);
                        break;
                    }
                    case "BackLogin": {
                        forStartViews(View.VISIBLE);
                        forLoginViews(View.GONE);
                        break;
                    }
                    default:
                        break;
                }
            }
        });
    }

    void forStartViews(int visiblitiltype) {
        activity_start_bts_container.setVisibility(visiblitiltype);
        activity_start_social_container.setVisibility(visiblitiltype);
    }

    void forLoginViews(int visiblitiltype) {
        activity_start_login_btns_container.setVisibility(visiblitiltype);
        activity_start_login_ets_container.setVisibility(visiblitiltype);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_welcome_action_authenticate: {
                viewConvertion("ShowLogin");
                break;
            }
            case R.id.activity_start_back_tv: {
                viewConvertion("BackLogin");
                break;
            }
            case R.id.activity_start_login_btn: {
                //Login
                if (emailET.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "Пожалуйста введите eMail", Toast.LENGTH_SHORT).show();
                } else if (passwordET.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "Пожалуйста введите пароль", Toast.LENGTH_SHORT).show();
                } else {
                    logInMethod(emailET.getText().toString(), passwordET.getText().toString(), true);
                    break;
                }
//                Intent intent= new Intent(AuthActivity.this,MainPageActivity.class);
//                startActivity(intent);
//                break;
            }
            case R.id.activity_welcome_action_register: {
                startActivity(new Intent(this,RegisterActivity.class));
                finish();
                break;
            }
            default: {
                MainController.showToast(AuthActivity.this, "Not implemented");
                break;
            }
        }
    }

    @Override
    public <T> void onRequestSuccess(T out)
    {
        LoginResponseModel data = (LoginResponseModel)out ;
    }

    @Override
    public void onRequestFailure() {
        MainController.showPreparedToast(this,"Error");
    }
}
