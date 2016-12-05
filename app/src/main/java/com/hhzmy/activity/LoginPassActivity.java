package com.hhzmy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hhzmy.mis.redchildhyl.R;
import com.hhzmy.util.Code;
import com.hhzmy.util.LoginUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hhzmy.mis.redchildhyl.R.id.iv_clean_loginpass_authcode;
import static com.hhzmy.mis.redchildhyl.R.id.iv_clean_loginpass_cont_name;

public class LoginPassActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.tv_loginpass_name)
    TextView tvLoginpassName;
    @BindView(R.id.et_loginpass_name)
    EditText etLoginpassName;
    @BindView(iv_clean_loginpass_cont_name)
    ImageView ivCleanLoginpassContName;
    @BindView(R.id.tv_loginpass_authcode)
    TextView tvLoginpassAuthcode;
    @BindView(R.id.et_loginpass_authcode)
    EditText etLoginpassAuthcode;
    @BindView(iv_clean_loginpass_authcode)
    ImageView ivCleanLoginpassAuthcode;
    @BindView(R.id.iv_loginpass_authcode)
    ImageView ivLoginpassAuthcode;
    @BindView(R.id.bt_loginpass_next)
    Button btLoginpass;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    String getCode=null; //获取验证码的值  a

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pass);
        ButterKnife.bind(this);/*初始化控件*/
        ivCleanLoginpassContName.setOnClickListener(this);
//        etLoginpassName.setOnClickListener(this);
//        etLoginpassAuthcode.setOnClickListener(this);
        ivCleanLoginpassContName.setOnClickListener(this);
        ivCleanLoginpassAuthcode.setOnClickListener(this);

        ivLoginpassAuthcode.setOnClickListener(this);
        btLoginpass.setOnClickListener(this);
        initEvent();/*初始化事件*/
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
                    etLoginpassName.setText(sb.toString());
                    etLoginpassName.setSelection(index);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    ivCleanLoginpassContName.setVisibility(View.GONE);/*设置密码不可见*/

                } else {
                    ivCleanLoginpassContName.setVisibility(View.VISIBLE);/*设置为可见*/
                }

            }
        };

        TextWatcher watcherAuto = new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    ivCleanLoginpassAuthcode.setVisibility(View.GONE);/*设置密码不可见*/

                } else {
                    ivCleanLoginpassAuthcode.setVisibility(View.VISIBLE);/*设置为可见*/
                }
            }
        };

        etLoginpassName.addTextChangedListener(watcher);
        etLoginpassAuthcode.addTextChangedListener(watcherAuto);
        ivLoginpassAuthcode.setImageBitmap(Code.getInstance().getBitmap());/*设置图片*/
        getCode=Code.getInstance().getCode(); //获取显示的验证码



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_loginpass_authcode:
                ivLoginpassAuthcode.setImageBitmap(Code.getInstance().getBitmap());/*设置图片*/
                getCode=Code.getInstance().getCode(); //获取显示的验证码
            break;
            case   R.id.bt_loginpass_next:
                String etPName = etLoginpassName.getText().toString();
                String newCode = etLoginpassAuthcode.getText().toString();
                if(TextUtils.isEmpty(newCode)){
                    Toast.makeText(this, "没有填写验证码", Toast.LENGTH_SHORT).show();
                }else if(!newCode.equals(getCode)){
                    Toast.makeText(this, "验证码填写不正确", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "操作成功", Toast.LENGTH_SHORT).show();
                    if(LoginUtil.isMobileNO(this,etPName)){
                        startActivity(new Intent(this,LoginPassActivity.class));
                    }
                }

                break;
            case R.id.iv_clean_loginpass_cont_name:
                etLoginpassName.setText("");
                break;
            case iv_clean_loginpass_authcode:
                etLoginpassAuthcode.setText("");
                break;

        }
    }
}
