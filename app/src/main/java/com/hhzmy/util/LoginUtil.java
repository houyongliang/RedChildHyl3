package com.hhzmy.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/5.
 */

public class LoginUtil {

    public static boolean isMobileNO(Context context,String mobiles) {
          /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        if (TextUtils.isEmpty(mobiles)) {
//            Toast.makeText(context, "用户名不能为空", Toast.LENGTH_SHORT).show();

            return false;
        } else {
            //判断手机格式是否正确
            Pattern p = Pattern
                    .compile("[1][3578]\\d{9}");/*"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位*/
            Matcher m = p.matcher(mobiles);
            return m.matches();
        }

    }
    /*判断是否是密码格式*/
    public static boolean isPasswordForm(String password){
        if(TextUtils.isEmpty(password)) return false;
        else{//判断密码格式是否正确
            Pattern p = Pattern
                    .compile("[a-zA-Z]\\d{9}");/*"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位*/
            Matcher m = p.matcher(password);
            return m.matches();
        }


    }

}
