package com.taiwan.oomatcher.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.taiwan.oomatcher.R;
import com.taiwan.oomatcher.base.BaseActivity;
import com.taiwan.oomatcher.config.Config;
import com.taiwan.oomatcher.entity.Request.LoginRequest;
import com.taiwan.oomatcher.entity.Request.SignRequest;
import com.taiwan.oomatcher.entity.Response.LoginResponse;
import com.taiwan.oomatcher.http.HttpUtils;
import com.taiwan.oomatcher.utils.CountDownTimerUtils;
import com.taiwan.oomatcher.utils.DbUtils;
import com.taiwan.oomatcher.utils.EncodeUtils;
import com.taiwan.oomatcher.utils.SPUtils;
import com.taiwan.oomatcher.utils.SystemUtil;
import com.taiwan.oomatcher.utils.Tools;

import static com.taiwan.oomatcher.R.id.sign_phone_type;

/**
 * 作 者: huanghuojun
 * 描 述: 登录注册页
 * 版 本:
 * 创建日期: 2017/12/18 21:36
 */
public class LoginActivity extends BaseActivity{

    private Context mContext;
    private EditText mSign_phoneEdit;
    private EditText mSign_pswEdit;
    private TextView mLogin_phone_typeTV;
    private TextView mSign_get_codeTV;
    private EditText mLogin_phoneEdit;
    private TextView mSign_upTV;
    private LinearLayout mSign_mainLl;
    private ImageView mSign_iconImgV;
    private TextView mLoginTV;
    private RelativeLayout mSign_titleRl;
    private TextView mSign_phone_typeTV;
    private EditText mLogin_pswEdit;
    private EditText mSign_inviterEdit;
    private EditText mSign_codeEdit;
    private RelativeLayout mLogin_titleRl;
    private EditText mSign_con_pswEdit;
    private LinearLayout mLogin_mainLl;
    private ImageView mLogin_iconImgV;

    private CountDownTimerUtils timerUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    protected void initView() {
         setContentView(R.layout.activity_login);
         mSign_phoneEdit = (EditText) findViewById(R.id.sign_phone);
         mSign_pswEdit = (EditText) findViewById(R.id.sign_psw);
         mLogin_phone_typeTV = (TextView) findViewById(R.id.login_phone_type);
         mSign_get_codeTV = (TextView) findViewById(R.id.sign_get_code);
         mLogin_phoneEdit = (EditText) findViewById(R.id.login_phone);
         mSign_upTV = (TextView) findViewById(R.id.sign_up);
         mSign_mainLl = (LinearLayout) findViewById(R.id.sign_main);
         mSign_iconImgV = (ImageView) findViewById(R.id.sign_icon);
         mLoginTV = (TextView) findViewById(R.id.login);
         mSign_titleRl = (RelativeLayout) findViewById(R.id.sign_title);
         mSign_phone_typeTV = (TextView) findViewById(sign_phone_type);
         mLogin_pswEdit = (EditText) findViewById(R.id.login_psw);
         mSign_inviterEdit = (EditText) findViewById(R.id.sign_inviter);
         mSign_codeEdit = (EditText) findViewById(R.id.sign_code);
         mLogin_titleRl = (RelativeLayout) findViewById(R.id.login_title);
         mSign_con_pswEdit = (EditText) findViewById(R.id.sign_con_psw);
         mLogin_mainLl = (LinearLayout) findViewById(R.id.login_main);
         mLogin_iconImgV = (ImageView) findViewById(R.id.login_icon);
    }

    @Override
    protected void init() {
    }

