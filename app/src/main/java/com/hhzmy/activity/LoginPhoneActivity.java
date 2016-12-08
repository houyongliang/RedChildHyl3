package com.hhzmy.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.util.LoginUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginPhoneActivity extends AppCompatActivity implements View.OnClickListener {
    String text = "同意苏宁易购会员章程和易付宝协议";

    @BindView(R.id.tv_login_name)
    TextView tvLoginName;
    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.iv_clean_cont_name)
    ImageView ivCleanContName;
    @BindView(R.id.cb_loginphone)
    CheckBox cbLoginphone;
    @BindView(R.id.tv_loginphone)
    TextView tvLoginphone;
    @BindView(R.id.bt_loginpass_next)
    Button btLoginpassNext;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;
    private String etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);
        ButterKnife.bind(this);
        initEvent();

    }

    private void initEvent() {
        btLoginpassNext.setOnClickListener(this);
        ivCleanContName.setOnClickListener(this);

        cbLoginphone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    btLoginpassNext.setEnabled(false);
                    btLoginpassNext.setBackgroundColor(Color.WHITE);
                }else{
                    if(ivCleanContName.getVisibility()==View.VISIBLE){
                        btLoginpassNext.setEnabled(true);
                        btLoginpassNext.setBackgroundColor(Color.RED);
                    }
                }

            }
        });


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
                if (s.length() == 0||s.length()<13) {
                    ivCleanContName.setVisibility(View.GONE);/*设置密码不可见*/
                    btLoginpassNext.setEnabled(false);
                    btLoginpassNext.setBackgroundColor(Color.WHITE);
                } else if(s.length()>=13){
                    ivCleanContName.setVisibility(View.VISIBLE);/*设置为可见*/
                    /*对登陆按钮可点击进行判断*/
                        if(cbLoginphone.isChecked()){
                            btLoginpassNext.setEnabled(true);
                            btLoginpassNext.setBackgroundColor(Color.RED);
                        }



                }

            }
        };
        etLoginName.addTextChangedListener(watcher);

        addSpen();



    }

    private void addSpen() {


        SpannableString span = new SpannableString(text);


        span.setSpan(new URLSpan("http://res.m.suning.com/project/newuser_redpacket/member_agreement.html"), 2, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.colorAccent)), 2, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new URLSpan("http://res.m.suning.com/project/newuser_redpacket/member_agreement.html"), 11, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.colorAccent)), 11, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLoginphone.setMovementMethod(new LinkMovementMethod());
        tvLoginphone.setText(span);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_loginpass_next:
                  /*获取登录名字，并对 登陆的手机号进行正则验证，去掉中间和首尾空格*/
                etName = etLoginName.getText().toString().replaceAll(" ", "").trim();

//                if (!LoginUtil.isMobileNO(this, etName)) {
//
//                } else {
//                    if (cbLoginphone.isChecked()) {
//
//                    } else {
//                        Toast.makeText(this, "您必须同意苏宁服务条款后，才能提交注册", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
                if (TextUtils.isEmpty(etName))
                {
                    Toast.makeText(this,"请输入手机号",Toast.LENGTH_SHORT).show();
                    etLoginName.getText().clear();
                }
                else if (etName.length()!=11)
                {
                    Log.e("phoneNum.getText()",etLoginName.getText().toString().length()+"");
                    etLoginName.getText().clear();
                    Toast.makeText(this,"输入有误，请重新输入",Toast.LENGTH_SHORT).show();
                }else{
                    if (cbLoginphone.isChecked()) {
                        Intent intent = LoginPasswordActivity.toLoginPasswordActivity(this, etName);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "您必须同意苏宁服务条款后，才能提交注册", Toast.LENGTH_SHORT).show();

                    }
                }

                break;

            case R.id.iv_clean_cont_name:
                etLoginName.setText("");
                break;



        }
    }
}
