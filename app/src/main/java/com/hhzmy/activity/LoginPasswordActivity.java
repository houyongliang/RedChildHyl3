package com.hhzmy.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhzmy.bean.User;
import com.hhzmy.httputil.Utils;
import com.hhzmy.mis.redchildhyl.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.SMSSDK;

/*设置密码页面*/
public class LoginPasswordActivity extends AppCompatActivity {
    private final static String GET_USER_TEL = "com.hhzmy.activity.tel";
    public final static String KEY_TEL= "com.hhzmy.activity.telephone";
    public final static String KEY_PASSWORD= "com.hhzmy.activity.password";
    public  final static String KEY_ISCOMMIT= "com.hhzmy.activity.iscommit";
    private Boolean isPassShow=false;
    @BindView(R.id.et_loginTimes_authcode)
    EditText etLoginTimesAuthcode;
    @BindView(R.id.tv_loginTimesSet_authcode)/*设置 times */
    TextView tvLoginTimesAuthcode;


    @BindView(R.id.bt_loginpassword_commite)
    Button btLoginpasswordCommite;
    @BindView(R.id.tv_showTel)
    TextView tvShowTel;


    @BindView(R.id.et_login_Setpassword)/*密码输入框*/
    EditText etLoginSetpassword;
    @BindView(R.id.ib_login_SetShowpawd)/*显示密码显示或隐藏*/
    ImageButton ibLoginSetShowpawd;
    @BindView(R.id.iv_clean_cont_Setpass)/*设置情况et 信息*/
    ImageView ivCleanContSetPass;

    private String extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_password);
        ButterKnife.bind(this);
        extra = getIntent().getStringExtra(GET_USER_TEL);
        if (extra != null) {
            tvShowTel.setText("短信号码已发送至 " + extra);
        }


    }

    private TimeCount time;

    @OnClick({R.id.ib_login_SetShowpawd, R.id.tv_loginTimesSet_authcode, R.id.iv_clean_cont_Setpass,  R.id.bt_loginpassword_commite})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_loginTimesSet_authcode:
                showTimes();
                break;

            case R.id.ib_login_SetShowpawd:/*显示密码显示或隐藏*/
                isPassShow = !isPassShow;
//                ibLoginShowpawd.setSelected(isPassShow);
                if (isPassShow) {
                    ibLoginSetShowpawd.setBackgroundResource(R.mipmap.icon_display);
//                  etLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etLoginSetpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    ibLoginSetShowpawd.setBackgroundResource(R.mipmap.icon_hidden);
                    etLoginSetpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
            case R.id.iv_clean_cont_Setpass:/*设置情况et 信息*/
                etLoginSetpassword.setText("");
                break;
            case R.id.bt_loginpassword_commite:
                String password = etLoginSetpassword.getText().toString().replace(" ", "").trim();
                if(password.length()>6&&password.length()<20&&extra!=null){
                    User user=new User();
                    user.tel=extra;
                    user.password=password;
                    EventBus.getDefault().postSticky(user);
                    Utils.putSP(this,KEY_ISCOMMIT,true);
                    Utils.putSPString(this,KEY_TEL,extra);
                    Utils.putSPString(this,KEY_PASSWORD,password);
                    startActivity(new Intent(this,LoginActivity.class));
                    finish();


                }
                break;
        }
    }

    private void showTimes() {/*展示倒计时*/
//        if (TextUtils.isEmpty(etLoginTimesAuthcode.getText()))
//        {
//            Toast.makeText(this,"请输入手机号",Toast.LENGTH_SHORT).show();
//            etLoginTimesAuthcode.getText().clear();
//        }
//        else if (etLoginTimesAuthcode.getText().toString().replace(" ","").trim().length()!=11)
//        {
//            Log.e("phoneNum.getText()",etLoginTimesAuthcode.getText().toString().length()+"");
//            etLoginTimesAuthcode.getText().clear();
//            Toast.makeText(this,"输入有误，请重新输入",Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
        if (extra != null) {
            //进行验证
            time = new TimeCount(60000, 1000);//参数依次为总时长，计时时间间隔
            time.start();
            SMSSDK.getVerificationCode("86", extra);
        }


//        }
    }


    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程显示
        @Override
        public void onTick(long millisUntilFinished) {
            tvLoginTimesAuthcode.setTextSize(14);
            tvLoginTimesAuthcode.setText(millisUntilFinished / 1000 + "秒后重新发送");
            tvLoginTimesAuthcode.setClickable(false);
            int mycolor = getResources().getColor(R.color.umeng_text_color);
            tvLoginTimesAuthcode.setBackgroundColor(mycolor);

        }

        //计时完成触发
        @Override
        public void onFinish() {
            tvLoginTimesAuthcode.setText("获取验证码");
            tvLoginTimesAuthcode.setBackgroundColor(Color.YELLOW);
            tvLoginTimesAuthcode.setClickable(true);
        }
    }

    public static Intent toLoginPasswordActivity(Context context, String str) {
        Intent intent = new Intent(context, LoginPasswordActivity.class);
        intent.putExtra(GET_USER_TEL, str);
        return intent;
    }
}
