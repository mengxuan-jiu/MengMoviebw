package com.bw.movie.view;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.band.LoginBand;
import com.bw.movie.band.RegisteredBand;
import com.bw.movie.band.SendOutEmailCodeBand;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.url.Api;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity {


    private EditText name;
    private EditText pwb;
    private EditText r_email;
    private EditText r_syz;
    private Button r_f_email;
    private Button zc;

    @Override
    public BasePresenter initPresenter() {
        return new PresenterImpl();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initViews() {
        name = findViewById(R.id.name);
        pwb = findViewById(R.id.pwb);
        r_email = findViewById(R.id.r_email);
        r_syz = findViewById(R.id.r_syz);
        r_f_email = findViewById(R.id.r_f_email);
        zc = findViewById(R.id.zc);

    }

    @Override
    public void setListener() {
        r_f_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> map = new HashMap<>();
                String email = r_email.getText().toString();
                map.put("email", email);
                mPresenter.postRequest(Api.SENDOUTEMAILCODE, SendOutEmailCodeBand.class, map);
            }
        });
        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> map = new HashMap<>();

                String sname = name.getText().toString();
                String spwb = pwb.getText().toString();
                String semail = r_email.getText().toString();
                String ssyz = r_syz.getText().toString();
                map.put("nickName", sname);
                map.put("pwd", spwb);
                map.put("email", semail);
                map.put("code", ssyz);
                mPresenter.postRequest(Api.REGISTER, SendOutEmailCodeBand.class, map);
            }
        });

    }

    @Override
    public void startCoding() {

    }


    @Override
    public void onSuccess(Object o) {
        if (o instanceof SendOutEmailCodeBand) {
         if ("0000".equals(((SendOutEmailCodeBand) o).getStatus())){
             Toast.makeText(this, ""+((SendOutEmailCodeBand) o).getMessage(), Toast.LENGTH_SHORT).show();
         }
            Toast.makeText(this, ""+((SendOutEmailCodeBand) o).getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (o instanceof RegisteredBand) {
            if ("0000".equals(((SendOutEmailCodeBand) o).getStatus())){

                Toast.makeText(this, ""+((SendOutEmailCodeBand) o).getMessage(), Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, ""+((SendOutEmailCodeBand) o).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(String error) {

    }


}
