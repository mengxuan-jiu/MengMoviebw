package com.bw.movie.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.App;
import com.bw.movie.R;
import com.bw.movie.band.LoginBand;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.url.Api;
import com.bw.movie.util.EncryptUtil;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {
    private SharedPreferences.Editor edit;
    private SharedPreferences sp;

    private ImageButton button;
    private EditText username;
    private EditText password;
    private Button forget_password;
    private TextView sign_up_now;
    private Button login;
    private ImageButton we_Chat;

    @Override
    public BasePresenter initPresenter() {
        return new PresenterImpl();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews() {
            button=findViewById(R.id.button);
            username=findViewById(R.id.username);
            password=findViewById(R.id.password);
            forget_password=findViewById(R.id.forget_password);
            sign_up_now=findViewById(R.id.sign_up_now);
            login=findViewById(R.id.login);
            we_Chat=findViewById(R.id.we_chat);


    }

    @Override
    public void setListener() {
        sign_up_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent(LoginActivity.this, RegisterActivity.class, Bundle.EMPTY);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<>();
                String em = username.getText().toString();

                String pass = password.getText().toString();
                String encrypt = EncryptUtil.encrypt(pass);
                map.put("email", em);
                map.put("pwd", encrypt);
                mPresenter.postRequest(Api.LOGIN, LoginBand.class, map);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void startCoding() {
        sp = getPreferences(MODE_PRIVATE);
        boolean isorno = sp.getBoolean("isorno", false);
        String yy = sp.getString("yy", "");
        String mm = sp.getString("mm", "");
        if (isorno) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
           // finish();
        }

    }
    @Override
    public void onSuccess(Object o) {
        if (o instanceof LoginBand) {
            String status = ((LoginBand) o).getStatus();
            Toast.makeText(this, "" + status, Toast.LENGTH_SHORT).show();
            Log.e("ll", "onSuccess: "+ status);
            if ("0000".equals(status)){
//                SharedPreferences lvxx = App.sContext.getSharedPreferences("lvxx", Context.MODE_PRIVATE);
//                if (lvxx != null) {
//                    SharedPreferences.Editor edit = lvxx.edit();
//                    LoginBand.ResultBean result = ((LoginBand) o).getResult();
//                    int userId = result.getUserId();
//                    String sessionId = result.getSessionId();
//
//                    edit.putInt("userId", userId);
//                    edit.putString("sessionId", sessionId);
//                    edit.commit();
//                }

                    LoginBand.ResultBean result = ((LoginBand) o).getResult();
                    int userId = result.getUserId();
                    String sessionId = result.getSessionId();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                this.edit = sp.edit();
                this.edit.putBoolean("isorno", true);
                String em = username.getText().toString();

                String pass = password.getText().toString();
                this.edit.putString("yy",em);
                this.edit.putString("mm",EncryptUtil.encrypt(pass));
                this.edit.apply();
                finish();
            }

        }
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
        Log.e("ll", "onSuccess: "+ error);
    }


}
