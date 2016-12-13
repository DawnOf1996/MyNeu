package com.example.mouse.myneu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mouse.myneu.util.Constant;
import com.example.mouse.myneu.util.SPUtils;
import com.lyp.networkhelper.activity.BaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;


public class LoginActivity extends BaseActivity {


    EditText etName,etPassword,etValidate;
    ImageView imgValidate,refresh;
    Button btnLogin;
    CheckBox autoLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        refreshImgCode();
    }

    @Override
    protected void initViews() {
        etName= (EditText) findViewById(R.id.name);
        etPassword= (EditText) findViewById(R.id.password);
        etValidate= (EditText) findViewById(R.id.imgpassword);
        imgValidate= (ImageView) findViewById(R.id.validate);
        refresh= (ImageView) findViewById(R.id.refresh);
        btnLogin= (Button) findViewById(R.id.login);
        autoLogin= (CheckBox) findViewById(R.id.autologin);
    }

    @Override
    protected void initEvents() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInfo()) {
                    Map<String, String> map = new HashMap();
                    map.put("Cookie", (String) SPUtils.get(LoginActivity.this, "jsessionid", ""));

                    OkHttpUtils
                            .post()
                            .url(Constant.URL_LOGIN)
                            .headers(map)
                            .addParams("username", etName.getText().toString())
                            .addParams("pwd", etPassword.getText().toString())
                            .addParams("imgcode", etValidate.getText().toString())
                            .build()
                            .execute(new Callback() {
                                @Override
                                public Object parseNetworkResponse(Response response, int id) throws Exception {
                                    return response.body().string();
                                }

                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    toast("连接失败，请退出后重试");
                                    Log.e("",e.getMessage());
                                }

                                @Override
                                public void onResponse(Object response, int id) {
                                    String json = (String) response;
                                    Log.d("",json);
                                    try {
                                        JSONObject object = new JSONObject(json);
                                        JSONObject loginReturn = object.getJSONObject("loginReturn");
                                        String msg = loginReturn.getString("msg");
                                        String flag = loginReturn.getString("loginFlag");

                                        if (flag.equals("0")) {
                                            toast(msg); //失败信息
                                            etPassword.setText("");
                                            etValidate.setText("");
                                            refreshImgCode();
                                        } else {
                                            // toast("登录成功！");

                                            if (autoLogin.isChecked()) {
                                                SPUtils.put(LoginActivity.this, "username", etName.getText().toString());
                                            }

                                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                            finish();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                }
            }
        });

    }

    private void refreshImgCode() {
        OkHttpUtils
                .get()
                .url(Constant.URL_CODE_IMAGE)
                .build()
                .execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        Map<String, Object> map = new HashMap<>();
                        map.put("Set-Cookie", response.headers().get("Set-Cookie"));
                        map.put("codeImg", BitmapFactory.decodeStream(response.body().byteStream()));
                        return map;
                    }

                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {

                    }


                    @Override
                    public void onResponse(Object response, int id) {
                        Map<String, Object> map = (Map<String, Object>) response;
                        imgValidate.setImageBitmap((Bitmap) map.get("codeImg"));

                        String setcookie = (String) map.get("Set-Cookie");
                        if (setcookie != null) {
                            Log.d("",setcookie.substring(0, setcookie.indexOf(';')));
                            SPUtils.put(LoginActivity.this, "jsessionid", setcookie.substring(0, setcookie.indexOf(';')));
                        }
                    }
                });
    }

    private boolean checkInfo() {
        etName.setError(null);
        etPassword.setError(null);
        etValidate.setError(null);

        if (etName.getText().toString().equals("")) {
            etName.setError("请输入用户名");
            autoLogin.setChecked(false);
            return false;
        }

        if (etPassword.getText().toString().equals("")) {
            etPassword.setError("请输入密码");
            autoLogin.setChecked(false);
            return false;
        }

        if (etValidate.getText().toString().equals("")) {
            etValidate.setError("请输入验证码");
            autoLogin.setChecked(false);
            return false;
        }
        return true;
    }
}
