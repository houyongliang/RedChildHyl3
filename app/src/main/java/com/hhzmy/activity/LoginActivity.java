package com.hhzmy.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.util.LoginUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hhzmy.util.LoginUtil.isMobileNO;

public class LoginActivity extends AppCompatActivity implements ImageView.OnClickListener {

    @BindView(R.id.tv_login_name)
    TextView tvLoginName;
    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.tv_login_password)
    TextView tvLoginPassword;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.ib_login_showpawd)
    ImageButton ibLoginShowpawd;
    @BindView(R.id.iv_register)
    ImageView ivRegister;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    @BindView(R.id.iv_clean_cont_pass)
    ImageView ivCleanContPass;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.iv_clean_cont_name)
    ImageView ivCleanContName;
    @BindView(R.id.tv_forget_pass)
    TextView tvForgetPass;
    @BindView(R.id.tv_login_qq)
    TextView tvLoginQq;
    @BindView(R.id.tv_login_weixin)
    TextView tvLoginWeixin;
    @BindView(R.id.tv_login_xina)
    TextView tvLoginXina;


    private String etName;
    private String etPassword;

    private Boolean isPassShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*初始化控件*/
        ButterKnife.bind(this);
        initData();
        initEvent();/*初始化事件*/


    }

    private void initData() {
        ivCleanContName.setOnClickListener(this);
        ivCleanContPass.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        ibLoginShowpawd.setOnClickListener(this);
        tvForgetPass.setOnClickListener(this);
        ivRegister.setOnClickListener(this);
        tvLoginQq.setOnClickListener(this);/*qq登陆*/
        tvLoginWeixin.setOnClickListener(this);/*微信登陆*/
        tvLoginXina.setOnClickListener(this);/*sina 登陆*/
        /*ibLoginShowpawd 初始化状态 */
        ibLoginShowpawd.setBackgroundResource(R.mipmap.icon_hidden);
        etLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    private void initEvent() {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() == 0)
                    return;
               /*判断是只输入 数字，如果输入不是数字则提示信息--不能输入空格，*/


                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    if (i != 3 && i != 8 && s.charAt(i) == ' ') {
                        continue;
                    } else {
                        sb.append(s.charAt(i));
                        if ((sb.length() == 4 || sb.length() == 9)
                                && sb.charAt(sb.length() - 1) != ' ') {
                            sb.insert(sb.length() - 1, ' ');
                        }
                    }
                }
                if (!sb.toString().equals(s.toString())) {
                    int index = start + 1;
                    if (sb.charAt(start) == ' ') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }
                    etLoginName.setText(sb.toString());
                    etLoginName.setSelection(index);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    ivCleanContName.setVisibility(View.GONE);/*设置密码不可见*/
                    btLogin.setEnabled(false);
                    btLogin.setBackgroundColor(Color.WHITE);
                } else {
                    ivCleanContName.setVisibility(View.VISIBLE);/*设置为可见*/
                    /*对登陆按钮可点击进行判断*/
                    if (ivCleanContPass.getVisibility() == View.VISIBLE) {
                        btLogin.setEnabled(true);
                        btLogin.setBackgroundColor(Color.RED);

                    }
                }

            }
        };
        etLoginName.addTextChangedListener(watcher);
        /*获取登录名字，并对 登陆的手机号进行正则验证，去掉中间和首尾空格*/
        etName = etLoginName.getText().toString().replaceAll(" ", "").trim();

        TextWatcher watcherPass = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    ivCleanContPass.setVisibility(View.GONE);/*设置密码不可见*/
                    btLogin.setEnabled(false);
                    btLogin.setBackgroundColor(Color.WHITE);
                } else {
                    ivCleanContPass.setVisibility(View.VISIBLE);/*设置为可见*/
                    /*对登陆按钮可点击进行判断*/
                    if (ivCleanContName.getVisibility() == View.VISIBLE) {
                        btLogin.setEnabled(true);
                        btLogin.setBackgroundColor(Color.RED);
                    }

                }
            }
        };
        etLoginPassword.addTextChangedListener(watcherPass);
        /*获取登录密码进行判断*/
        etPassword = etLoginPassword.getText().toString();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                if (!isMobileNO(this, etName) || !LoginUtil.isPasswordForm(etPassword)) {
                    Toast.makeText(this, "输入账号密码不正确请重新输入", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(this, LoginPhoneActivity.class));/*跳转到主页面*/
                }

                break;
            case R.id.iv_clean_cont_name:
                etLoginName.setText("");
                break;
            case R.id.iv_clean_cont_pass:
                etLoginPassword.setText("");
                break;
            case R.id.ib_login_showpawd:/*设置点击状态改变*/
                isPassShow = !isPassShow;
//                ibLoginShowpawd.setSelected(isPassShow);
                if (isPassShow) {
                    ibLoginShowpawd.setBackgroundResource(R.mipmap.icon_display);
//                  etLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etLoginPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    ibLoginShowpawd.setBackgroundResource(R.mipmap.icon_hidden);
                    etLoginPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.tv_forget_pass:
                startActivity(new Intent(this, LoginPassActivity.class));

                break;
            case R.id.iv_register:
                startActivity(new Intent(this, LoginPhoneActivity.class));

            case R.id.tv_login_qq:
                UMShareAPI  mShareAPI = UMShareAPI.get( this );
                mShareAPI.doOauthVerify(this, SHARE_MEDIA.QQ, umAuthListener);

                break;
            case R.id.tv_login_weixin:
                UMShareAPI  mShareAPI1 = UMShareAPI.get( this );
                mShareAPI1.doOauthVerify(this, SHARE_MEDIA.WEIXIN, umAuthListener);

                break;
            case R.id.tv_login_xina:
                UMShareAPI  mShareAPI2 = UMShareAPI.get( this );
                mShareAPI2.doOauthVerify(this, SHARE_MEDIA.SINA, umAuthListener);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };
}
