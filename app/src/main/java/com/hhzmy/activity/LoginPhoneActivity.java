package com.hhzmy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
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


    @BindView(R.id.tv_loginphone_name)
    TextView tvLoginphoneName;
    @BindView(R.id.et_loginphone_name)
    EditText etLoginphoneName;
    @BindView(R.id.iv_clean_loginphone_cont_name)
    ImageView ivCleanLoginphoneContName;
    @BindView(R.id.cb_loginphone)
    CheckBox cbLoginphone;
    @BindView(R.id.tv_loginphone)
    TextView tvLoginphone;
    @BindView(R.id.bt_loginpass_next)
    Button btLoginpassNext;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);
        ButterKnife.bind(this);
        initEvent();

    }

    private void initEvent() {
        etLoginphoneName.setOnClickListener(this);
        ivCleanLoginphoneContName.setOnClickListener(this);
        btLoginpassNext.setOnClickListener(this);
        cbLoginphone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

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
                    etLoginphoneName.setText(sb.toString());
                    etLoginphoneName.setSelection(index);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    ivCleanLoginphoneContName.setVisibility(View.GONE);/*设置密码不可见*/

                } else {
                    ivCleanLoginphoneContName.setVisibility(View.VISIBLE);/*设置为可见*/
                }

            }
        };
        etLoginphoneName.addTextChangedListener(watcher);
        String text="";
        SpannableString span=new SpannableString(text);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        span.setSpan(new URLSpan("https://pay.suning.com/epp-paycore/pay/static/quickPayRelatedServiceContract.html"),2,8,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                span.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.colorAccent)),2,8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new URLSpan("http://res.m.suning.com/project/newuser_redpacket/member_agreement.html"),11,16,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                span.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.colorAccent)),11,16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLoginphone.setMovementMethod(new LinkMovementMethod());
        tvLoginphone.setText(span);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_loginpass_next:
                String etFName = etLoginphoneName.getText().toString();
                if(!LoginUtil.isMobileNO(this,etFName)){

                }else{
                    if(cbLoginphone.isChecked()){

                    }else{
                        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                    }
                }

                break;

            case R.id.iv_clean_loginphone_cont_name:
                etLoginphoneName.setText("");
                break;


        }
    }
}