    @Override
    protected void setListeners() {
        mSign_titleRl.setOnClickListener(this);
        mLogin_titleRl.setOnClickListener(this);

        mLogin_phone_typeTV.setOnClickListener(this);
        mSign_phone_typeTV.setOnClickListener(this);
        mSign_get_codeTV.setOnClickListener(this);
        mSign_upTV.setOnClickListener(this);
        mLoginTV.setOnClickListener(this);
    }

    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.login_phone_type:
                break;
            case R.id.sign_get_code:
                getCode();
                break;
            case R.id.sign_up:{
                sign();
                break;
            }
            case R.id.login:{
                login();
                break;
            }
            case R.id.sign_title:
                mLogin_mainLl.setVisibility(View.GONE);
                mSign_mainLl.setVisibility(View.VISIBLE);
                mLogin_iconImgV.setVisibility(View.GONE);
                mSign_iconImgV.setVisibility(View.VISIBLE);
                break;
            case sign_phone_type:
                break;
            case R.id.login_title:
                mLogin_mainLl.setVisibility(View.VISIBLE);
                mSign_mainLl.setVisibility(View.GONE);
                mLogin_iconImgV.setVisibility(View.VISIBLE);
                mSign_iconImgV.setVisibility(View.GONE);
                break;
        }
    }

    public void getCode() {
        if(Tools.isEmpty(mSign_phoneEdit.getText().toString())){
            showToast(R.string.register_phone_error);
            return;
        }
        String codeType = mSign_phone_typeTV.getText().toString();
        if(timerUtils == null){
            timerUtils = new CountDownTimerUtils(mSign_get_codeTV, 60000,1000);
        }
        timerUtils.start();
        JSONObject json = new JSONObject();
        json.put("language", (String)SPUtils.get(SPUtils.LANGUAGE_CODE, ""));
        json.put("mobileNumber", codeType+mSign_phoneEdit.getText().toString());
        json.put("codeType", 1);
        HttpUtils.sendPostSign("sendValidCode", json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String responseData) {

            }

            @Override
            public void onFailure(Exception e, String msg) {
                showToast(msg);
            }
        });
    }

    public void sign() {
        showProgress();
        if(Tools.isEmpty(mSign_phoneEdit.getText().toString())){
            showToast(R.string.register_phone_error);
            return;
        }
        if(Tools.isEmpty(mSign_codeEdit.getText().toString())){
            showToast(R.string.register_code_error);
            return;
        }
        if(Tools.isEmpty(mSign_pswEdit.getText().toString())){
            showToast(R.string.register_pwd_error);
            return;
        }
        if(Tools.isEmpty(mSign_con_pswEdit.getText().toString())){
            showToast(R.string.register_pwd_error1);
            return;
        }
        String codeType = mSign_phone_typeTV.getText().toString();
        JSONObject json = new JSONObject();
        json.put("inviterCode", mSign_inviterEdit.getText().toString());
        json.put("mobileNumber", codeType + mSign_phoneEdit.getText().toString());
        json.put("password", mSign_pswEdit.getText().toString());
        json.put("rePassword", mSign_con_pswEdit.getText().toString());
        json.put("validCode", mSign_codeEdit.getText().toString());

        HttpUtils.sendPostSign("register",  json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String responseData) {
                hideProgress();
                mLogin_mainLl.setVisibility(View.VISIBLE);
                mSign_mainLl.setVisibility(View.GONE);
                mLogin_iconImgV.setVisibility(View.VISIBLE);
                mSign_iconImgV.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Exception e, String msg) {
                hideProgress();
                showToast(msg);
            }
        });
    }

    public void login() {
        showProgress();
        if(Tools.isEmpty(mLogin_phoneEdit.getText().toString())){
            showToast(R.string.register_phone_error);
            return;
        }
        if(Tools.isEmpty(mLogin_pswEdit.getText().toString())){
            showToast(R.string.register_pwd_error);
            return;
        }
        String codeType = mLogin_phone_typeTV.getText().toString();
        JSONObject json = new JSONObject();
        json.put("mobileNumber", EncodeUtils.urlEncode(codeType + mLogin_phoneEdit.getText().toString()));
        json.put("appTypeId", 2);
        json.put("password", mLogin_pswEdit.getText().toString());
        json.put("appId", Tools.getAppVersionCode(mContext));
        json.put("phoneType", SystemUtil.getSystemModel());
        HttpUtils.sendGetSign("login", json, new HttpUtils.CallBackText() {
            @Override
            public void onSuccess(String responseData) {
                LoginResponse login = JSONObject.parseObject(responseData, LoginResponse.class);
                SPUtils.put(SPUtils.TOKEN, login.getToken());
                DbUtils.save(login);
                gotoMain();
            }

            @Override
            public void onFailure(Exception e, String msg) {
                hideProgress();
                showToast(msg);
            }
        });
    }

    private void gotoMain(){
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
