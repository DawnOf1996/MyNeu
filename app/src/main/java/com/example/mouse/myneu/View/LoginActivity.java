package com.example.mouse.myneu.View;

import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mouse.myneu.R;
import com.lyp.networkhelper.activity.BaseActivity;


public class LoginActivity extends BaseActivity {

    Button login;
    TextView register;
    TextView forgetpsw;
    CheckBox remPsw;
    CheckBox autoLogin;
    EditText userName;
    EditText userPsw;
    EditText checkCode;
    Button checkCode_btn;

    private final static String TAG = "MySD_Login";

    Bitmap bitmap;
    Handler bitmapHandler;
    Handler loginHandler;

    @Override
    protected int getLayoutId() {
        return R.layout.login;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }
}
